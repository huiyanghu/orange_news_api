package com.it7890.orange.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/5/26.
 */
public class LanguageDTO {

	private String objectId;
	private String name;
	private String code;
	private List<String> keywords = new ArrayList<>();
	private List<AppTopicsDTO> topicList = new ArrayList<>();

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<AppTopicsDTO> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<AppTopicsDTO> topicList) {
		this.topicList = topicList;
	}
}
