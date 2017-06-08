package com.it7890.orange.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/5/26.
 */
public class CountryDTO {

	private String objectId;
	private String cnName;
	private String enName;
	private String iconUrl;
	private String languageId;
	private String countryCode;
	private String shortName;
	private List<String> keywords = new ArrayList<>();
	private List<AppTopicsDTO> topicList = new ArrayList<>();

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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
