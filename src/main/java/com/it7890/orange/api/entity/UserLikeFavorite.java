package com.it7890.orange.api.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("UserLikeFavorite")
public class UserLikeFavorite extends AVObject{

	public AVObject getArticleObj() {
		return getAVObject("articleObj");
	}

	public void setArticleObj(AVObject articleObj) {
		this.put("articleObj",articleObj);
	}

	public AVObject getUserObj() {
		return getAVObject("userObj");
	}

	public void setUserObj(AVObject userObj) {
		this.put("userObj",userObj);
	}

	public int getLType() {
		return getInt("lType");
	}

	public void setLType(int lType) {
		this.put("lType",lType);
	}

	public int getStatus() {
		return getInt("status");
	}

	public void setStatus(int status) {
		this.put("status",status);
	}
}
