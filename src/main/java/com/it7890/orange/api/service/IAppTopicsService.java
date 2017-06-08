package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.entity.AppTopics;

import java.util.List;


public interface IAppTopicsService {
	public List<AppTopics> getAppTopicsById(String Code);

	List<AVObject> findAllTopicsList();
}
