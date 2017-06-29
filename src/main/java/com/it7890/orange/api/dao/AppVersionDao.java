package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.util.StringUtil;

/**
 * Created by Astro on 17/6/28.
 */
public class AppVersionDao {

	public AVObject getNewAppVersion(String channelId, String appId, int versionCode) {
		AVObject newAppVersion = null;
		if (StringUtil.isNotEmpty(channelId) && StringUtil.isNotEmpty(appId) && versionCode > 0) {
			String cql = "select * from AppVersion where channelId = ? and appId = ? and versionCode > ? order by versionCode desc limit 1";
			try {
				AVCloudQueryResult queryResult = AVQuery.doCloudQuery(cql, new Object[]{channelId, appId, versionCode});
				if (null != queryResult.getResults() && queryResult.getResults().size() > 0) {
					newAppVersion = queryResult.getResults().get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newAppVersion;
	}
}
