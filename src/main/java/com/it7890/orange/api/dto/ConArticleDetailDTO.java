package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConArticleDetailDTO {
	private static Logger logger = LogManager.getLogger(ConArticleDetailDTO.class);
	private String title;//标题
	private String country;//国家
	private String excerpt;//摘要
	private String copyright;//来源
	private String keywords;//检索词
	private String category;//目录
	private String categoryId;//话题Id
	private String contentBody;//文章内容
	private String pubTime;//发布时间
	private List titlePicList;//顶图
	private int likeCount;
	private int noLikeCount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContentBody() {
		return contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public List getTitlePicList() {
		return titlePicList;
	}

	public void setTitlePicList(List titlePicList) {
		this.titlePicList = titlePicList;
	}
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getNoLikeCount() {
		return noLikeCount;
	}

	public void setNoLikeCount(int noLikeCount) {
		this.noLikeCount = noLikeCount;
	}

	public static ConArticleDetailDTO objectToDto(ConArticlesContent tmp) throws IOException, AVException {
		ConArticleDetailDTO conArticleDetailDTO = null;
		if(null != tmp) {
			conArticleDetailDTO = new ConArticleDetailDTO();
			conArticleDetailDTO.setTitle(tmp.getArticleObj().getString("title"));
//			conArticleDetailDTO.setContentBody(tmp.getContent());
			BASE64Encoder encoder = new BASE64Encoder();

//			String enStr = encoder.encode(tmp.getContent().getBytes());
//			logger.info("enenenenenenen:::"+enStr);
//			BASE64Decoder decoder = new BASE64Decoder();
//			String ss = new String(decoder.decodeBuffer(enStr));
//			logger.info("ddededededede::::"+ss);
//			String str = new String(decoder.decodeBuffer(tmp.getContent()));
//			conArticleDetailDTO.setContentBody(StringEscapeUtils.unescapeHtml4(tmp.getContent()));

			conArticleDetailDTO.setContentBody(encoder.encode(tmp.getContent().getBytes()));
			conArticleDetailDTO.setCategory(tmp.getTopicObj().getString("topicName"));
			conArticleDetailDTO.setCategoryId(tmp.getTopicObj().getObjectId());
			conArticleDetailDTO.setCountry(tmp.getArticleObj().getString("countrycode"));
			conArticleDetailDTO.setPubTime(DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS,tmp.getArticleObj().getCreatedAt()));
			conArticleDetailDTO.setCopyright(tmp.getPubicationObj().getString("name"));

			List<ImageInfoDTO> titlePicInfo = new ArrayList<>();
			List<AVFile> titlePics = tmp.getArticleObj().getList("titlePicObjArr");
			if(titlePics!=null){
				ImageInfoDTO imageInfoDTO = null;
				for (AVFile titlePic : titlePics) {
					imageInfoDTO = new ImageInfoDTO();
					AVQuery<AVObject> query = new AVQuery<AVObject>("MediaInfo");
					query.whereEqualTo("fileObj",AVObject.createWithoutData("_File",titlePic.getObjectId()));
					List<AVObject> l = query.find();
					imageInfoDTO.setImageUrl(titlePic.getUrl());
					imageInfoDTO.setImageWidth(l.get(0).getInt("width"));
					imageInfoDTO.setImageHeight(l.get(0).getInt("height"));
					titlePicInfo.add(imageInfoDTO);
//					titlePicUrls.add(titlePic.getUrl());
				}
			}
			conArticleDetailDTO.setTitlePicList(titlePicInfo);
			conArticleDetailDTO.setLikeCount(tmp.getInt("likeCount"));
			conArticleDetailDTO.setNoLikeCount(tmp.getInt("noLikeCount"));
		}
		return conArticleDetailDTO;

	}
}
