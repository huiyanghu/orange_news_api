package com.it7890.orange.api.dao;

import com.avos.avoscloud.*;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;
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

	public void updateAVObject(AVObject avObject) {
		if (null != avObject && StringUtil.isNotEmpty(avObject.getObjectId())) {
			try {
				avObject.save();
			} catch (AVException e) {
				e.printStackTrace();
			}
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
		if (StringUtils.isNotBlank(userId)){
			avQuery.whereEqualTo("userObj",AVObject.createWithoutData("_User",userId));
		}else if(StringUtils.isNotBlank(imei)){
			avQuery.whereEqualTo("imei",imei);
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

	public List<AVObject> getFavList(AVUser user,String imei,long createTime){
		List<AVObject> list =  new ArrayList<>();
		AVQuery avQueryUserLikeFavorite = new AVQuery("UserLikeFavorite");
		avQueryUserLikeFavorite.whereEqualTo("status", 0);
		if (user != null) {
			avQueryUserLikeFavorite.whereEqualTo("userObj", AVObject.createWithoutData("_User", user.getObjectId()));
		}
		avQueryUserLikeFavorite.whereEqualTo("imei", imei);
		avQueryUserLikeFavorite.whereEqualTo("lType", 2);
		if (createTime != 0) {
//            avQueryUserLikeFavorite.whereLessThan("createdAt", DateUtil.Long2StringUTC(createTime, DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1));
			avQueryUserLikeFavorite.whereLessThan("createdAt", DateUtil.long2Date(createTime-1000));
		}
		avQueryUserLikeFavorite.addDescendingOrder("createdAt");
		avQueryUserLikeFavorite.include("articleObj");
		avQueryUserLikeFavorite.include("articleObj.titlePicObjArr");
		avQueryUserLikeFavorite.include("articleObj.publicationObj");
		avQueryUserLikeFavorite.include("articleObj.topicObj");
		avQueryUserLikeFavorite.limit(20);
		try {
			list = avQueryUserLikeFavorite.find();
		} catch (AVException e) {
			e.printStackTrace();
		}
		return list;
	}
}
