package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.dto.AppTopDTO;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.service.impl.AppTopServiceImpl;
import com.it7890.orange.api.service.impl.ConArticleServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.DateUtil;
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


public class AppTopCloud {
	private static Logger logger = LogManager.getLogger(AppTopCloud.class);

	/**
	 * 置顶大图
	 * @param countryCode
	 * @param topCreateTime
	 * @return
	 * @throws AVException
	 * @throws ParseException
     */
	@EngineFunction("queryAppTopList")
	public static String queryAppTopList(@EngineFunctionParam("countryCode") String countryCode,
													@EngineFunctionParam("topCreateTime") long topCreateTime
													) throws AVException, ParseException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AppTopDTO> resTopDTOList = new ArrayList<AppTopDTO>();

		AVQuery<AVObject> query = new AVQuery<>("AppTop");
		query.include("articleObj");
		query.include("articleObj.titlePicObjArr");
		query.whereEqualTo("countryCode", countryCode);
		query.orderByDescending("createdAt");
		query.limit(10);
		if(topCreateTime!=0){
			String createdAt =  DateUtil.Long2StringUTC(topCreateTime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_0);
			query.whereGreaterThan("createdAt",createdAt);
		}
		List<AVObject> ls = query.find();
		if(ls.size()>0){
			for (AVObject avo:ls){
				resTopDTOList.add(AppTopDTO.avoobjectToDto(avo));
			}
			resultMap.put("appTopList",resTopDTOList);
		}else {
			resultMsg = "置顶大图已最新";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);

	}

}
