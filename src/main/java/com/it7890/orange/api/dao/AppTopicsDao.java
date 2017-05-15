package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppTopics;
import java.util.ArrayList;
import java.util.List;




public class AppTopicsDao {

	public List<AppTopics> getAppTopicsListByCountryId(String Id) {
		List<AppTopics> topicsList = new ArrayList<AppTopics>();
		if(Id != null && !"".equals(Id)) {
			String cql = " select * from AppTopics where countryCode = ?";
			try {
				AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, Id);
				topicsList = (List<AppTopics>) avCloudQueryResult.getResults();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		return topicsList;
	}
	
	
}
