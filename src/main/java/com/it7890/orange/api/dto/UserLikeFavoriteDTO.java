package com.it7890.orange.api.dto;

import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.util.DateUtil;

import java.io.Serializable;

public class UserLikeFavoriteDTO{
	private String id;
	private String objId;
	private String userId;
	private int lType;//1:like喜欢 2:favorite收藏
	private String createTime;
	private int status;//0:正常 -1:删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getlType() {
		return lType;
	}

	public void setlType(int lType) {
		this.lType = lType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static UserLikeFavoriteDTO objectToDto(UserLikeFavorite tmp) {
		UserLikeFavoriteDTO userLikeFavoriteDTO = null;
		if(null != tmp) {
			userLikeFavoriteDTO = new UserLikeFavoriteDTO();
			userLikeFavoriteDTO.setId(tmp.getObjectId());
			userLikeFavoriteDTO.setCreateTime(DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS,tmp.getCreatedAt()));
			userLikeFavoriteDTO.setlType(tmp.getLType());
			userLikeFavoriteDTO.setStatus(tmp.getStatus());
			userLikeFavoriteDTO.setObjId(tmp.getArticleObj().getObjectId());
			userLikeFavoriteDTO.setUserId(tmp.getUserObj().getObjectId());
		}
		return userLikeFavoriteDTO;
	}
}
