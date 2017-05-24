package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.service.IUserService;
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

	@EngineFunction("register")
	public static String register(@EngineFunctionParam("email") String email, @EngineFunctionParam("password") String password) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		if (StringUtil.isNotEmpty(email) && StringUtil.isNotEmpty(password)) {
			boolean isExist = new UserServiceImpl().getIsExistUsername(email);
			if (!isExist) {
				AVUser user = new AVUser();
				user.setUsername(email);
				user.setPassword(password);
				user.setEmail(email); // 设置邮箱
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
