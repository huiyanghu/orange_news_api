package com.it7890.orange.api.dto;

import com.it7890.orange.api.entity.AppAdvert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/5/15.
 */
public class AppAdvertDto {

	private String objectId;
	private String adName;
	private String adTitle;
	private String adContent;
	private String adimg;
	private String countryCode;
	private String langId;
	private String modelId;
	private int urlType;
	private String adType;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public String getAdimg() {
		return adimg;
	}

	public void setAdimg(String adimg) {
		this.adimg = adimg;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}
}
