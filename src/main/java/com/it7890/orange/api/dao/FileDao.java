package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Astro on 17/5/31.
 */
public class FileDao {

	private final static Logger logger = LogManager.getLogger(FileDao.class);
	/**
	 * 主键id是否存在
	 * @param objectId
	 * @return
	 */
	public boolean isExistById(String objectId) {
		boolean isExist = false;
		if (StringUtil.isNotEmpty(objectId)) {
			AVQuery query = AVQuery.getQuery("_File");
			query.whereEqualTo("objectId", objectId);
			try {
				int dataCount = query.count();
				isExist = dataCount > 0;
			} catch (AVException e) {
				e.printStackTrace();
			}
		}
		return isExist;
	}
}
