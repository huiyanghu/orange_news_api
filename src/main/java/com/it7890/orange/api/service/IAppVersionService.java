package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;

/**
 * Created by Astro on 17/6/28.
 */
public interface IAppVersionService {

	AVObject getNewAppVersion(String channelId, String appId, int versionCode);
}
