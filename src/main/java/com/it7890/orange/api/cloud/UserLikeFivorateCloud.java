package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dao.UserCommentDao;
import com.it7890.orange.api.dao.UserLikeFavoriteDao;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.UserCommentDTO;
import com.it7890.orange.api.dto.UserLikeFavoriteDTO;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.service.impl.UserLikeFavariteServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
		if(StringUtils.isNotBlank(objId)|| StringUtils.isNotBlank(userId)){
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
			resultMsg = "objId userId不能为空";
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


	/**
	 * 提交/删除评论
	 * @param articleId  文章id
	 * @param userId  用户id
	 * @param commentId  评论id
	 * @param content  评论内容
	 * @param status  0 添加  -1删除
	 * @return
     * @throws AVException
     */
	@EngineFunction("userPostComment")
	public static String userPostComment(@EngineFunctionParam("articleId") String articleId,
										@EngineFunctionParam("userId") String userId,
										@EngineFunctionParam("commentId") String commentId,
										@EngineFunctionParam("content") String content,
										@EngineFunctionParam("status") int status) throws AVException {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(articleId)){
			if(StringUtils.isNotBlank(userId)){
				AVObject avoobj = new AVObject("UserComment");
				AVQuery<AVObject> artavQuery = new AVQuery<>("conarticle");
				AVObject artObject = artavQuery.get(articleId);
				AVQuery<AVObject> useravQuery = new AVQuery<>("_User");
				AVObject userObject = useravQuery.get(userId);
				avoobj.put("userObj",userObject);
				avoobj.put("articleObj",artObject);
				if(status!=-1){//添加评论
					avoobj.put("status",status);
					avoobj.put("content",content);
					new UserCommentDao().saveObj(avoobj);
				}else {//删除
					AVQuery<AVObject> commentavQuery = new AVQuery<>("UserComment");
					AVObject commentObj= commentavQuery.get(commentId);
					if(commentObj.getInt("status")!=status){
						avoobj.setObjectId(commentObj.getObjectId());
						avoobj.put("status",status);
						new UserCommentDao().saveObj(avoobj);
					}else {
						resultCode = Constants.CODE_PARAMS_FAIL;
						resultMsg = "状态错误";
						resultMap.put("code", resultCode);
						resultMap.put("msg", resultMsg);
						return JSON.toJSONString(resultMap);
					}
				}
			}else {
				resultCode = Constants.CODE_PARAMS_FAIL;
				resultMsg = "请登录";
			}
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "objId不能为空";
		}

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);
	}

	@EngineFunction("artCommentList")
	public static String artCommentList(@EngineFunctionParam("articleId") String articleId,
										@EngineFunctionParam("createTime") long createTime) throws AVException {

		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(articleId)){
			List<AVObject> ls = new UserCommentDao().getArtCommentList(articleId,createTime);
			List<UserCommentDTO> resList = buildCommentDTOList(ls);
			resultMap.put("commentList", resList);
		}else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "文章id不能为空";
			return JSON.toJSONString(resultMap);
		}
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}

	private static List<UserCommentDTO> buildCommentDTOList(List<AVObject> tmp) {
		UserCommentDTO userCommentDTO;
		List<UserCommentDTO> DTOList = new ArrayList<UserCommentDTO>();
		for(AVObject aVObject : tmp) {
			userCommentDTO = UserCommentDTO.AVOBbjToDTO(aVObject);
			if (null != userCommentDTO) {
				DTOList.add(userCommentDTO);
			}
		}
		return DTOList;
	}

	@EngineFunction("userFavoriteList")
	public static String userFavoriteList(@EngineFunctionParam("createTime") long createTime) throws AVException {

		AVUser user  = AVUser.getCurrentUser();
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";
		Map<String, Object> resultMap = new HashMap<String, Object>();


//		user.setObjectId(user.get);
//		AVQuery<AVObject> query = new AVQuery<>("UserLikeFavorite");
//		query.whereEqualTo("userObj", AVObject.createWithoutData("_User", userId));
//		query.whereLessThan("createdAt", 2);
//		// 如果这样写，第二个条件将覆盖第一个条件，查询只会返回 priority = 1 的结果
//		List<AVObject> todos = query.find();

		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);

	}

}
