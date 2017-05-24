package com.it7890.orange.api.service;

import com.it7890.orange.api.entity.AppAdvert;

import java.util.List;

/**
 * Created by Astro on 17/5/11.
 */
public interface IAppAdvertService {

	List<AppAdvert> findAppAdverListByCountryCode(String countryCode);
}
