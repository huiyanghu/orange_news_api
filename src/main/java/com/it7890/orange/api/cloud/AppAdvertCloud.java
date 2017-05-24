package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.dto.AppAdvertDto;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.service.IAppAdvertService;
import com.it7890.orange.api.service.impl.AppAdvertServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertCloud {

	@Resource
	private IAppAdvertService appAdvertService;
	@Resource
	private AppAdvertDao appAdvertDao;

	private static final Logger logger = LogManager.getLogger(AppAdvertCloud.class);

	/**
	 * 首页人文图
	 * @param countryCode 国家编码
	 * @return
	 * @throws AVException
	 */
	@EngineFunction("startImg")
	public static String startImg(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		logger.info("args===>：{}", countryCode);

		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		List<AppAdvertDto> appAdvertDTOList = new ArrayList<>();
		if (StringUtil.isNotEmpty(countryCode)) {
			List<AppAdvert> appAdvertList = new AppAdvertServiceImpl().findAppAdverListByCountryCode(countryCode);
			appAdvertDTOList = buildAppAdvertDtoList(appAdvertList);
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("privilegeList", appAdvertDTOList);
		return JSON.toJSONString(resultMap);
	}

	private static List<AppAdvertDto> buildAppAdvertDtoList(List<AppAdvert> appAdvertList) {
		AppAdvertDto appAdvertDto;
		List<AppAdvertDto> appAdvertDTOList = new ArrayList<>();
		for(AppAdvert appAdvert : appAdvertList) {
			appAdvertDto = AppAdvertDto.objectToDto(appAdvert);
			if (null != appAdvertDto) {
				appAdvertDTOList.add(appAdvertDto);
			}
		}
		return appAdvertDTOList;
	}
}
