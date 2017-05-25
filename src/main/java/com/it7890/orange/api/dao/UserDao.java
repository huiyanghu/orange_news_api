package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.util.StringUtil;

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
}
