package com.it7890.orange.api.service;

import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.HbCountrys;

import java.util.List;


public interface IHbCountrysService {
	public List<HbCountrys> getcsByCode(String Code);
}
