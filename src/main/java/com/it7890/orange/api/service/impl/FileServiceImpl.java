package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.dao.FileDao;
import com.it7890.orange.api.service.IFileService;

/**
 * Created by Astro on 17/5/31.
 */
public class FileServiceImpl implements IFileService {

	@Override
	public boolean isExistById(String objectId) {
		return new FileDao().isExistById(objectId);
	}
}
