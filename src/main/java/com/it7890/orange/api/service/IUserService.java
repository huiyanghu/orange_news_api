package com.it7890.orange.api.service;

import com.avos.avoscloud.AVUser;

import java.util.Map;

/**
 * Created by Astro on 17/5/24.
 */
public interface IUserService {

	boolean getIsExistUsername(String username);

	boolean getIsExistEmail(String email);

	Map<String, Object> updateUserAvatar(AVUser userInfo, String fileId);

	Map<String, Object> updateUserInfo(AVUser userInfo, String fileId, String nickName, int sex);
}
