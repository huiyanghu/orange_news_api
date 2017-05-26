package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.dto.CountryDTO;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.util.Constants;
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

	private static List<CountryDTO> builderCountryDto(List<AVObject> countryInfoList) {
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
				String languageId = "";
				if (null != countryInfo.get("languageObj")) {
					AVObject languageObj = (AVObject) countryInfo.get("languageObj");
					languageId = languageObj.getObjectId();
				}
				countryDto.setLanguageId(languageId);

				String iconUrl = "";
				if (null != countryInfo.get("iconFileObj")) {
					AVFile iconFileObj = (AVFile) countryInfo.get("iconFileObj");
					iconUrl = iconFileObj.getUrl();
				}
				countryDto.setIconUrl(iconUrl);
				countryDtoList.add(countryDto);
			}
		}
		return countryDtoList;
	}
}
