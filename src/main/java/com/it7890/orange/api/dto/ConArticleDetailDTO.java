package com.it7890.orange.api.dto;

import com.it7890.orange.api.entity.ConArticlesContent;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.sql.Timestamp;

public class ConArticleDetailDTO {
	private static Logger logger = LogManager.getLogger(ConArticleDetailDTO.class);
	private String title;//标题
	private String country;//国家
	private String excerpt;//摘要
	private String copyright;//来源
	private String keywords;//检索词
	private String category;//目录
	private String contentBody;//文章内容
	private String pubTime;//发布时间

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

	public static ConArticleDetailDTO objectToDto(ConArticlesContent tmp) throws IOException {
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
			conArticleDetailDTO.setCountry(tmp.getArticleObj().getString("countrycode"));
		}
		return conArticleDetailDTO;
	}
}
