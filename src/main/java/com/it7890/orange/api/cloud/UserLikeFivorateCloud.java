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
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserLikeFivorateCloud {
    private static Logger logger = LogManager.getLogger(UserLikeFivorateCloud.class);

//	/**
//	 * 点赞收藏 istatus 0 添加  -1 取消
//	 * lType  1  点赞  2   收藏
//	 */
//	@EngineFunction("userLikeFivorate")
//	public static String queryIndexAndRecommendList(@EngineFunctionParam("objId") String objId,
//											 		@EngineFunctionParam("lType") int lType,
//													@EngineFunctionParam("userId") String userId,
//													@EngineFunctionParam("istatus") int istatus) throws AVException {
//		int resultCode = Constants.CODE_SUCCESS;
//		String resultMsg = "成功";
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		if(StringUtils.isNotBlank(objId)|| StringUtils.isNotBlank(userId)){
//			if(objId.indexOf(",")!=-1){
//				String[] objIds=objId.split(",");
//				for (int i = 0; i < objIds.length; i++) {
//					if(objIds[i]!=null&&!"".equals(objIds[i])){
//						UserLikeFavorite avoObj=new UserLikeFavorite();
//						avoObj.put("articleObj", AVObject.createWithoutData("conarticle", objIds[i]));
//						avoObj.put("userObj",AVObject.createWithoutData("_User", userId));
//						avoObj.setLType(lType);
//						avoObj.setStatus(istatus);
//						doUserLF(avoObj,lType,objIds[i],userId,istatus);
//					}
//				}
//			}else{
//				if(lType==1){//点赞,对文章喜欢|不喜欢数操作
//					List<UserLikeFavoriteDTO> list= new UserLikeFavariteServiceImpl().getUserLFList(lType,objId,userId);
//					if(list.size()==0){
//						List<ConArticlesContent> ls = new ConArticleDao().getArtContentById(objId);
//						int likeCount = ls.get(0).getInt("likeCount");
//						int noLikeCount = ls.get(0).getInt("noLikeCount");
//						String avobjId = ls.get(0).getObjectId();
//						if(istatus==0){
//							likeCount+=1;
//						}else{
//							noLikeCount+=1;
//						}
//						AVObject avo = new AVObject("con_articles_content");
//						avo.setObjectId(avobjId);
//						avo.put("likeCount",likeCount);
//						avo.put("noLikeCount",noLikeCount);
//						avo.save();
//					}else {
//						resultCode = Constants.CODE_PARAMS_FAIL;
//						resultMsg = "您已操作过,不能重复操作";
//						resultMap.put("code", resultCode);
//						resultMap.put("msg", resultMsg);
//						return JSON.toJSONString(resultMap);
//					}
//				}
//				UserLikeFavorite avoObj=new UserLikeFavorite();
//				avoObj.put("articleObj", AVObject.createWithoutData("conarticle", objId));
//				avoObj.put("userObj",AVObject.createWithoutData("_User", userId));
//				avoObj.setLType(lType);
//				avoObj.setStatus(istatus);
//				doUserLF(avoObj,lType,objId,userId,istatus);
//			}
//		}else {
//			resultCode = Constants.CODE_PARAMS_FAIL;
//			resultMsg = "objId userId不能为空";
//		}
//
//		resultMap.put("code", resultCode);
//		resultMap.put("msg", resultMsg);
//
//		return JSON.toJSONString(resultMap);
//	}
//
//	public static void doUserLF(UserLikeFavorite avoObj,int lType,String artId,String userId,int istatus){
//		List<UserLikeFavoriteDTO> list= new UserLikeFavariteServiceImpl().getUserLFList(lType,artId,userId);
//		if(list.size()>0){
//			avoObj.setObjectId(list.get(0).getId());
//			if(list.get(0).getStatus()!=istatus){
//				new UserLikeFavoriteDao().saveObj(avoObj);
//			}
//		}else{
//			if(lType==1){
//				new UserLikeFavoriteDao().saveObj(avoObj);
//			}else {
//				if(istatus!=-1){
//					new UserLikeFavoriteDao().saveObj(avoObj);
//				}
//			}
//		}
//	}

    /**
     * 点赞收藏 istatus 0 添加  -1 取消
     * lType  1  点赞  2   收藏
     */
    @EngineFunction("userLikeFivorate")
    public static String userLikeFivorate(@EngineFunctionParam("objId") String objId,
                                          @EngineFunctionParam("lType") int lType,
                                          @EngineFunctionParam("imei") String imei,
                                          @EngineFunctionParam("istatus") int istatus) throws AVException {
        int resultCode = Constants.CODE_SUCCESS;
        String resultMsg = "成功";
        Map<String, Object> resultMap = new HashMap<String, Object>();

        AVQuery<AVObject> queryConArticleContent = new AVQuery<AVObject>("con_articles_content");

        AVObject avObjectConArticleContent = new AVObject("con_articles_content");
        AVObject avObjectUserLikeFavorite = new AVObject("UserLikeFavorite");
        String userId = "";
        AVUser user = AVUser.getCurrentUser();
        if (user != null) {
            userId = user.getObjectId();
            logger.info("登录用户id==>" + user.getObjectId());
        } else {
            logger.info("未登录用户");
        }
        if (StringUtils.isNotBlank(objId)) {
            if (StringUtils.isNotBlank(imei)) {

                if (lType == 1) {//点赞,对文章喜欢|不喜欢数操作
                    List<AVObject> list = getUserFLSize(lType, objId, imei);
                    if (list.size() == 0) {
                        queryConArticleContent.whereEqualTo("articleObj", AVObject.createWithoutData("conarticle", objId));
                        List<AVObject> ls = queryConArticleContent.find();
                        int likeCount = ls.get(0).getInt("likeCount");
                        int noLikeCount = ls.get(0).getInt("noLikeCount");
                        String avobjId = ls.get(0).getObjectId();
                        if (istatus == 0) {
                            likeCount += 1;
                        } else {
                            noLikeCount += 1;
                        }
                        avObjectConArticleContent.setObjectId(avobjId);
                        avObjectConArticleContent.put("likeCount", likeCount);
                        avObjectConArticleContent.put("noLikeCount", noLikeCount);
                        avObjectConArticleContent.save();

                        avObjectUserLikeFavorite.put("articleObj", AVObject.createWithoutData("conarticle", objId));
                        if (StringUtils.isNotBlank(userId)) {
                            avObjectUserLikeFavorite.put("userObj", AVObject.createWithoutData("_User", userId));
                        }
                        avObjectUserLikeFavorite.put("lType", lType);
                        avObjectUserLikeFavorite.put("status", istatus);
                        avObjectUserLikeFavorite.put("imei", imei);
                        new UserLikeFavoriteDao().saveAVObj(avObjectUserLikeFavorite);
                    } else {
                        resultCode = Constants.CODE_PARAMS_FAIL;
                        resultMsg = "您已操作过,不能重复操作";
                        resultMap.put("code", resultCode);
                        resultMap.put("msg", resultMsg);
                        return JSON.toJSONString(resultMap);
                    }
                } else {//收藏
                    String[] objIdss = objId.split(",");
                    for (String objIds : objIdss) {

                        avObjectUserLikeFavorite.put("articleObj", AVObject.createWithoutData("conarticle", objIds));
                        if (StringUtils.isNotBlank(userId)) {
                            avObjectUserLikeFavorite.put("userObj", AVObject.createWithoutData("_User", userId));
                            avObjectUserLikeFavorite.put("synTmp", 1);
                        } else {
                            avObjectUserLikeFavorite.put("synTmp", 0);
                        }
                        avObjectUserLikeFavorite.put("lType", lType);
                        avObjectUserLikeFavorite.put("status", istatus);
                        avObjectUserLikeFavorite.put("imei", imei);
                        List<AVObject> list = getUserFLSize(lType, objIds, imei);
                        if (list.size() > 0) {
                            avObjectUserLikeFavorite.setObjectId(list.get(0).getObjectId());
                            new UserLikeFavoriteDao().saveAVObj(avObjectUserLikeFavorite);
                        } else {
                            if (istatus != -1) {
                                new UserLikeFavoriteDao().saveAVObj(avObjectUserLikeFavorite);
                            }
                        }
                    }
                }
            } else {
                resultCode = Constants.CODE_PARAMS_FAIL;
                resultMsg = "imei不能为空";
                resultMap.put("code", resultCode);
                resultMap.put("msg", resultMsg);
                return JSON.toJSONString(resultMap);
            }
        } else {
            resultCode = Constants.CODE_PARAMS_FAIL;
            resultMsg = "objId不能为空";
            resultMap.put("code", resultCode);
            resultMap.put("msg", resultMsg);
            return JSON.toJSONString(resultMap);
        }
        resultMap.put("code", resultCode);
        resultMap.put("msg", resultMsg);
        return JSON.toJSONString(resultMap);
    }

    public static List<AVObject> getUserFLSize(int lType, String artId, String imei) throws AVException {
        AVQuery<AVObject> queryUserLikeFavorite = new AVQuery<AVObject>("UserLikeFavorite");
        queryUserLikeFavorite.whereEqualTo("lType", lType);
        queryUserLikeFavorite.whereEqualTo("articleObj", AVObject.createWithoutData("conarticle", artId));
        queryUserLikeFavorite.whereEqualTo("imei", imei);
        return queryUserLikeFavorite.find();
    }

    /**
     * 提交/删除评论
     *
     * @param articleId 文章id
     * @param userId    用户id
     * @param commentId 评论id
     * @param content   评论内容
     * @param status    0 添加  -1删除
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
        if (StringUtils.isNotBlank(articleId)) {
            if (StringUtils.isNotBlank(userId)) {
                AVObject avoobj = new AVObject("UserComment");
                AVQuery<AVObject> artavQuery = new AVQuery<>("conarticle");
                AVObject artObject = artavQuery.get(articleId);
                AVQuery<AVObject> useravQuery = new AVQuery<>("_User");
                AVObject userObject = useravQuery.get(userId);
                avoobj.put("userObj", userObject);
                avoobj.put("articleObj", artObject);
                if (status != -1) {//添加评论
                    avoobj.put("status", status);
                    avoobj.put("content", content);
                    new UserCommentDao().saveObj(avoobj);
                } else {//删除
                    AVQuery<AVObject> commentavQuery = new AVQuery<>("UserComment");
                    AVObject commentObj = commentavQuery.get(commentId);
                    if (commentObj.getInt("status") != status) {
                        avoobj.setObjectId(commentObj.getObjectId());
                        avoobj.put("status", status);
                        new UserCommentDao().saveObj(avoobj);
                    } else {
                        resultCode = Constants.CODE_PARAMS_FAIL;
                        resultMsg = "状态错误";
                        resultMap.put("code", resultCode);
                        resultMap.put("msg", resultMsg);
                        return JSON.toJSONString(resultMap);
                    }
                }
            } else {
                resultCode = Constants.CODE_PARAMS_FAIL;
                resultMsg = "请登录";
            }
        } else {
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
        if (StringUtils.isNotBlank(articleId)) {
            List<AVObject> ls = new UserCommentDao().getArtCommentList(articleId, createTime);
            List<UserCommentDTO> resList = buildCommentDTOList(ls);
            resultMap.put("commentList", resList);
        } else {
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
        for (AVObject aVObject : tmp) {
            userCommentDTO = UserCommentDTO.AVOBbjToDTO(aVObject);
            if (null != userCommentDTO) {
                DTOList.add(userCommentDTO);
            }
        }
        return DTOList;
    }

    @EngineFunction("userFavoriteList")
    public static String userFavoriteList(@EngineFunctionParam("createTime") long createTime,
                                          @EngineFunctionParam("imei") String imei) throws AVException, ParseException {

        AVUser user = AVUser.getCurrentUser();
        int resultCode = Constants.CODE_SUCCESS;
        String resultMsg = "成功";
        Map<String, Object> resultMap = new HashMap<String, Object>();

        AVQuery avQueryUserLikeFavorite = new AVQuery("UserLikeFavorite");
        avQueryUserLikeFavorite.whereEqualTo("status", 0);
        if (user != null) {
            avQueryUserLikeFavorite.whereEqualTo("userObj", AVObject.createWithoutData("_User", user.getObjectId()));
        }
        avQueryUserLikeFavorite.whereEqualTo("imei", imei);
        avQueryUserLikeFavorite.whereEqualTo("lType", 2);
        if (createTime != 0) {
//            avQueryUserLikeFavorite.whereLessThan("createdAt", DateUtil.Long2StringUTC(createTime, DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1));
            avQueryUserLikeFavorite.whereLessThan("createdAt",DateUtil.long2Date(createTime-1000));
        }
        avQueryUserLikeFavorite.addDescendingOrder("createdAt");
        avQueryUserLikeFavorite.include("articleObj");
        avQueryUserLikeFavorite.limit(20);
        List<AVObject> list = avQueryUserLikeFavorite.find();
        List<UserLikeFavoriteDTO> resList = buildUserLikeFavoriteDtoList(list);

        resultMap.put("code", resultCode);
        resultMap.put("msg", resultMsg);
        resultMap.put("favoriteList", resList);

        return JSON.toJSONString(resultMap);

    }

    private static List<UserLikeFavoriteDTO> buildUserLikeFavoriteDtoList(List<AVObject> tmp) {
        UserLikeFavoriteDTO userLikeFavoriteDTO;
        List<UserLikeFavoriteDTO> DTOList = new ArrayList<UserLikeFavoriteDTO>();
        for (AVObject avObject : tmp) {
            userLikeFavoriteDTO = UserLikeFavoriteDTO.avobjectToDto(avObject);
            if (null != userLikeFavoriteDTO) {
                DTOList.add(userLikeFavoriteDTO);
            }
        }
        return DTOList;
    }

}
