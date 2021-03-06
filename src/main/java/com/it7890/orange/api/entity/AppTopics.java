package com.it7890.orange.api.entity;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	public String getCountryId() {
		return getString("countryId");
	}

	public void setCountryid(String countryId) {
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
	public int getChannelId() {
		return getInt("channelId");
	}

	public void setChannelId(int channelId) {
		this.put("channelId",channelId);
	}
//
//
//	@Override
//	public String toString() {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("hubiId", this.getString("hubiId"));
//		result.put("topicType", this.getString("topicType"));
//		result.put("keyWords", this.getString("keyWords"));
//		result.put("topicIcon", this.getString("topicIcon"));
//		result.put("rank", this.getString("rank"));
//		result.put("topicName", this.getString("topicName"));
//		result.put("countryId", this.getString("countryId"));
//		result.put("topicId", this.getInt("topicId"));
//		result.put("createuId", this.getInt("createuId"));
//		return JSON.toJSONString(result);
//	}

}
