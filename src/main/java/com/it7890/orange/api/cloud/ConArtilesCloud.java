package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.dto.AppTopDTO;
import com.it7890.orange.api.dto.AppTopicsDTO;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.AppTopServiceImpl;
import com.it7890.orange.api.service.impl.AppTopicsServiceImpl;
import com.it7890.orange.api.service.impl.ConArticleServiceImpl;
import com.it7890.orange.api.service.impl.HbCountrysServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConArtilesCloud {
	private static Logger logger = LogManager.getLogger(ConArtilesCloud.class);

	@EngineFunction("queryIndexAndRecommendList")
	public static String queryIndexAndRecommendList(@EngineFunctionParam("countryCode") String countryCode,
													@EngineFunctionParam("artCreateTime") String artCreateTime,
													@EngineFunctionParam("topCreateTime") long topCreateTime,
													@EngineFunctionParam("direct") int direct) throws AVException, ParseException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> resArtDTOList = new ArrayList<ConArticleDTO>();

		int topUpdateTmp = 0;
		//根据时间戳判断置顶大图是否有更新
		if(topCreateTime!=0){
			topUpdateTmp = getTopTmpByTime(countryCode,topCreateTime);
		}

		//开始文章查询
		resArtDTOList = new ConArticleServiceImpl().getArticlesList(artCreateTime,direct);
		if(resArtDTOList==null){
			resultMsg = "文章已最新";
		}else {
			resultMap.put("artsList", resArtDTOList);
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("topUpdateTmp", topUpdateTmp);

		return JSON.toJSONString(resultMap);

	}

	@EngineFunction("queryTopicsArticlesList")
	public static String queryTopicsArticlesList(@EngineFunctionParam("topicID") String topicID,
												 @EngineFunctionParam("countryCode") String countryCode,
												 @EngineFunctionParam("createTime") String createTime,
												 @EngineFunctionParam("topCreateTime") long topCreateTime,
												 @EngineFunctionParam("direct") int direct) throws AVException, ParseException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> resArtDTOList = new ArrayList<ConArticleDTO>();

		int topUpdateTmp = 0;
		//根据时间戳判断置顶大图是否有更新
		if(topCreateTime!=0){
			topUpdateTmp = getTopTmpByTime(countryCode,topCreateTime);
		}

		if (StringUtils.isNotEmpty(topicID)){
			resArtDTOList = new ConArticleServiceImpl().getTopicsArticlesList(topicID,createTime,direct);
			if(resArtDTOList!=null){
				resultMap.put("artsList", resArtDTOList);
			}else {
				resultMsg = "文章已是最新";
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误,topicID不能为空";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("topUpdateTmp", topUpdateTmp);

		return JSON.toJSONString(resultMap);

	}


	@EngineFunction("getArtContentById")
	public static String getArtContent(@EngineFunctionParam("articleId") String articleId) throws AVException, IOException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ConArticleDetailDTO resArtContentDTO = new ConArticleDetailDTO();

		if (StringUtils.isNotEmpty(articleId)){
			resArtContentDTO = new ConArticleServiceImpl().getArtContentById(articleId);
			if(resArtContentDTO!=null){
				resultMap.put("articleDetails", resArtContentDTO);
			}else {
				resultMsg = "文章内容不存在";
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误,articleId不能为空";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);

	}

	public static int getTopTmpByTime(String countryCode,long topCreateTime) throws AVException{
		int topTmp = 0;
		AVQuery<AVObject> query = new AVQuery<>("AppTop");
		query.include("articleObj");
		query.whereEqualTo("countryCode", countryCode);
		query.orderByDescending("createdAt");
		query.limit(10);
		String createdAt =  DateUtil.Long2StringUTC(topCreateTime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_0);
		query.whereGreaterThan("createdAt",createdAt);
		List<AVObject> ls = query.find();
		if(ls.size()>0){
			topTmp = 1;
		}
		return topTmp;
	}

//	/**
//	 * 置顶大图
//	 * @param countryCode
//	 * @param topCreateTime
//	 * @return
//	 * @throws AVException
//	 * @throws ParseException
//     */
//	@EngineFunction("queryAppTopList")
//	public static String queryAppTopList(@EngineFunctionParam("countryCode") String countryCode,
//													@EngineFunctionParam("topCreateTime") String topCreateTime
//													) throws AVException, ParseException {
//		int resultCode = Constants.CODE_SUCCESS;
//		String resultMsg = "成功";
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		List<AppTopDTO> resTopDTOList = new ArrayList<AppTopDTO>();
//		List<AppTopDTO> appTopDTOLs = new ArrayList<AppTopDTO>();
//
//		//根据国家code获取置顶大图
//		appTopDTOLs = new AppTopServiceImpl().getAppTopsList(countryCode,topCreateTime);
//		if(appTopDTOLs!=null){
//			for(int i=0;i<appTopDTOLs.size();i++){
//				AppTopDTO topDTO=appTopDTOLs.get(i);
//				AppTopDTO newDto ;
//				if(topDTO.getiType()==1){//文章
//					List<ConArticleDTO> lsid = new ConArticleServiceImpl().getArticleById(topDTO.getObjId());
////				artvo.setCommentnum(interactiveService.getCommentnumByArtid(Integer.parseInt(top.getObjid().toString())));
//					if(lsid!=null){
//						newDto = new AppTopDTO();
//						BeanUtils.copyProperties(lsid.get(0), newDto);
//						newDto.setArticleId(topDTO.getObjId());
//						newDto.setObjId(topDTO.getObjId());
//						newDto.setCountryCode(topDTO.getCountryCode());
//						resTopDTOList.add(newDto);
//					}
////				String titlepic=newDto.getTitlePic();
////				if(titlepic.indexOf("!")>-1){
////					titlepic=titlepic.substring(0, titlepic.indexOf("!"));
////					titlepic=titlepic+"!320";
////				}
////				newDto.setTitlePic(titlepic);
//				}
//			}
//
//			resultMap.put("topsList", resTopDTOList);
//		}else {
//			resultMsg = "置顶大图已最新";
//		}
//
//		resultMap.put("code", resultCode);
//		resultMap.put("msg", resultMsg);
//
//		return JSON.toJSONString(resultMap);
//
//	}

}
