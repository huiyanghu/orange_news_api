package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppTopics;
import java.util.ArrayList;
import java.util.List;




public class AppTopicsDao {

	public List<AppTopics> getAppTopicsListByCountryId(String Id) {
//		List<AppTopics> l = new AVQuery("AppTopics").whereEqualTo("countryObjArr", [new Object(),]).find();
		List<AppTopics> topicsList = new ArrayList<AppTopics>();
		if(Id != null && !"".equals(Id)) {
			String cql = " select * from AppTopics where countryObj = pointer('hb_countrys', ?)";
//			String cql = " select include countryObjArr, * from AppTopics where countryObj pointer('hb_countrys', ?)";
			try {
				AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql,AppTopics.class, Id);
				topicsList = (List<AppTopics>) avCloudQueryResult.getResults();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		return topicsList;
	}

	public List<AVObject> findAllTopicsList() {
		List<AVObject> topicsList = new ArrayList<>();
		String cql = "select include topicIconFile, * from AppTopics order by rank asc limit 1000";
		try {
			AVCloudQueryResult queryResult = AVQuery.doCloudQuery(cql);
			topicsList = (List<AVObject>) queryResult.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return topicsList;
	}
	
}
