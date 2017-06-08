package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dto.LanguageDTO;
import com.it7890.orange.api.service.impl.HbLanguagesServiceImpl;
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
public class LanguageCloud {

	private static final Logger logger = LogManager.getLogger(LanguageCloud.class);


	@EngineFunction("languageList")
	public static String languageList() throws AVException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		List<AVObject> languages = new HbLanguagesServiceImpl().findLanguageInfoList();
		List<LanguageDTO> languageDtos = builderLanguageDto(languages);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("languageList", languageDtos);
		return JSON.toJSONString(resultMap);
	}

	public static List<LanguageDTO> builderLanguageDto(List<AVObject> languages) {
		List<LanguageDTO> languageDtos = new ArrayList<>();
		if (null != languages && languages.size() > 0) {
			LanguageDTO languageDTO;
			for (AVObject languageInfo : languages) {
				if (null != languageInfo) {
					languageDTO = new LanguageDTO();
					languageDTO.setObjectId(languageInfo.getObjectId());
					languageDTO.setCode(languageInfo.getString("codes"));
					languageDTO.setName(languageInfo.getString("name"));
					languageDtos.add(languageDTO);
				}
			}
		}
		return languageDtos;
	}

}
