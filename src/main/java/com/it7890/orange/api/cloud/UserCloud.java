package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.*;
import com.it7890.orange.api.dao.UserDao;
import com.it7890.orange.api.dto.UserDTO;
import com.it7890.orange.api.service.impl.UserServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/5/24.
 */
public class UserCloud {

	private final static Logger logger = LogManager.getLogger(UserCloud.class);

	/**
	 * 注册
	 * @param email
	 * @param password
	 * @return
	 */
	@EngineFunction("register")
	public static String register(@EngineFunctionParam("email") String email, @EngineFunctionParam("password") String password) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		if (StringUtil.isNotEmpty(email) && StringUtil.isNotEmpty(password)) {
			boolean isExist = new UserServiceImpl().getIsExistUsername(email);
			if (!isExist) {
				AVUser user = new AVUser();
				user.setEmail(email);
				user.setUsername(email);
				user.setPassword(password);
				try {
					user.signUp();
				} catch (AVException e) {
					e.printStackTrace();

					resultCode = e.getCode();
					resultMsg = getRegisterMsg(resultCode);
				}
			} else {
				resultCode = Constants.CODE_EMAIL_EXIST;
				resultMsg = "邮箱已经被占用";
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "邮箱或密码不能为空";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}


	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@EngineFunction("updatePassword")
	public static String updatePassword(@EngineFunctionParam("oldPassword") String oldPassword, @EngineFunctionParam("newPassword") String newPassword) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		if (StringUtil.isNotEmpty(oldPassword) && StringUtil.isNotEmpty(newPassword)) {
			if (!oldPassword.equals(newPassword)) {
				AVUser currentUser = AVUser.getCurrentUser();
				if (null != currentUser) {
					try {
						AVUser loginUser = AVUser.logIn(currentUser.getUsername(), oldPassword);
						if (null != loginUser) {
							loginUser.setPassword(newPassword);
							loginUser.save();
						} else {
							resultCode = Constants.CODE_USER_PASSWORD_FAIL;
							resultMsg = "原密码错误";
						}
					} catch (AVException e) {
						e.printStackTrace();
						logger.info("用户修改密码失败，errorCode:{}, cause: {}", e.getCode(), e);
						resultCode = Constants.CODE_USER_PASSWORD_FAIL;
						resultMsg = "原密码错误";
					}
				} else {
					resultCode = Constants.CODE_AUTHORIZE_FAIL;
					resultMsg = "请先登录";
				}
			} else {
				resultCode = Constants.CODE_USER_PASSWORD_NOT_EQUALS;
				resultMsg = "两次密码相同，不允许修改";
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("forgotPassword")
	public static String forgotPassword(@EngineFunctionParam("email") String email,
										@EngineFunctionParam("newPassWord") String newPassWord) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		if (StringUtil.isNotEmpty(email)) {
			boolean isExists = new UserDao().getIsExistEmail(email);
			if (isExists) {
				AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
					public void done(AVException e) {
						if (e!=null){
							logger.info("重置密码邮件发送失败，cause: {}, code: {}", e.getMessage(), e.getCode());
						}else {
							logger.info("重置密码邮件发送成功：{}", email);
						}
					}
				});
			} else {
				resultCode = Constants.CODE_CANNOT_FIND;
				resultMsg = "该邮箱未注册";
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("getUserInfo")
	public static String getUserInfo() {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		AVUser currentUser = AVUser.getCurrentUser();
		if (null == currentUser) {
			resultCode = Constants.CODE_AUTHORIZE_OVERDUE;
			resultMsg = "用户未登录";
		} else {
			AVObject avatarObj = currentUser.getAVObject("avatarObj");
			if (null != avatarObj) {
				try {
					avatarObj.fetch();
				} catch (AVException e) {
					e.printStackTrace();
					logger.warn("用户头像对象存在，但是属性取不到，{}", e.getMessage());
				}
			}
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("userInfo", buildUserDto(currentUser));
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("updateUserAvatar")
	public static String updateUserAvatar(@EngineFunctionParam("fileId") String fileId) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = null;

		AVUser currentUser = AVUser.getCurrentUser();
		if (null != currentUser) {
			if (StringUtil.isNotEmpty(fileId)) {
				resultMap = new UserServiceImpl().updateUserAvatar(currentUser, fileId);
			} else {
				resultCode = Constants.CODE_PARAMS_FAIL;
				resultMsg = "参数错误";
			}
		} else {
			resultCode = Constants.CODE_AUTHORIZE_OVERDUE;
			resultMsg = "用户未登录";
		}
		if (null == resultMap) {
			resultMap = new HashMap<>();
			resultMap.put("code", resultCode);
			resultMap.put("msg", resultMsg);
		}
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("updateUserInfo")
	public static String updateUserInfo(@EngineFunctionParam("fileId") String fileId, @EngineFunctionParam("nickName") String nickName, @EngineFunctionParam("sex") int sex) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = null;

		AVUser currentUser = AVUser.getCurrentUser();
		if (null != currentUser) {
			resultMap = new UserServiceImpl().updateUserInfo(currentUser, fileId, nickName, sex);
		} else {
			resultCode = Constants.CODE_AUTHORIZE_OVERDUE;
			resultMsg = "用户未登录";
		}
		if (null == resultMap) {
			resultMap = new HashMap<>();
			resultMap.put("code", resultCode);
			resultMap.put("msg", resultMsg);
		}
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("updateBrowseSign")
	public static String updateBrowseSign(@EngineFunctionParam("loadSign") int loadSign) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		AVUser currentUser = AVUser.getCurrentUser();
		if (null != currentUser) {
			loadSign = loadSign != 0 ? 1 : 0;  // 1源链接模式 0快捷模式
			currentUser.put("loadSign", loadSign);
			new UserServiceImpl().updateUserInfo(currentUser);
		} else {
			resultCode = Constants.CODE_AUTHORIZE_OVERDUE;
			resultMsg = "用户未登录";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("bindEmial")
	public static String bindEmial(@EngineFunctionParam("email") String email) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		AVUser currentUser = AVUser.getCurrentUser();
		if (null != currentUser){
			if (StringUtil.isNotEmpty(email)) {
				boolean isBind = new UserDao().getIsBindEmail(email);
				if (isBind) {
					resultCode = Constants.CODE_CANNOT_FIND;
					resultMsg = "该邮箱已被绑定";
				} else {
					AVUser.requestEmailVerifyInBackground(email, new RequestEmailVerifyCallback() {
						public void done(AVException e) {
							if (e!=null){
								logger.info("发送绑定邮箱邮件失败，cause: {}， code: {}", e.getMessage(), e.getCode());
							}else {
								logger.info("发送绑定邮箱邮件成功：{}", email);
							}
						}
					});
				}
			} else {
				resultCode = Constants.CODE_PARAMS_FAIL;
				resultMsg = "参数错误";
			}
		}else {
			resultCode = Constants.CODE_CANNOT_FIND;
			resultMsg = "请登录";
		}


		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}
	private static String getRegisterMsg(int errorCode) {
		String resultMsg = "服务异常，请稍后再试";
		switch (errorCode) {
			case Constants.CODE_USERNAME_EMPTY:
				resultMsg = "用户名不能为空";
				break;
			case Constants.CODE_PASSWORD_EMPTY:
				resultMsg = "密码不能为空";
				break;
			case Constants.CODE_USERNAME_EXIST:
				resultMsg = "用户名已经被占用";
				break;
			case Constants.CODE_EMAIL_EXIST:
				resultMsg = "邮箱已经被占用";
				break;
			case Constants.CODE_EMAIL_EMPTY:
				resultMsg = "邮箱不能为空";
				break;
			default:
				break;
		}
		return resultMsg;
	}

	private static UserDTO buildUserDto(AVUser userInfo) {
		UserDTO userDto = null;
		if (null != userInfo) {
			userDto = new UserDTO();
			userDto.setObjectId(userInfo.getObjectId());
			userDto.setUsername(userInfo.getString("username"));
			userDto.setEmail(userInfo.getString("email"));

			String avatarUrl = "";
			if (null != userInfo.getAVObject("avatarObj") && StringUtil.isNotEmpty(userInfo.getAVObject("avatarObj").getString("url"))) {
				avatarUrl = userInfo.getAVObject("avatarObj").getString("url");
			}
			userDto.setAvatarUrl(avatarUrl);
			userDto.setNickName(StringUtil.isNotEmpty(userInfo.getString("nickName")) ? userInfo.getString("nickName") : "");
			userDto.setSex(null != userInfo.get("sex") ? userInfo.getInt("sex") : -1);
			userDto.setLoadSign(null != userInfo.get("loadSign") ? userInfo.getInt("loadSign") : 0);
			userDto.setEmailBind(userInfo.getBoolean("emailVerified"));
		}
		return userDto;
	}
}