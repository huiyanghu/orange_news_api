package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppAdvert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertDao {

	public List<AppAdvert> findAppAdverListByCountryCode(String countryCode) {
		List<AppAdvert> adverList = new ArrayList<AppAdvert>();
		if(countryCode != null && !"".equals(countryCode)) {
			String cql = " select * from AppAdvert where countryCode = ?";
			try {
				AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, countryCode);
				adverList = (List<AppAdvert>) avCloudQueryResult.getResults();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return adverList;
	}
}
