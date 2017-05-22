package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConArticleDao {
    private static Logger logger = LogManager.getLogger(ConArticleDao.class);

    public List<ConArticle> getArticlesList() {
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select * from conarticle where status = ? limit ? order by createdAt desc";
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticle.class, 0,10);
            articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
        } catch (Exception e) {
            e.getMessage();
        }
        return articlesList;
    }

    public List<ConArticle> getArticleById(String articleid){
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select * from conarticle where status = ? and articleid = ?";
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

    public List<ConArticle> getTopicsArticlesList(String tid,String time,int direct) {//direct 0上拉 1下拉
        String timeAt = "";
        long ltime = 0;
        if(StringUtils.isNotEmpty(time)){
            try {
                ltime = DateUtil.stringToLong(time,DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS);
                timeAt = DateUtil.befor8HoursLong2String(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        StringBuffer cql = new StringBuffer();
        cql.append("select * from conarticle where status = ? and topicObj = pointer('AppTopics', ?)") ;
        if (StringUtils.isNotEmpty(time)){
//            logger.info("22222222222222222222222"+time);
//            logger.info("22222222222222222222222"+createdAt);
            cql.append(" and createdAt > date(?) limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0,tid,timeAt,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }else {//首次刷新
//            logger.info("11111111111111111111111"+time);
            cql.append(" limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0,tid,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
//        String cql = " select * from conarticle where createdAt > ? and  limit ?";
        return articlesList;

    }


}
