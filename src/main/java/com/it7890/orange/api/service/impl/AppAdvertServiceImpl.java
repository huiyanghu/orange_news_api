package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.cloud.AppAdvertCloud;
import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.service.AppAdvertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertServiceImpl implements AppAdvertService {

	@Resource
	public AppAdvertDao appAdvertDao;

	private static final Logger logger = LogManager.getLogger(AppAdvertServiceImpl.class);

	public List<AppAdvert> findAppAdverListByCountryCode(String countryCode) {
		return new AppAdvertDao().findAppAdverListByCountryCode(countryCode);
	}
}
