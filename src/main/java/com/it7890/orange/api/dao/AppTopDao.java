package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.cloud.ConArtilesCloud;
import com.it7890.orange.api.entity.AppTop;
import com.it7890.orange.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AppTopDao {
    private static Logger logger = LogManager.getLogger(AppTopDao.class);

    public List<AppTop> getAppTopsList(String countryCode,String topCreateTime) throws ParseException {
        List<AppTop> appTopsList = new ArrayList<AppTop>();
        if(StringUtils.isNotEmpty(topCreateTime)){
            long ltime = DateUtil.stringToLong(topCreateTime,DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS);
            String createdAt = DateUtil.befor8HoursLong2String(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
            String cql = " select * from AppTop where status = ? and countryCode = ? and createdAt > date(?) ";
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, AppTop.class, 0,countryCode,createdAt);
                appTopsList = (List<AppTop>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.getMessage();
            }
        }else {
            String  cql = " select * from AppTop where status = ? and countryCode = ? ";
            try {
//                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, AppTop.class, Arrays.asList(0,countryCode));
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, AppTop.class, 0,countryCode);
                appTopsList = (List<AppTop>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return appTopsList;
    }


}
