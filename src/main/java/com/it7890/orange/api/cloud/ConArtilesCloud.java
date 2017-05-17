package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.it7890.orange.api.dto.AppTopicsDTO;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.AppTopicsServiceImpl;
import com.it7890.orange.api.service.impl.ConArticleServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConArtilesCloud {
	private static Logger logger = LogManager.getLogger(ConArtilesCloud.class);

	@EngineFunction("queryConArtilesList")
	public  static String queryConArtilesList() throws AVException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		List<ConArticle> ls = new ArrayList<ConArticle>();
		List<ConArticleDTO> resDTOList = new ArrayList<ConArticleDTO>();
		if (true){
			ls = new ConArticleServiceImpl().getArticlesList();
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}
		if(resultCode == 1){
			resDTOList = buildDtoList(ls);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("privilegeList", resDTOList);
		return JSON.toJSONString(resultMap);

	}

	private static List<ConArticleDTO> buildDtoList(List<ConArticle> tmp) {
		ConArticleDTO conArticleDTO;
		List<ConArticleDTO> DTOList = new ArrayList<ConArticleDTO>();
		for(ConArticle conArticle : tmp) {
			conArticleDTO = ConArticleDTO.objectToDto(conArticle);
			if (null != conArticleDTO) {
				DTOList.add(conArticleDTO);
			}
		}
		return DTOList;
	}
}
