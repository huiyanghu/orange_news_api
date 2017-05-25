package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dao.UserLikeFavoriteDao;
import com.it7890.orange.api.dto.UserLikeFavoriteDTO;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.service.impl.UserLikeFavariteServiceImpl;
import com.it7890.orange.api.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UserLikeFivorateCloud {
	private static Logger logger = LogManager.getLogger(UserLikeFivorateCloud.class);

	/**
	 * 点赞收藏 istatus 0 添加  -1 取消
	 * lType  1  点赞  2   收藏
	 */
	@EngineFunction("userLikeFivorate")
	public static String queryIndexAndRecommendList(@EngineFunctionParam("objId") String objId,
											 		@EngineFunctionParam("lType") int lType,
													@EngineFunctionParam("userId") String userId,
													@EngineFunctionParam("istatus") int istatus) throws AVException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(objId)){
			if(objId.indexOf(",")!=-1){
				String[] objIds=objId.split(",");
				for (int i = 0; i < objIds.length; i++) {
					if(objIds[i]!=null&&!"".equals(objIds[i])){
						UserLikeFavorite avoObj=new UserLikeFavorite();
						avoObj.put("articleObj", AVObject.createWithoutData("conarticle", objIds[i]));
						avoObj.put("userObj",AVObject.createWithoutData("_User", userId));
						avoObj.setLType(lType);
						avoObj.setStatus(istatus);
						doUserLF(avoObj,lType,objIds[i],userId,istatus);
					}
				}
			}else{
				if(lType==1){//点赞,对文章喜欢|不喜欢数操作
					List<UserLikeFavoriteDTO> list= new UserLikeFavariteServiceImpl().getUserLFList(lType,objId,userId);
					if(list.size()==0){
						List<ConArticlesContent> ls = new ConArticleDao().getArtContentById(objId);
						int likeCount = ls.get(0).getInt("likeCount");
						int noLikeCount = ls.get(0).getInt("noLikeCount");
						String avobjId = ls.get(0).getObjectId();
						if(istatus==0){
							likeCount+=1;
						}else{
							noLikeCount+=1;
						}
						AVObject avo = new AVObject("con_articles_content");
						avo.setObjectId(avobjId);
						avo.put("likeCount",likeCount);
						avo.put("noLikeCount",noLikeCount);
						avo.save();
					}else {
						resultCode = Constants.CODE_PARAMS_FAIL;
						resultMsg = "您已操作过,不能重复操作";
						resultMap.put("code", resultCode);
						resultMap.put("msg", resultMsg);
						return JSON.toJSONString(resultMap);
					}
				}
				UserLikeFavorite avoObj=new UserLikeFavorite();
				avoObj.put("articleObj", AVObject.createWithoutData("conarticle", objId));
				avoObj.put("userObj",AVObject.createWithoutData("_User", userId));
				avoObj.setLType(lType);
				avoObj.setStatus(istatus);
				doUserLF(avoObj,lType,objId,userId,istatus);
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "objId不能为空";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);
	}

	public static void doUserLF(UserLikeFavorite avoObj,int lType,String artId,String userId,int istatus){
		List<UserLikeFavoriteDTO> list= new UserLikeFavariteServiceImpl().getUserLFList(lType,artId,userId);
		if(list.size()>0){
			avoObj.setObjectId(list.get(0).getId());
			if(list.get(0).getStatus()!=istatus){
				new UserLikeFavoriteDao().saveObj(avoObj);
			}
		}else{
			if(lType==1){
				new UserLikeFavoriteDao().saveObj(avoObj);
			}else {
				if(istatus!=-1){
					new UserLikeFavoriteDao().saveObj(avoObj);
				}
			}
		}
	}

}
