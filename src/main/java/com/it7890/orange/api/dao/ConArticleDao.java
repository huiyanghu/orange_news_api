package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ConArticleDao {
    private static Logger logger = LogManager.getLogger(ConArticleDao.class);

    public List<ConArticle> getArticlesList(String time,int direct) {
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        StringBuffer cql = new StringBuffer();
        cql.append("select include titlePicObj, * from conarticle where status = ?");
        String timeAt = "";
        long ltime = 0;
        if(StringUtils.isNotEmpty(time)){
            try {
                ltime = DateUtil.stringToLong(time,DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS);
//                timeAt = DateUtil.befor8HoursLong2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (direct == 0){
                logger.info("RECOMMtimeAt00000000000000000::::"+timeAt);
                cql.append(" and createdAt > date(?) limit ? order by createdAt desc");
            } else if (direct == 1) {
                logger.info("RECOMMtimeAt11111111111111111111::::"+timeAt);
                cql.append(" and createdAt < date(?) limit ? order by createdAt desc");
            }
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class,0,timeAt,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {//首次刷新
            cql.append(" limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class,0,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return articlesList;
    }

    public List<ConArticle> getArticleById(String articleid){
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select * from conarticle where status = ? and objectId = ?";
        ConArticle art = new ConArticle();
        try {
//            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticle.class, Arrays.asList(0,articleid));
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticle.class,0,articleid);
            articlesList = (List<ConArticle>)avCloudQueryResult.getResults();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return articlesList;
    }

    public List<ConArticle> getTopicsArticlesList(String tid,String time,int direct) {//direct 0下拉 1上拉,默认0
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        StringBuffer cql = new StringBuffer();
        cql.append("select * from conarticle where status = ? and topicObj = pointer('AppTopics', ?)") ;
        String timeAt = "";
        long ltime = 0;
        if(StringUtils.isNotEmpty(time)){
            try {
                ltime = DateUtil.stringToLong(time,DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS);
//                timeAt = DateUtil.befor8HoursLong2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(direct == 0){
                logger.info("TOPICtimeAt00000000000000000::::"+timeAt);
                cql.append(" and createdAt > date(?) limit ? order by createdAt desc");
            } else if (direct == 1) {
                logger.info("TOPICtimeAt11111111111111111111::::"+timeAt);
                cql.append(" and createdAt < date(?) limit ? order by createdAt desc");
            }
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0,tid,timeAt,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }else {//首次刷新
            cql.append(" limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0,tid,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return articlesList;
    }


}
