package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import java.util.List;

/**
 * Created by Astro on 17/5/26.
 */
public class HbLanguagesDao {

	public List<AVObject> findLanguageInfoList() {
		List<AVObject> languageList = null;
		try {
			AVCloudQueryResult queryResult = AVQuery.doCloudQuery("select * from hb_languages where status = 1 order by createdAt limit 1000");
			languageList = (List<AVObject>) queryResult.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return languageList;
	}
}
