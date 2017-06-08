package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppKeywords;
import com.it7890.orange.api.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/6/8.
 */
public class AppKeywordsDao {

	/**
	 * 国家关联关键字
	 * @param countryId 国家id
	 * @return
	 */
	public List<AVObject> findKeywordsByCountryId(String countryId) {
		List<AVObject> keywords = new ArrayList<>();
		if (StringUtil.isNotEmpty(countryId)) {
			String cql = "select * from AppKeywords where countryObj = pointer('hb_countrys', ?) order by createdAt desc limit 100";
			try {
				AVCloudQueryResult queryResult = AVQuery.doCloudQuery(cql, AppKeywords.class, countryId);
				keywords = (List<AVObject>) queryResult.getResults();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return keywords;
	}

	/**
	 * 所有关键字列表
	 * @return
	 */
	public List<AVObject> findAllKeywords() {
		List<AVObject> keywords = new ArrayList<>();
		String cql = "select * from AppKeywords order by createdAt desc limit 1000";
		try {
			AVCloudQueryResult queryResult = AVQuery.doCloudQuery(cql);
			keywords = (List<AVObject>) queryResult.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keywords;
	}
}
