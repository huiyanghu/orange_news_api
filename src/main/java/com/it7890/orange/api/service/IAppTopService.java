package com.it7890.orange.api.service;

import com.it7890.orange.api.dto.AppTopDTO;
import com.it7890.orange.api.entity.AppTop;
import com.it7890.orange.api.entity.AppTopics;

import java.text.ParseException;
import java.util.List;


public interface IAppTopService {
	public List<AppTopDTO> getAppTopsList(String countryCode,String topCreateTime) throws ParseException;
}
