package com.it7890.orange.api.dto;

/**
 * Created by Astro on 17/5/31.
 */
public class UserDTO {

	private String objectId;
	private String username;
	private String email;
	private String avatarUrl;
	private String nickName;
	private int sex;
	private int loadSign;
	private boolean emailBind;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getLoadSign() {
		return loadSign;
	}

	public void setLoadSign(int loadSign) {
		this.loadSign = loadSign;
	}

	public boolean isEmailBind() {
		return emailBind;
	}

	public void setEmailBind(boolean emailBind) {
		this.emailBind = emailBind;
	}
}
