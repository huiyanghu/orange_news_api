package com.it7890.orange.api.dao;

import com.avos.avoscloud.*;
import com.it7890.orange.api.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/5/24.
 */
public class UserDao {

	/***
	 * 用户名是否已存在
	 * @param username 用户名
	 * @return
	 */
	public boolean getIsExistUsername(String username) {
		boolean isExist = true;
		if (StringUtil.isNotEmpty(username)) {
			String cql = "select count(*) from _User where username = ?";
			AVCloudQueryResult result = null;
			try {
				result = AVQuery.doCloudQuery(cql, username);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != result && result.getCount() == 0) {
				isExist = false;
			}
		}
		return isExist;
	}

	/**
	 * 邮箱是否存在
	 * @param email
	 * @return
	 */
	public boolean getIsExistEmail(String email) {
		boolean isExist = true;
		if (StringUtil.isNotEmpty(email)) {
			String cql = "select count(*) from _User where email = ?";
			AVCloudQueryResult result = null;
			try {
				result = AVQuery.doCloudQuery(cql, email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != result && result.getCount() == 0) {
				isExist = false;
			}
		}
		return isExist;
	}
	/**
	 * 邮箱是否被绑定
	 * @param email
	 * @return
	 */
	public boolean getIsBindEmail(String email) {
		List<AVObject> ls = new ArrayList<>();
		boolean isBind = false;
		AVQuery avQuery = new AVQuery("_User");
		avQuery.whereEqualTo("email",email);
		try {
			ls = avQuery.find();
			if (ls!=null&&ls.get(0).getBoolean("emailVerified")==true){
				isBind = true;
			}
		} catch (AVException e) {
			e.printStackTrace();
		}
		return isBind;
	}

	/**
	 * 修改用户信息
	 * @param userInfo 用户对象
	 * @return
	 */
	public AVUser updateUserInfo(AVUser userInfo) {
		if (null != userInfo && StringUtil.isNotEmpty(userInfo.getObjectId())) {
			try {
				userInfo.save();
			} catch (AVException e) {
				e.printStackTrace();
				userInfo = null;
			}
		}
		return userInfo;
	}
}
