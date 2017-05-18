package com.it7890.orange.api.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("AppTop")
public class AppTop extends AVObject {

	public String getObjId() {
		return getString("objId");
	}

	public void setObjId(String objId) {
		this.put("objId",objId);
	}

	public String getAbstracts() {
		return getString("abstracts");
	}

	public void setAbstracts(String abstracts) {
		this.put("abstracts",abstracts);
	}

	public String getArticleId() {
		return getString("articleId");
	}

	public void setArticleId(String articleId) {
		this.put("articleId",articleId);
	}

	public String getCountryCode() {
		return getString("countryCode");
	}

	public void setCountryCode(String countryCode) {
		this.put("countryCode",countryCode);
	}

	public String getTitle() {
		return getString("title");
	}

	public void setTitle(String title) {
		this.put("title",title);
	}

	public String getTitlePic() {
		return getString("titlePic");
	}

	public void setTitlePic(String titlePic) {
		this.put("titlePic",titlePic);
	}

	public int getTopicsId() {
		return  getInt("topicsId");
	}

	public void setTopicsId(int topicsId) {
		this.put("topicsId",topicsId);
	}

	public String getLangId() {
		return getString("langId");
	}

	public void setLangId(String langId) {
		this.put("langId",langId);
	}

	public String getLinkUrl() {
		return getString("linkUrl");
	}

	public void setLinkUrl(String linkUrl) {
		this.put("linkUrl",linkUrl);
	}

	public int getAttr() {
		return getInt("attr");
	}

	public void setAttr(int attr) {
		this.put("attr",attr);
	}

	public String getAuthor() {
		return getString("author");
	}

	public void setAuthor(String author) {
		this.put("author",author);
	}

	public int getChannelId() {
		return getInt("channelId");
	}

	public void setChannelId(int channelId) {
		this.put("channelId",channelId);
	}

	public int getCreateuId() {
		return getInt("createuId");
	}

	public void setCreateuId(int createuId) {
		this.put("createuId",createuId);
	}

	public int getcType() {
		return getInt("cType");
	}

	public void setcType(int cType) {
		this.put("cType",cType);
	}

	public int getImgCount() {
		return getInt("imgCount");
	}

	public void setImgCount(int imgCount) {
		this.put("imgCount",imgCount);
	}

	public String getKeyWords() {
		return getString("keyWords");
	}

	public void setKeyWords(String keyWords) {
		this.put("keyWords",keyWords);
	}

	public int getLatitude() {
		return getInt("latitude");
	}

	public void setLatitude(int latitude) {
		this.put("latitude",latitude);
	}

	public int getLongitude() {
		return getInt("longitude");
	}

	public void setLongitude(int longitude) {
		this.put("longitude",longitude);
	}

	public String getMediaLink() {
		return getString("mediaLink");
	}

	public void setMediaLink(String mediaLink) {
		this.put("mediaLink",mediaLink);
	}

	public int getPublicationId() {
		return getInt("publicationId");
	}

	public void setPublicationId(int publicationId) {
		this.put("publicationId",publicationId);
	}

	public int getRank() {
		return getInt("rank");
	}

	public void setRank(int rank) {
		this.put("rank",rank);
	}

	public String getSource() {
		return getString("source");
	}

	public void setSource(String source) {
		this.put("source",source);
	}

	public String getSourceUrl() {
		return getString("sourceUrl");
	}

	public void setSourceUrl(String sourceUrl) {
		this.put("sourceUrl",sourceUrl);
	}

	public int getStatus() {
		return getInt("status");
	}

	public void setStatus(int status) {
		this.put("status",status);
	}

	public int getSubuid() {
		return getInt("subuid");
	}

	public void setSubuid(int subuid) {
		this.put("subuid",subuid);
	}

	public String getWriter() {
		return getString("writer");
	}

	public void setWriter(String writer) {
		this.put("writer",writer);
	}

	public String getSourceTitilePic() {
		return getString("sourceTitilePic");
	}

	public void setSourceTitilePic(String sourceTitilePic) {
		this.put("sourceTitilePic",sourceTitilePic);
	}

	public int getPushNum() {
		return getInt("pushNum");
	}

	public void setPushNum(int pushNum) {
		this.put("pushNum",pushNum);
	}

	public int getCommentNum() {
		return getInt("commentNum");
	}

	public void setCommentNum(int commentNum) {
		this.put("commentNum",commentNum);
	}
	public int getiType() {
		return getInt("iType");
	}

	public void setiType(int iType) {
		this.put("iType",iType);
	}
}
