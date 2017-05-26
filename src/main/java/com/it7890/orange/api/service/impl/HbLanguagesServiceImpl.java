package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.HbLanguagesDao;
import com.it7890.orange.api.service.IHbLanguagesService;

import java.util.List;

/**
 * Created by Astro on 17/5/26.
 */
public class HbLanguagesServiceImpl implements IHbLanguagesService {

	@Override
	public List<AVObject> findLanguageInfoList() {
		return new HbLanguagesDao().findLanguageInfoList();
	}
}
