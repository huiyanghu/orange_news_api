package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.service.impl.AppVersionServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/6/28.
 */
public class VersionCloud {

	private static final Logger logger = LogManager.getLogger(VersionCloud.class);


	/**
	 * 检查新版本
	 * @param channelId
	 * @param appId
	 * @param versionCode
	 * @return
	 */
	@EngineFunction("newVersionData")
	public static String newVersionData(@EngineFunctionParam("channelId") String channelId,
	                                       @EngineFunctionParam("appId") String appId,
	                                       @EngineFunctionParam("versionCode") int versionCode) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> versionMap = new HashMap<>();

		if (StringUtil.isNotEmpty(channelId) && StringUtil.isNotEmpty(appId) && versionCode > 0) {
			AVObject newAppVersionObj = new AppVersionServiceImpl().getNewAppVersion(channelId, appId, versionCode);
			if (null != newAppVersionObj) {
				versionMap.put("versionCode", newAppVersionObj.getInt("versionCode"));
				versionMap.put("versionName", newAppVersionObj.getString("versionName"));
				versionMap.put("versionUrl", newAppVersionObj.getString("versionUrl"));
				versionMap.put("versionDesc", StringUtil.isNotEmpty(newAppVersionObj.getString("versionDesc")) ? newAppVersionObj.getString("versionDesc") : "");
				versionMap.put("createdAt", newAppVersionObj.getCreatedAt().getTime());
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("newVersion", versionMap);
		return JSON.toJSONString(resultMap);
	}
}
