package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.HbCountrys;

import java.util.List;


public interface IHbCountrysService {

	HbCountrys getCountryByCountryCode(String countryCode);

	List<AVObject> findCountryList();
}
