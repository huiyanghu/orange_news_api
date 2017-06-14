package com.it7890.orange.api.dao;

import com.avos.avoscloud.*;
import com.it7890.orange.api.entity.UserLikeFavorite;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class UserRssPublicationDao {
	private static Logger logger = LogManager.getLogger(UserRssPublicationDao.class);

	public List<AVObject> getList(String pid,String imei) {
		List<AVObject> avos = new ArrayList<>();
		AVQuery avQuery = new AVQuery("UserRssPublication");
		avQuery.whereEqualTo("publicationObj",pid);
		avQuery.whereEqualTo("imei",imei);
		try {
			avos = avQuery.find();
		} catch (AVException e) {
			e.printStackTrace();
		}
		return avos;
	}

}
