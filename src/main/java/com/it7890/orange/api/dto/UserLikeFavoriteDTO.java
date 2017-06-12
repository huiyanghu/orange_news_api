package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.util.DateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserLikeFavoriteDTO{
	private String id;
	private String objId;
	private String userId;
	private int lType;//1:like喜欢 2:favorite收藏
	private String createTime;
	private int status;//0:正常 -1:删除
	private long createDate;
	private String imei;
	private int synTmp;

	private String title;
	private List titlePicList;
	private String sourceUrl;
	private String publicationName;


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

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getSynTmp() {
		return synTmp;
	}

	public void setSynTmp(int synTmp) {
		this.synTmp = synTmp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List getTitlePicList() {
		return titlePicList;
	}

	public void setTitlePicList(List titlePicList) {
		this.titlePicList = titlePicList;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
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

	public static UserLikeFavoriteDTO avobjectToDto(AVObject tmp) {
		UserLikeFavoriteDTO userLikeFavoriteDTO = null;
		if(null != tmp) {
			userLikeFavoriteDTO = new UserLikeFavoriteDTO();
			userLikeFavoriteDTO.setId(tmp.getObjectId());
			userLikeFavoriteDTO.setCreateDate(tmp.getCreatedAt().getTime());
			userLikeFavoriteDTO.setlType(tmp.getInt("lType"));
			userLikeFavoriteDTO.setStatus(tmp.getInt("status"));
			if(tmp.getAVObject("articleObj")!=null){
				userLikeFavoriteDTO.setObjId(tmp.getAVObject("articleObj").getObjectId());
				userLikeFavoriteDTO.setTitle(tmp.getAVObject("articleObj").getString("title"));
				userLikeFavoriteDTO.setSourceUrl(tmp.getAVObject("articleObj").getString("sourceUrl"));

				List<ImageInfoDTO> titlePicInfo = new ArrayList<ImageInfoDTO>();
				List<AVFile> titlePics = tmp.getAVObject("articleObj").getList("titlePicObjArr");
				if (titlePics != null) {
					ImageInfoDTO imageInfoDTO = null;
					for (AVFile titlePic : titlePics) {
						imageInfoDTO = new ImageInfoDTO();
						AVQuery<AVObject> query = new AVQuery<AVObject>("MediaInfo");
						query.whereEqualTo("fileObj", AVObject.createWithoutData("_File", titlePic.getObjectId()));
						List<AVObject> l = null;
						try {
							l = query.find();
						} catch (AVException e) {
							e.printStackTrace();
						}
						imageInfoDTO.setImageUrl(titlePic.getUrl());
						imageInfoDTO.setImageWidth(l.get(0).getInt("width"));
						imageInfoDTO.setImageHeight(l.get(0).getInt("height"));
						titlePicInfo.add(imageInfoDTO);
					}
				}
				userLikeFavoriteDTO.setTitlePicList(titlePicInfo);
				if(tmp.getAVObject("articleObj").getAVObject("publicationObj")!=null){
					userLikeFavoriteDTO.setPublicationName(tmp.getAVObject("articleObj").getAVObject("publicationObj").getString("name"));
				}
			}
			if(tmp.getAVObject("userObj")!=null){
				userLikeFavoriteDTO.setUserId(tmp.getAVObject("userObj").getObjectId());
			}
			userLikeFavoriteDTO.setImei(tmp.getString("imei"));
			userLikeFavoriteDTO.setSynTmp(tmp.getInt("synTmp"));
		}
		return userLikeFavoriteDTO;
	}
}
