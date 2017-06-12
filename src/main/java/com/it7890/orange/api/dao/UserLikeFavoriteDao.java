package com.it7890.orange.api.dao;

import com.avos.avoscloud.*;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.UserLikeFavorite;
import org.apache.commons.lang.StringUtils;
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

	public void saveAVObj(AVObject avObject){
		try {
			avObject.save();
		} catch (AVException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<AVObject> getLikeStatusByArtIdAndImei(int lType,String artId,String imei){
		AVUser avUser = AVUser.getCurrentUser();
		String userId = "";
		List<AVObject> ls = new ArrayList<>();
		if(avUser!=null){
			userId=avUser.getObjectId();
		}
		AVQuery avQuery = new AVQuery("UserLikeFavorite");
		if(StringUtils.isNotBlank(imei)){
			avQuery.whereEqualTo("imei",imei);
		}
		if (StringUtils.isNotBlank(userId)){
			avQuery.whereEqualTo("userObj",AVObject.createWithoutData("_User",userId));
		}
		avQuery.whereEqualTo("lType",lType);
		if (lType==2){
			avQuery.whereEqualTo("status",0);
		}
		avQuery.whereEqualTo("articleObj",AVObject.createWithoutData("conarticle",artId));

		try {
			ls = avQuery.find();
		} catch (AVException e) {
			e.printStackTrace();
		}
		return ls;
	}
}
