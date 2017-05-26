package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.entity.AppTop;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.util.DateUtil;

import java.util.Date;

public class AppTopDTO {

	private String objId;//对象ID 文章类型 为文章ID  竞猜类型为竞猜ID  广告类型 为广告ID

	private String abstracts;
	
	private String articleId;

	private String countryCode;

	private String title;

	private String titlePic;
	
	private String topicsId;

	private String langId;
	
	private String linkUrl;

	private int attr;//1=图文文章 0=文字文章 2=视频文章 3=链接文章 4=H5游戏文章 5=竞猜文章 6=游戏文章 7=竞猜  8=广告

	private String author;

	private String channelId;


	private int cType;

	private int imgCount;

	private String keyWords;

	private int latitude;

	private int longitude;

	private String mediaLink;
	
	private String publicationId;

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
	private String createTime;
	private long createDate;
	private String createrId;

	public int getiType() {
		return iType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

	public String getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(String topicsId) {
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(String publicationId) {
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

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public static AppTopDTO avoobjectToDto(AVObject tmp) {
		AppTopDTO appTopDTO = null;
		if(null != tmp) {
			appTopDTO = new AppTopDTO();
			appTopDTO.setKeyWords(tmp.getAVObject("aricleObj").getString("keyWords"));
			appTopDTO.setCreaterId(tmp.getAVObject("sysUserObj").getObjectId());
			appTopDTO.setRank(tmp.getInt("rank"));
			appTopDTO.setAbstracts(tmp.getAVObject("articleObj").getString("abstracts"));
			appTopDTO.setArticleId(tmp.getAVObject("articleObj").getObjectId());
			appTopDTO.setAttr(tmp.getAVObject("articleObj").getInt("attr"));
			appTopDTO.setAuthor(tmp.getAVObject("articleObj").getString("author"));
			appTopDTO.setChannelId(tmp.getAVObject("channelObj").getObjectId());
			appTopDTO.setCommentNum(tmp.getAVObject("articleObj").getInt("commentNum"));
			appTopDTO.setCountryCode(tmp.getString("countryCode"));
			appTopDTO.setcType(tmp.getInt("cType"));
			appTopDTO.setImgCount(tmp.getAVObject("articleObj").getInt("imgCount"));
			appTopDTO.setLangId(tmp.getAVObject("languagesObj").getObjectId());
			appTopDTO.setLatitude(tmp.getInt("latitude"));
			appTopDTO.setLongitude(tmp.getInt("longitude"));
			appTopDTO.setLinkUrl(tmp.getAVObject("articleObj").getString("linkUrl"));
			appTopDTO.setMediaLink(tmp.getAVObject("articleObj").getString("mediaLink"));
			appTopDTO.setWriter(tmp.getAVObject("articleObj").getString("writer"));
			appTopDTO.setTopicsId(tmp.getAVObject("topicsObj").getObjectId());
			appTopDTO.setTitlePic(tmp.getAVObject("articleObj").getString("titlePic"));
			appTopDTO.setStatus(tmp.getInt("status"));
			appTopDTO.setSourceUrl(tmp.getAVObject("articleObj").getString("souceUrl"));
			appTopDTO.setSourceTitilePic(tmp.getAVObject("articleObj").getString("sourceTitlePic"));
			appTopDTO.setPushNum(tmp.getInt("pushNum"));
			appTopDTO.setPublicationId(tmp.getAVObject("publicationObj").getObjectId());
			appTopDTO.setCreateTime(DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS,tmp.getCreatedAt()));
			appTopDTO.setiType(tmp.getInt("iType"));
		}
		return appTopDTO;
	}

}
