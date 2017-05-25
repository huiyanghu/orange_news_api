package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.UserLikeFavorite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class UserLikeFavoriteDao {
	private static Logger logger = LogManager.getLogger(UserLikeFavoriteDao.class);

	public List<UserLikeFavorite> getUserLFListByCountryId(int lType,String artId,String userId) {
		List<UserLikeFavorite> UserLFList = new ArrayList<UserLikeFavorite>();
		String cql = " select * from UserLikeFavorite where lType = ? and articleObj = pointer('conarticle', ?) and userObj = pointer('_User', ?) ";
		try {
			AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql,UserLikeFavorite.class, lType,artId,userId);
			UserLFList = (List<UserLikeFavorite>) avCloudQueryResult.getResults();
		} catch (Exception e) {
			e.getMessage();
		}

		return UserLFList;
	}

	public void saveObj(UserLikeFavorite obj){
		try {
			obj.save();
		} catch (AVException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
