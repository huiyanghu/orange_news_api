package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.AppVersionDao;
import com.it7890.orange.api.service.IAppVersionService;

/**
 * Created by Astro on 17/6/28.
 */
public class AppVersionServiceImpl implements IAppVersionService {

	@Override
	public AVObject getNewAppVersion(String channelId, String appId, int versionCode) {
		return new AppVersionDao().getNewAppVersion(channelId, appId, versionCode);
	}
}
