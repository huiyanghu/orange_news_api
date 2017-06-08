package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.google.gson.Gson;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.dto.AppTopicsDTO;
import com.it7890.orange.api.dto.CountryDTO;
import com.it7890.orange.api.dto.LanguageDTO;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.service.impl.AppKeywordsServiceImpl;
import com.it7890.orange.api.service.impl.AppTopicsServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import com.it7890.orange.api.service.impl.HbLanguagesServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Astro on 17/6/8.
 */
public class SystemCloud {

	private final static Logger logger = LogManager.getLogger(SystemCloud.class);

	/**
	 * 国家、语言等数据
	 * @return
	 */
	@EngineFunction("sysCommonData")
	public static String sysCommonData() {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		// 国家
		List<AVObject> countryList = new HbCountrysServiceImpl().findCountryList();
		List<CountryDTO> countryDtoList = CountryCloud.builderCountryDto(countryList);
		// 语言
		List<AVObject> languageList = new HbLanguagesServiceImpl().findLanguageInfoList();
		List<LanguageDTO> languageDtoList = LanguageCloud.builderLanguageDto(languageList);
		// 关键字
		List<AVObject> keywordList = new AppKeywordsServiceImpl().findAllKeywords();
		// 分类
		List<AVObject> topicsList = new AppTopicsServiceImpl().findAllTopicsList();
		List<AppTopicsDTO> topicsDtoList = AppTopicsCloud.buildavoDtoList(topicsList);

		String countryId;
		String countryLanguageId;
		String keyword;
		for (CountryDTO countryDto : countryDtoList) {
			for (AVObject keywordItem : keywordList) {
				keyword = keywordItem.getString("keyword");
				countryId = null!=keywordItem.get("countryObj") ? ((AVObject) keywordItem.get("countryObj")).getObjectId() : "";
				if (countryDto.getObjectId().equals(countryId) && countryDto.getKeywords().indexOf(keyword) == -1) {
					countryDto.getKeywords().add(keyword);
				}
			}

			countryLanguageId = countryDto.getLanguageId();
			if (StringUtil.isNotEmpty(countryLanguageId)) {
				for (LanguageDTO languageDto : languageDtoList) {
					if (countryLanguageId.equals(languageDto.getObjectId())) {
						for (AVObject keywordItem : keywordList) {
							keyword = keywordItem.getString("keyword");
							countryId = null!=keywordItem.get("countryObj") ? ((AVObject) keywordItem.get("countryObj")).getObjectId() : "";
							if (countryDto.getObjectId().equals(countryId) && languageDto.getKeywords().indexOf(keyword) == -1) {
								languageDto.getKeywords().add(keyword);
							}
						}

						for (AppTopicsDTO topicDto : topicsDtoList) {
							if (countryDto.getObjectId().equals(topicDto.getCountryId()) && countryDto.getTopicList().indexOf(topicDto) == -1) {
								languageDto.getTopicList().add(topicDto);
							}
						}
						break;
					}
				}
			}

			for (AppTopicsDTO topicDto : topicsDtoList) {
				if (countryDto.getObjectId().equals(topicDto.getCountryId()) && countryDto.getTopicList().indexOf(topicDto) == -1) {
					countryDto.getTopicList().add(topicDto);
				}
			}
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("countryList", countryDtoList);
		resultMap.put("languageList", languageDtoList);
		return new Gson().toJson(resultMap);
//		return JSON.toJSONString(resultMap);
	}
}
