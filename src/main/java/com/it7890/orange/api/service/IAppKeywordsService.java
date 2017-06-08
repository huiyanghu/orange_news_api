package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * Created by Astro on 17/6/8.
 */
public interface IAppKeywordsService {

	List<AVObject> findKeywordsByCountryId(String countryId);

	List<AVObject> findAllKeywords();
}
