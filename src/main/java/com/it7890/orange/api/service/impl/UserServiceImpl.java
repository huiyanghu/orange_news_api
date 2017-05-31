package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dao.UserDao;
import com.it7890.orange.api.service.IUserService;

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
}
