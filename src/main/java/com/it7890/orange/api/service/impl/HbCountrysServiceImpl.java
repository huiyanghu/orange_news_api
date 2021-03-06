package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.IHbCountrysService;

import java.util.List;


public class HbCountrysServiceImpl implements IHbCountrysService {

	@Override
	public HbCountrys getCountryByCountryCode(String countryCode) {
		return new HbCountrysDao().getCountryByCountryCode(countryCode);
	}

	@Override
	public List<AVObject> findCountryList() {
		return new HbCountrysDao().findCountryList();
	}
}
