package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.avos.avoscloud.AVException;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.AppTopicsServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class AppTopicsCloud {
	private static Logger logger = LogManager.getLogger(AppTopicsCloud.class.getName());


	HbCountrysDao hbCountrysDao;
	@EngineFunction("queryCountryTopics")
	public List<AppTopics> queryCountryTopics(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		List<HbCountrys> hs = new HbCountrysServiceImpl().getcsByCode(countryCode);
		return new AppTopicsServiceImpl().getAppTopicsById(hs.get(0).getObjectId());
	}
}
