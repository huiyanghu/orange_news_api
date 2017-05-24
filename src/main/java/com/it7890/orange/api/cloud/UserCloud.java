package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
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
					int errorCode = -1;
					AVUser loginUser = null;
					try {
						loginUser = AVUser.logIn(currentUser.getUsername(), oldPassword);
					} catch (AVException e) {
						e.printStackTrace();
						errorCode = e.getCode();
					}
					if (null != loginUser && errorCode == -1) {
						loginUser.setPassword(newPassword);
						try {
							loginUser.save();
						} catch (AVException e) {
							e.printStackTrace();
							logger.info("用户修改密码失败，errorCode:{}, cause: {}", e.getCode(), e);
						}
					} else {
						resultCode = Constants.CODE_PARAMS_FAIL;
						resultMsg = "原密码错误";
					}
				} else {
					resultCode = Constants.CODE_PARAMS_FAIL;
					resultMsg = "请先登录";
				}
			} else {
				resultCode = Constants.CODE_PARAMS_FAIL;
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
}
