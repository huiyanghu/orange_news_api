package com.it7890.orange.api.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

@AVClassName("AppTopics")
public class AppTopics extends AVObject {

	public String getHubiId() {
		return getString("hubiId");
	}

	public void setHubiId(String hubiId) {
		this.put("hubiId",hubiId);
	}

	public int getTopicType() {
		return getInt("topicType");
	}

	public void setTopicType(int topicType) {
		this.put("topicType",topicType);
	}

	public String getKeyWords() {
		return getString("keyWords");
	}

	public void setKeywords(String keyWords) {
		this.put("keyWords",keyWords);
	}

	public String getTopicIcon() {
		return getString("topicIcon");
	}

	public void setTopicIcon(String topicIcon) {
		this.put("topicIcon",topicIcon);
	}

	public int getRank() {
		return getInt("rank");
	}

	public void setRank(int rank) {
		this.put("rank",rank);
	}

	public String getTopicName() {
		return getString("topicName");
	}

	public void setTopicName(String topicName) {
		this.put("topicName",topicName);
	}

	public int getCountryId() {
		return getInt("countryId");
	}

	public void setCountryid(int countryId) {
		this.put("countryId",countryId);
	}

	public int getTopicId() {
		return getInt("topicId");
	}

	public void setTopicid(int topicId) {
		this.put("topicId",topicId);
	}

	public int getCreateuId() {
		return getInt("createuId");
	}

	public void setCreateuid(int createuId) {
		this.put("createuId",createuId);
	}

}
