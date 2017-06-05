package com.it7890.orange.api.cloud;


import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dto.UserRssPublicationDTO;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRssPublicationCloud {

    private static Logger logger = LogManager.getLogger(UserRssPublicationCloud.class);

    /**
     * 订阅取消媒体
     * publicationId 媒体id
     * imei 设备号
     * status  取消-1 添加0
     */
    @EngineFunction("postUserRssPublication")
    public static String userRssPublication(@EngineFunctionParam("publicationIds") String publicationIds,
                                            @EngineFunctionParam("imei") String imei,
                                            @EngineFunctionParam("status") int status) throws AVException {
        int resultCode = Constants.CODE_SUCCESS;
        String resultMsg = "成功";
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String userId = "";
        AVUser user = AVUser.getCurrentUser();
        if (user != null) {
            userId = user.getObjectId();
            logger.info("登录用户id==>" + user.getObjectId());
        } else {
            logger.info("未登录用户");
        }

        AVObject avObjectUserRssPublication = null;
        AVObject publicationObj = null;
        int subCount;

        String[] publicationIdss = publicationIds.split(",");
        for (String publicationId : publicationIdss) {
            avObjectUserRssPublication = new AVObject("UserRssPublication");
            publicationObj = getPublicationObj(publicationId);
            subCount = publicationObj.getInt("subCount");
            avObjectUserRssPublication.put("publicationObj", AVObject.createWithoutData("con_publications", publicationId));
            if (StringUtils.isNotBlank(userId)) {
                avObjectUserRssPublication.put("userObj", AVObject.createWithoutData("_User", userId));
                avObjectUserRssPublication.put("synTmp", 1);
            } else {
                avObjectUserRssPublication.put("synTmp", 0);
            }
            avObjectUserRssPublication.put("imei", imei);
            List<AVObject> list = getUserPlSize(publicationId, imei);
            if (status != -1) {
                if (list.size() > 0) {
                    avObjectUserRssPublication.setObjectId(list.get(0).getObjectId());
                } else {
                    subCount += 1;
                    publicationObj.put("subCount", subCount);
                    publicationObj.save();
                }
                avObjectUserRssPublication.save();
            } else {
                if (list.size() > 0) {
                    list.get(0).delete();
                    subCount--;
                    publicationObj.put("subCount", subCount);
                    publicationObj.save();
                } else {
                    resultCode = Constants.CODE_PARAMS_FAIL;
                    resultMsg = "参数错误";
                }
            }
        }

        resultMap.put("code", resultCode);
        resultMap.put("msg", resultMsg);
        return JSON.toJSONString(resultMap);
    }

    public static List<AVObject> getUserPlSize(String pulicationId, String imei) throws AVException {
        AVQuery<AVObject> queryUserLikeFavorite = new AVQuery<AVObject>("UserRssPublication");
        queryUserLikeFavorite.whereEqualTo("publicationObj", AVObject.createWithoutData("conarticle", pulicationId));
        queryUserLikeFavorite.whereEqualTo("imei", imei);
        return queryUserLikeFavorite.find();
    }

    public static AVObject getPublicationObj(String publicationId) throws AVException {
        AVQuery<AVObject> queryObj = new AVQuery<>("con_publications");
        return queryObj.get(publicationId);
    }


    /**
     * 订阅媒体列表
     *
     * @param createTime
     * @param imei
     * @return
     * @throws AVException
     */
    @EngineFunction("userRssPublicationList")
    public static String userRssPublicationList(@EngineFunctionParam("createTime") long createTime,
                                                @EngineFunctionParam("imei") String imei) throws AVException, ParseException {

        AVUser user = AVUser.getCurrentUser();
        int resultCode = Constants.CODE_SUCCESS;
        String resultMsg = "成功";
        Map<String, Object> resultMap = new HashMap<String, Object>();

        AVQuery avQueryUserRssPublication = new AVQuery("UserRssPublication");
        if (user != null) {
            avQueryUserRssPublication.whereEqualTo("userObj", AVObject.createWithoutData("_User", user.getObjectId()));
        }
        avQueryUserRssPublication.whereEqualTo("imei", imei);
        if (createTime != 0) {
//            avQueryUserRssPublication.whereLessThan("createdAt", DateUtil.Long2StringUTC(createTime, DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1));
            avQueryUserRssPublication.whereLessThan("createdAt",DateUtil.long2Date(createTime-1000));
        }
        avQueryUserRssPublication.addDescendingOrder("createdAt");
        avQueryUserRssPublication.include("publicationObj");
        avQueryUserRssPublication.limit(20);
        List<AVObject> list = avQueryUserRssPublication.find();
        List<UserRssPublicationDTO> resList = buildUserRssPublicationDtoList(list);

        resultMap.put("code", resultCode);
        resultMap.put("msg", resultMsg);
        resultMap.put("publicationList", resList);

        return JSON.toJSONString(resultMap);

    }

    private static List<UserRssPublicationDTO> buildUserRssPublicationDtoList(List<AVObject> tmp) {
        UserRssPublicationDTO userRssPublicationDTO;
        List<UserRssPublicationDTO> DTOList = new ArrayList<UserRssPublicationDTO>();
        for (AVObject avObject : tmp) {
            userRssPublicationDTO = UserRssPublicationDTO.avobjectToDto(avObject);
            if (null != userRssPublicationDTO) {
                DTOList.add(userRssPublicationDTO);
            }
        }
        return DTOList;
    }
}
