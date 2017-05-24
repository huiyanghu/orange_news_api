package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/5/24.
 */
public class TestCloud {

	private static final Logger logger = LogManager.getLogger(AppAdvertCloud.class);

	@EngineFunction("updateTitlePic")
	public static String updateTitlePic() {
		String cql = " select * from conarticle limit 1000";
		try {
			AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql);
			List<AVObject> articleList = (List<AVObject>) avCloudQueryResult.getResults();
			logger.debug("article count: {}", articleList.size());

			List<AVObject> titlePics;
			for (AVObject articleInfo : articleList) {
				if (null != articleInfo.getAVObject("titlePicObj")) {
					titlePics = new ArrayList<>();
					titlePics.add(articleInfo.getAVObject("titlePicObj"));
					articleInfo.put("titlePicObjArr", titlePics);
				}
			}
			AVObject.saveAll(articleList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
