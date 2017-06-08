package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.dto.CountryDTO;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.AppKeywordsServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Astro on 17/5/26.
 */
public class CountryCloud {

	private static final Logger logger = LogManager.getLogger(LanguageCloud.class);


	@EngineFunction("countryList")
	public static String countryList() throws AVException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		List<AVObject> countryList = new HbCountrysDao().findCountryList();
		List<CountryDTO> countryDtoList = builderCountryDto(countryList);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("countryList", countryDtoList);
		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("keywordsByCountry")
	public static String keywordsByCountry(@EngineFunctionParam("countryCode") String countryCode) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		List<String> keywords = new ArrayList<>();
		if (StringUtil.isNotEmpty(countryCode)) {
			HbCountrys countryInfo = new HbCountrysServiceImpl().getCountryByCountryCode(countryCode);
			if (null != countryInfo) {
				List<AVObject> keywordList = new AppKeywordsServiceImpl().findKeywordsByCountryId(countryInfo.getObjectId());
				for (AVObject keywordItem : keywordList) {
					keywords.add(keywordItem.getString("keyword"));
				}
			} else {
				resultCode = Constants.CODE_CANNOT_FIND;
				resultMsg = "未找到相关国家";
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("keywords", keywords);
		return JSON.toJSONString(resultMap);
	}

	public static List<CountryDTO> builderCountryDto(List<AVObject> countryInfoList) {
		List<CountryDTO> countryDtoList = new ArrayList<>();
		if (null != countryInfoList) {
			CountryDTO countryDto;
			for (AVObject countryInfo : countryInfoList) {
				countryDto = new CountryDTO();

				countryDto.setObjectId(countryInfo.getObjectId());
				countryDto.setCnName(countryInfo.getString("cnName"));
				countryDto.setEnName(countryInfo.getString("enName"));
				countryDto.setShortName(countryInfo.getString("shortName"));
				countryDto.setCountryCode(countryInfo.getString("countryCode"));
				countryDto.setLanguageId(null != countryInfo.get("languageObj") ?((AVObject) countryInfo.get("languageObj")).getObjectId() : "");
				countryDto.setIconUrl(null != countryInfo.get("iconFileObj") ? ((AVFile) countryInfo.get("iconFileObj")).getUrl() : "");
				countryDtoList.add(countryDto);
			}
		}
		return countryDtoList;
	}
}
