package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.IHbCountrysService;

import java.util.List;


public class HbCountrysServiceImpl implements IHbCountrysService {

	@Override
	public List<HbCountrys> getcsByCode(String Code) {
		return new HbCountrysDao().getCountryByCountryCode(Code);
	}
}
