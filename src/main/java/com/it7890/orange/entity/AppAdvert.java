package com.it7890.orange.entity;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/5/11.
 */
@AVClassName("AppAdvert")
public class AppAdvert extends AVObject{


	public AppAdvert() {
		super();
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getAdType() {
		return getString("adType");
	}
	public void setAdType(String adType) {
		this.put("adType", adType);
	}

	public String getAdName() {
		return getString("adName");
	}
	public void setAdName(String adName) {
		this.put("adName", adName);
	}

	public String getAdDesc() {
		return getString("adDesc");
	}
	public void setAdDesc(String adDesc) {
		this.put("adDesc", adDesc);
	}

	public int getStatus() {
		return getInt("status");
	}
	public void setAdDesc(int status) {
		this.put("status", status);
	}

	public String getAppId() {
		return getString("appid");
	}
	public void setAppId(String appId) {
		this.put("appId", appId);
	}

	public String getModeId() {
		return getString("modeId");
	}
	public void setModeId(String modeId) {
		this.put("modeId", modeId);
	}

	public double getDivideprice() {
		return getDouble("dividePrice");
	}
	public void setDivideprice(double dividePrice) {
		this.put("dividePrice", dividePrice);
	}

	public String getJointId() {
		return getString("jointId");
	}
	public void setJointId(double jointId) {
		this.put("jointId", jointId);
	}

	public int getStarLv() {
		return getInt("starLv");
	}
	public void setStarLv(int starLv) {
		this.put("starLv", starLv);
	}

	public String getJointCom() {
		return getString("jointCom");
	}
	public void setJointCom(String jointCom) {
		this.put("jointCom", jointCom);
	}

	public int getConType() {
		return getInt("conType");
	}
	public void setConType(int conType) {
		this.put("conType", conType);
	}

	public int getJointType() {
		return getInt("jointType");
	}
	public void getJointType(int jointType) {
		this.put("jointType", jointType);
	}

	public String getAdUrl() {
		return getString("adUrl");
	}
	public void setAdUrl(String adUrl) {
		this.put("adUrl", adUrl);
	}

	public String getAdImg() {
		return getString("adImg");
	}
	public void setAdImg(String adImg) {
		this.put("adImg", adImg);
	}

	public String getAdTitle() {
		return getString("adTitle");
	}
	public void setAdTitle(String adTitle) {
		this.put("adTitle", adTitle);
	}

	public String getAdPackage() {
		return getString("adPackage");
	}
	public void setAdPackage(String adPackage) {
		this.put("adPackage", adPackage);
	}

	public String getAdIcon() {
		return getString("adIcon");
	}
	public void setAdIcon(String adIcon) {
		this.put("adIcon", adIcon);
	}

	public String getAdApk() {
		return getString("adApk");
	}
	public void setAdApk(String adApk) {
		this.put("adApk", adApk);
	}

	public String getAdContent() {
		return getString("adContent");
	}
	public void setAdContent(String adContent) {
		this.put("adContent", adContent);
	}

	public String getCountryCode() {
		return getString("countryCode");
	}
	public void setCountryCode(String countryCode) {
		this.put("countryCode", countryCode);
	}

	public String getLangId() {
		return getString("langId");
	}
	public void setLangId(String langId) {
		this.put("langId", langId);
	}

	public int getUrlType() {
		return getInt("urlType");
	}
	public void setUrlType(int urlType) {
		this.put("urlType", urlType);
	}

	public int getAdApkSize() {
		return getInt("adApkSize");
	}
	public void setAdApkSize(int adApkSize) {
		this.put("adApkSize", adApkSize);
	}

	public Date getStartTime() {
		return getDate("startTime");
	}
	public void setStartTime(Date startTime) {
		this.put("startTime", startTime);
	}

	public Date getEndTime() {
		return getDate("endTime");
	}
	public void setEndTime(Date endTime) {
		this.put("endTime", endTime);
	}

	@Override
	public String toString() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("objectId", this.getObjectId());
		result.put("adApk", this.getString("adApk"));
		result.put("adContent", this.getString("adContent"));
		result.put("adDesc", this.getString("adDesc"));
		result.put("adIcon", this.getString("adIcon"));
		result.put("adImg", this.getString("adImg"));
		result.put("adName", this.getString("adName"));
		result.put("adPackage", this.getString("adPackage"));
		result.put("adTitle", this.getString("adTitle"));
		result.put("adUrl", this.getString("adUrl"));
		result.put("appId", this.getString("appId"));
		result.put("countryCode", this.getString("countryCode"));
		result.put("langId", this.getString("langId"));
		result.put("modelId", this.getString("modelId"));
		result.put("adApkSize", this.getInt("adapksize"));
		result.put("adType", this.getInt("adType"));
		result.put("conType", this.getInt("conType"));
		result.put("countryCode", this.getInt("countryCode"));
		result.put("jointType", this.getInt("jointType"));
		result.put("urlType", this.getInt("urlType"));
		result.put("starLv", this.getInt("starLv"));
		result.put("status", this.getInt("status"));

		return JSON.toJSONString(result);
	}
}
