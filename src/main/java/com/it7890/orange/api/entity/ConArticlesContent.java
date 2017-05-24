package com.it7890.orange.api.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.io.Serializable;



@AVClassName("con_articles_content")
public class ConArticlesContent extends AVObject {

	public int getStatus() {
		return getInt("status");
	}

	public void setStatus(int status) {
		this.put("status",status);
	}

	public String getArticleid() {
		return getString("articleid");
	}

	public void setArticleid(String articleid) {
		this.put("articleid",articleid);
	}

	public String getContent() {
		return getString("content");
	}

	public void setContent(String content) {
		this.put("content",content);
	}

	public AVObject getArticleObj(){
		return getAVObject("articleObj");
	}
	public void setArticleObj(AVObject articleObj){
		this.put("articleObj",articleObj);
	}

	public AVObject getTopicObj(){
		return getAVObject("articleObj").getAVObject("topicObj");
	}
	public void setTopicObj(AVObject topicObj){
		this.put("topicObj",topicObj);
	}

}