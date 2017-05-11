package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.entity.AppAdvert;

import java.util.List;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertCloud {

	@EngineFunction("startImg")
	public static List<AppAdvert> startimg(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		return new AppAdvertDao().findAppAdverListByCountryCode(countryCode);
	}
}
