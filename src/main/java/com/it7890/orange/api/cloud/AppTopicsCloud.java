package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.dto.AppAdvertDto;
import com.it7890.orange.api.dto.AppTopicsDTO;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.AppTopicsServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppTopicsCloud {
	private static Logger logger = LogManager.getLogger(AppTopicsCloud.class);

//	@EngineFunction("queryCountryTopics")
//	public  static String queryCountryTopics(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
//		logger.info("queryCountryTopics===========>params:"+"countryCode="+countryCode);
//		int resultCode = Constants.CODE_SUCCESS;
//		String resultMsg = "成功";
//		List<AppTopics> ls = new ArrayList<AppTopics>();
//		List<AppTopicsDTO> resDTOList = new ArrayList<AppTopicsDTO>();
//		if (StringUtil.isNotEmpty(countryCode)){
//			List<HbCountrys> hs = new HbCountrysServiceImpl().getcsByCode(countryCode.toUpperCase());
//			if(hs!=null){
//				if(hs.size()>0){
//					if(StringUtil.isNotEmpty(hs.get(0).getObjectId())){
//						ls = new AppTopicsServiceImpl().getAppTopicsById(hs.get(0).getObjectId());
//					}else {
//						resultCode = Constants.CODE_PARAMS_FAIL;
//						resultMsg = "内部参数有误";
//					}
//				}else {
//					resultCode = Constants.CODE_PARAMS_FAIL;
//					resultMsg = "国家不存在";
//				}
//			}else {
//				resultCode = Constants.CODE_PARAMS_FAIL;
//				resultMsg = "国家不存在";
//			}
//		}else {
//			resultCode = Constants.CODE_PARAMS_FAIL;
//			resultMsg = "参数错误";
//		}
//		if(resultCode == 1){
//			resDTOList = buildDtoList(ls);
//		}
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("code", resultCode);
//		resultMap.put("msg", resultMsg);
//		resultMap.put("privilegeList", resDTOList);
//		return JSON.toJSONString(resultMap);
//
//	}

	@EngineFunction("queryCountryTopics")
	public  static String queryCountryTopics(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		logger.info("queryCountryTopics===========>params:"+"countryCode="+countryCode);
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		List<AppTopicsDTO> resDTOList = new ArrayList<AppTopicsDTO>();
		AVQuery queryCountry = new AVQuery("hb_countrys");
		AVQuery queryAppTopics = new AVQuery("AppTopics");
		if (StringUtil.isNotEmpty(countryCode)){
			queryCountry.whereEqualTo("countryCode",countryCode.toUpperCase());
			List<AVObject> hs = queryCountry.find();
			if(hs!=null){
				if(hs.size()>0){
					if(StringUtil.isNotEmpty(hs.get(0).getObjectId())){
						queryAppTopics.whereEqualTo("countryObj",AVObject.createWithoutData("hb_countrys",hs.get(0).getObjectId()));
						queryAppTopics.include("topicIconFile");
						queryAppTopics.addDescendingOrder("rank");
						List<AVObject> ls = queryAppTopics.find();
						resDTOList = buildavoDtoList(ls);
					}else {
						resultCode = Constants.CODE_PARAMS_FAIL;
						resultMsg = "内部参数有误";
					}
				}else {
					resultCode = Constants.CODE_PARAMS_FAIL;
					resultMsg = "国家不存在";
				}
			}else {
				resultCode = Constants.CODE_PARAMS_FAIL;
				resultMsg = "国家不存在";
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("topicsList", resDTOList);
		return JSON.toJSONString(resultMap);

	}

	private static List<AppTopicsDTO> buildDtoList(List<AppTopics> tmp) {
		AppTopicsDTO appTopicsDTO;
		List<AppTopicsDTO> appTopicsDTOList = new ArrayList<>();
		for(AppTopics appTopics : tmp) {
			appTopicsDTO = AppTopicsDTO.objectToDto(appTopics);
			if (null != appTopicsDTO) {
				appTopicsDTOList.add(appTopicsDTO);
			}
		}
		return appTopicsDTOList;
	}

	public static List<AppTopicsDTO> buildavoDtoList(List<AVObject> tmp) {
		AppTopicsDTO appTopicsDTO;
		List<AppTopicsDTO> appTopicsDTOList = new ArrayList<>();
		for(AVObject avObject : tmp) {
			appTopicsDTO = AppTopicsDTO.avobjectToDto(avObject);
			if (null != appTopicsDTO) {
				appTopicsDTOList.add(appTopicsDTO);
			}
		}
		return appTopicsDTOList;
	}
}
