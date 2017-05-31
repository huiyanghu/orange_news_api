package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dao.UserDao;
import com.it7890.orange.api.service.IUserService;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/5/24.
 */
public class UserServiceImpl implements IUserService {

	@Override
	public boolean getIsExistUsername(String username) {
		return new UserDao().getIsExistUsername(username);
	}

	@Override
	public boolean getIsExistEmail(String email) {
		return new UserDao().getIsExistEmail(email);
	}

	@Override
	public AVUser updateUserInfo(AVUser userInfo) {
		return new UserDao().updateUserInfo(userInfo);
	}

	@Override
	public Map<String, Object> updateUserAvatar(AVUser userInfo, String fileId) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		if (null != userInfo) {
			boolean isExists = new FileServiceImpl().isExistById(fileId);
			if (isExists) {
				userInfo.put("avatarObj", AVObject.createWithoutData("_File", fileId));
				new UserDao().updateUserInfo(userInfo);
			} else {
				resultCode = Constants.CODE_CANNOT_FIND;
				resultMsg = "未找到用户头像";
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "为获取到用户信息";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return resultMap;
	}

	public Map<String, Object> updateUserInfo(AVUser userInfo, String fileId, String nickName, int sex) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		if (null != userInfo) {
			if (StringUtil.isNotEmpty(fileId)) {
				boolean isExists = new FileServiceImpl().isExistById(fileId);
				if (isExists) {
					userInfo.put("avatarObj", AVObject.createWithoutData("_File", fileId));
				} else {
					resultCode = Constants.CODE_CANNOT_FIND;
					resultMsg = "未找到用户头像";
				}
			}
			if (resultCode == Constants.CODE_SUCCESS) {
				if (StringUtil.isNotEmpty(nickName)) {
					userInfo.put("nickName", nickName);
				}
				userInfo.put("sex", sex);
				new UserDao().updateUserInfo(userInfo);
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "为获取到用户信息";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return resultMap;
	}
}
