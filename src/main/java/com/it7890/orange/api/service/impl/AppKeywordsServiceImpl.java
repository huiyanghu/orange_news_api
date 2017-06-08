package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.AppKeywordsDao;
import com.it7890.orange.api.service.IAppKeywordsService;

import java.util.List;

/**
 * Created by Astro on 17/6/8.
 */
public class AppKeywordsServiceImpl implements IAppKeywordsService {

	@Override
	public List<AVObject> findKeywordsByCountryId(String countryId) {
		return new AppKeywordsDao().findKeywordsByCountryId(countryId);
	}

	@Override
	public List<AVObject> findAllKeywords() {
		return new AppKeywordsDao().findAllKeywords();
	}
}
