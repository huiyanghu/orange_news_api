package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.dao.MediaInfoDao;
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
	private String publicationId;
	private int viewCount;
	private int imgCount;
	private String writer;
	private String topicId;
	private String countryCode;


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

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}

	public int getImgCount() {
		return imgCount;
	}

	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
				userLikeFavoriteDTO.setSourceUrl(tmp.getAVObject("articleObj").getString("sourceurl"));

				List<AVFile> titlePics = tmp.getAVObject("articleObj").getList("titlePicObjArr");
				if (titlePics != null) {
					userLikeFavoriteDTO.setTitlePicList(ImageInfoDTO.buildImageInfoDTO(titlePics));
				}

				if(tmp.getAVObject("articleObj").getAVObject("publicationObj")!=null){
					userLikeFavoriteDTO.setPublicationName(tmp.getAVObject("articleObj").getAVObject("publicationObj").getString("name"));
					userLikeFavoriteDTO.setPublicationId(tmp.getAVObject("articleObj").getAVObject("publicationObj").getObjectId());
				}
				userLikeFavoriteDTO.setViewCount(tmp.getAVObject("articleObj").getInt("viewCount"));
				userLikeFavoriteDTO.setCountryCode(tmp.getAVObject("articleObj").getString("countrycode"));
				if(tmp.getAVObject("articleObj").getAVObject("topicObj")!=null){
					userLikeFavoriteDTO.setTopicId(tmp.getAVObject("articleObj").getAVObject("topicObj").getObjectId());
				}
			}
			if(tmp.getAVObject("userObj")!=null){
				userLikeFavoriteDTO.setUserId(tmp.getAVObject("userObj").getObjectId());
			}
			userLikeFavoriteDTO.setImei(tmp.getString("imei"));
			userLikeFavoriteDTO.setSynTmp(tmp.getInt("synTmp"));
			userLikeFavoriteDTO.setCreateTime(DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS,tmp.getCreatedAt().getTime()));
			userLikeFavoriteDTO.setImgCount(tmp.getAVObject("articleObj").getInt("imgcount"));
			userLikeFavoriteDTO.setWriter(tmp.getAVObject("articleObj").getString("writer"));
		}
		return userLikeFavoriteDTO;
	}
}
