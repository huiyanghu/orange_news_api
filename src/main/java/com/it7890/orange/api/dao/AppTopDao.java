package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.cloud.ConArtilesCloud;
import com.it7890.orange.api.entity.AppTop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AppTopDao {
    private static Logger logger = LogManager.getLogger(AppTopDao.class);

    public List<AppTop> getAppTopsList(String countryCode,Long topCreateTime) {
        List<AppTop> appTopsList = new ArrayList<AppTop>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
        if(topCreateTime!=0){
            topCreateTime = topCreateTime - 28800000L;
            String createdAt = df.format(topCreateTime);
            String cql = " select * from AppTop where status = ? and countryCode = ? and createdAt > date(?) ";
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, AppTop.class, Arrays.asList(0, countryCode,createdAt));
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
