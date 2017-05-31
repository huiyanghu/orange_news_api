package com.it7890.orange.api.service;

import com.avos.avoscloud.AVUser;

/**
 * Created by Astro on 17/5/24.
 */
public interface IUserService {

	boolean getIsExistUsername(String username);

	boolean getIsExistEmail(String email);
}
