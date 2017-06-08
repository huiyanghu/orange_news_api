package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserCommentDao {
	private static Logger logger = LogManager.getLogger(UserCommentDao.class);

	public List<AVObject> getArtCommentList(String artId,long createTime) {
		List<AVObject> artCommentList = new ArrayList<AVObject>();
		StringBuffer cql = new StringBuffer();
		cql.append("select * from UserComment where articleObj = pointer('conarticle', ?)");
		try {
			if(createTime != 0){
				cql.append(" and createdAt < date(?) and status = 0 limit 20 order by createdAt desc");
				String createdAt =DateUtil.Long2StringUTC(createTime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1);
				AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(),AVObject.class,artId,createdAt);
				artCommentList = (List<AVObject>) avCloudQueryResult.getResults();
			}else {
				cql.append(" and status = 0 limit 20 order by createdAt desc");
				AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(),AVObject.class,artId);
				artCommentList = (List<AVObject>) avCloudQueryResult.getResults();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return artCommentList;
	}

	public List<AVObject> getArtCommentList1(String artId,long createTime) {
		List<AVObject> artCommentList = new ArrayList<AVObject>();
		AVQuery avQuery = new AVQuery("UserComment");
		avQuery.include("userObj");
		avQuery.whereEqualTo("articleObj",AVObject.createWithoutData("conarticle",artId));
		avQuery.whereEqualTo("status",0);
		avQuery.limit(20);
		avQuery.addDescendingOrder("createdAt");
		if (createTime!=0){
			Date date = DateUtil.long2Date(createTime);
			logger.info("date time====>"+date);
			avQuery.whereLessThan("createdAt",date);
		}
		try {
			artCommentList = avQuery.find();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return artCommentList;
	}

	public void saveObj(AVObject obj){
		try {
			obj.save();
		} catch (AVException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
