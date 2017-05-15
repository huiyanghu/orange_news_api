package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.avos.avoscloud.AVException;
import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.service.impl.IAppTopicsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class AppTopicsCloud {
	private static Logger logger = LogManager.getLogger(AppTopicsCloud.class.getName());

	@EngineFunction("queryCountryTopics")
	public static List<AppTopics> queryCountryTopics(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		return new IAppTopicsServiceImpl().getAppTopicsByCode(countryCode);
	}
}
