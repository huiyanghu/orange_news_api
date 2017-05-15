package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.service.AppAdvertService;
import com.it7890.orange.api.service.impl.AppAdvertServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertCloud {

	@Resource
	private AppAdvertService appAdvertService;
	@Resource
	private AppAdvertDao appAdvertDao;

	private static final Logger logger = LogManager.getLogger(AppAdvertCloud.class);

	/**
	 * 首页人文图
	 * @param countryCode 国家编码
	 * @return
	 * @throws AVException
	 */
	@EngineFunction("startImg")
	public static List<AppAdvert> startImg(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		logger.info("startImg args: " + countryCode);
		return new AppAdvertServiceImpl().findAppAdverListByCountryCode(countryCode);
	}
}
