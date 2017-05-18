package com.it7890.orange.api.dto;

import com.it7890.orange.api.entity.AppTop;
import com.it7890.orange.api.entity.AppTopics;

import java.util.Date;

public class AppTopDTO {

	private String objId;//对象ID 文章类型 为文章ID  竞猜类型为竞猜ID  广告类型 为广告ID

	private String abstracts;
	
	private String articleId;

	private String countryCode;

	private String title;

	private String titlePic;
	
	private int topicsId;

	private String langId;
	
	private String linkUrl;

	private int attr;//1=图文文章 0=文字文章 2=视频文章 3=链接文章 4=H5游戏文章 5=竞猜文章 6=游戏文章 7=竞猜  8=广告

	private String author;

	private int channelId;

	private int createuId;

	private int cType;

	private int imgCount;

	private String keyWords;

	private int latitude;

	private int longitude;

	private String mediaLink;
	
	private int publicationId;

	private int rank;

	private String source;

	private String sourceUrl;

	private int status;

	private int subuid;

	private String writer;

	private String sourceTitilePic;

	private int pushNum;

	private int commentNum;

	private int iType; // 类型 1文章 2 竞猜 3广告
	private Date createTime;

	public int getiType() {
		return iType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setiType(int iType) {
		this.iType = iType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}

	public int getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(int topicsId) {
		this.topicsId = topicsId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getCreateuId() {
		return createuId;
	}

	public void setCreateuId(int createuId) {
		this.createuId = createuId;
	}

	public int getcType() {
		return cType;
	}

	public void setcType(int cType) {
		this.cType = cType;
	}

	public int getImgCount() {
		return imgCount;
	}

	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSubuid() {
		return subuid;
	}

	public void setSubuid(int subuid) {
		this.subuid = subuid;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSourceTitilePic() {
		return sourceTitilePic;
	}

	public void setSourceTitilePic(String sourceTitilePic) {
		this.sourceTitilePic = sourceTitilePic;
	}

	public int getPushNum() {
		return pushNum;
	}

	public void setPushNum(int pushNum) {
		this.pushNum = pushNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public static AppTopDTO objectToDto(AppTop tmp) {
		AppTopDTO appTopDTO = null;
		if(null != tmp) {
			appTopDTO = new AppTopDTO();
			appTopDTO.setKeyWords(tmp.getKeyWords());
			appTopDTO.setCreateuId(tmp.getCreateuId());
			appTopDTO.setRank(tmp.getRank());
			appTopDTO.setAbstracts(tmp.getAbstracts());
			appTopDTO.setArticleId(tmp.getArticleId());
			appTopDTO.setAttr(tmp.getAttr());
			appTopDTO.setAuthor(tmp.getAuthor());
			appTopDTO.setChannelId(tmp.getChannelId());
			appTopDTO.setCommentNum(tmp.getCommentNum());
			appTopDTO.setCountryCode(tmp.getCountryCode());
			appTopDTO.setcType(tmp.getcType());
			appTopDTO.setImgCount(tmp.getImgCount());
			appTopDTO.setLangId(tmp.getLangId());
			appTopDTO.setLatitude(tmp.getLatitude());
			appTopDTO.setLongitude(tmp.getLongitude());
			appTopDTO.setLinkUrl(tmp.getLinkUrl());
			appTopDTO.setMediaLink(tmp.getMediaLink());
			appTopDTO.setObjId(tmp.getObjId());
			appTopDTO.setWriter(tmp.getWriter());
			appTopDTO.setTopicsId(tmp.getTopicsId());
			appTopDTO.setTitlePic(tmp.getTitlePic());
			appTopDTO.setSubuid(tmp.getSubuid());
			appTopDTO.setStatus(tmp.getStatus());
			appTopDTO.setSourceUrl(tmp.getSourceUrl());
			appTopDTO.setSourceTitilePic(tmp.getSourceTitilePic());
			appTopDTO.setPushNum(tmp.getPushNum());
			appTopDTO.setPublicationId(tmp.getPublicationId());
			appTopDTO.setCreateTime(tmp.getCreatedAt());
			appTopDTO.setiType(tmp.getiType());
		}
		return appTopDTO;
	}

}
