package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dao.HbCountrysDao;
import com.it7890.orange.api.dao.PubicationDao;
import com.it7890.orange.api.dto.*;
import com.it7890.orange.api.entity.AppTopics;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.HbCountrys;
import com.it7890.orange.api.service.impl.*;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class ConArtilesCloud {
	private static Logger logger = LogManager.getLogger(ConArtilesCloud.class);

	@EngineFunction("queryIndexAndRecommendList")
	public static String queryIndexAndRecommendList(@EngineFunctionParam("countryCode") String countryCode,
													@EngineFunctionParam("langId") String langId,
													@EngineFunctionParam("artCreateTime") long artCreateTime,
													@EngineFunctionParam("topCreateTime") long topCreateTime,
													@EngineFunctionParam("direct") int direct,
													@EngineFunctionParam("limit") int limit) throws AVException, ParseException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> resArtDTOList = new ArrayList<ConArticleDTO>();
		if(StringUtils.isNotBlank(langId)){
			//根据langId获取国家code
			AVObject avObjectC = new HbCountrysDao().findCodeByLangId(langId);
			countryCode = avObjectC.getString("countryCode");
		}

		int topUpdateTmp = 0;
		//根据时间戳判断置顶大图是否有更新
		if(topCreateTime!=0){
			topUpdateTmp = getTopTmpByTime(countryCode,topCreateTime);
		}

		//开始文章查询
		resArtDTOList = new ConArticleServiceImpl().getArticlesList(countryCode,artCreateTime, direct,limit);
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
												 @EngineFunctionParam("langId") String langId,
												 @EngineFunctionParam("createTime") long createTime,
												 @EngineFunctionParam("topCreateTime") long topCreateTime,
												 @EngineFunctionParam("direct") int direct,
												 @EngineFunctionParam("limit") int limit) throws AVException, ParseException {

		logger.info("topicID: {}, countryCode: {}, langId: {}, createTime: {}, topCreateTime: {}, direct: {}, limit: {}", new Object[]{topicID, countryCode, langId, createTime, topCreateTime, direct, limit});

		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> resArtDTOList = new ArrayList<ConArticleDTO>();
		if(StringUtils.isNotBlank(langId)){
			//根据langId获取国家code
			AVObject avObjectC = new HbCountrysDao().findCodeByLangId(langId);
			countryCode = avObjectC.getString("countryCode");
		}

		int topUpdateTmp = 0;
		//根据时间戳判断置顶大图是否有更新
		if(topCreateTime!=0){
			topUpdateTmp = getTopTmpByTime(countryCode,topCreateTime);
		}

		if (StringUtils.isNotEmpty(topicID)){
			resArtDTOList = new ConArticleServiceImpl().getTopicsArticlesList(countryCode, topicID, createTime, direct, limit);
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
	public static String getArtContent(@EngineFunctionParam("articleId") String articleId,
									   @EngineFunctionParam("imei") String imei) throws AVException, IOException {
		int resultCode = Constants.CODE_SUCCESS;
		AVUser user = AVUser.getCurrentUser();
		String userId = "";
		if (null != user) {
			userId = user.getObjectId();
		}

		int tmpLike = -2; // 点赞状态 -2未点赞 0喜欢 -1不喜欢
		int tmpFav = -1;
		int tmpPub = -1;
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
			if(StringUtils.isNotBlank(imei)){
				List<AVObject> lsLike = new UserLikeFavariteServiceImpl().getLikeList(1, articleId, imei, userId);
				if (lsLike.size()>0) {
					tmpLike = lsLike.get(0).getInt("status");
				}
				List<AVObject> lsFav = new UserLikeFavariteServiceImpl().getLikeList(2,articleId,imei, userId);
				if (lsFav.size()>0){
					tmpFav = 0;
				}
				logger.info("artid::>>{},pubid::>>{}",articleId,resArtContentDTO.getCopyrightId());
				List<AVObject> lsPub = new UserRssPublicationServiceImpl().getList(resArtContentDTO.getCopyrightId(),imei);
				if (lsPub.size()>0){
					tmpPub = 0;
				}
			}else {
				resultCode = Constants.CODE_SUCCESS;
				resultMsg = "获取点赞收藏订阅状态失败,未传imei";
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误,articleId不能为空";
		}
		resultMap.put("tmpLike", tmpLike);
		resultMap.put("tmpFav", tmpFav);
		resultMap.put("tmpPub", tmpPub);

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);

	}

	/**
	 * 关键字搜索
	 * @param keywords 关键字
	 * @return
	 */
	@EngineFunction("searchArticle")
	public static String searchArticle(@EngineFunctionParam("keywords") String keywords,
	                                   @EngineFunctionParam("divTime") long divTime,
	                                   @EngineFunctionParam("direct") int direct) {

		logger.info("keywords: {}, divTime: {}, direct: {}", keywords, divTime, direct);

		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> articleDtoList = new ArrayList<>();

		if (StringUtil.isNotEmpty(keywords)) {
			if (direct == 0) {  // 0下拉 1上拉，加减1秒，防止取到自己
				divTime += 1000;
			} else {
				divTime -= 1000;
			}
			Date divDate = DateUtil.long2Date(divTime);
			List<AVObject> articleList = new ConArticleDao().findArticleListByKeywords(keywords, divDate, direct);
			logger.info("articleList length: {}", articleList.size());
			try {
				articleDtoList = ConArticleServiceImpl.buildavoDtoList(articleList);
			} catch (AVException e) {
				e.printStackTrace();
				logger.warn("搜索关键字结果转换异常，cause: {}", e);
			}
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误，请输入关键字";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("articleList", articleDtoList);
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

	@EngineFunction("publicationHomePage")
	public static String pubHomePage(@EngineFunctionParam("pubId") String pubId,
									   @EngineFunctionParam("artTime") long artTime,
										 @EngineFunctionParam("imei") String imei,
									   @EngineFunctionParam("direct") int direct) throws AVException {

		logger.info("pubId: {}, artTime: {}, direct: {}", pubId, artTime, direct);

		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ConArticleDTO> articleDtoList = new ArrayList<>();
		int tmpPub = -1;
		AVObject avObject = null;
		if (StringUtil.isNotEmpty(pubId)) {
			avObject = new PubicationDao().getById(pubId);
			if (StringUtils.isNotBlank(imei)){
				List<AVObject> lsPub = new UserRssPublicationServiceImpl().getList(pubId,imei);
				if (lsPub.size()>0){
					tmpPub = 0;
				}
			}
			articleDtoList = new ConArticleServiceImpl().getArtByPubId(pubId,artTime,direct);
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "params pubId can not null!";
		}
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		resultMap.put("articleList", articleDtoList);
		resultMap.put("publicationObj",PublicatiomHomeDTO.buildDTO(avObject,tmpPub));
		return JSON.toJSONString(resultMap);
	}

}
