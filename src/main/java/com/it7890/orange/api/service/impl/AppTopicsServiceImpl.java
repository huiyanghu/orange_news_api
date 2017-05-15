package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.dao.AppTopicsDao;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.service.IAppTopicsService;

import java.util.List;


public class AppTopicsServiceImpl implements IAppTopicsService {

	@Override
	public List<AppTopics> getAppTopicsById(String Id) {
		return new AppTopicsDao().getAppTopicsListByCountryId(Id);
	}
}
