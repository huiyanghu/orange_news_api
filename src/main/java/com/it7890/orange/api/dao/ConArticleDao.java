package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;


public class ConArticleDao {
    private static Logger logger = LogManager.getLogger(ConArticleDao.class);

    public List<ConArticle> getArticlesList(long ltime, int direct) {
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        StringBuffer cql = new StringBuffer();
        cql.append("select include titlePicObj,include publicationObj,include topicObj, include titlePicObjArr,* from conarticle where objectId is exists");
        String timeAt = "";

        if(ltime != 0){
            if (direct == 0){
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_0);
                cql.append(" and createdAt > date(?) limit ? order by createdAt desc");
            } else if (direct == 1) {
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1);
                logger.info("recommed timeAt long===>>"+ltime);
                logger.info("recommed timeAt utc1====>>"+timeAt);
                cql.append(" and createdAt < date(?) limit ? order by createdAt desc");
            }
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class,timeAt,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {//首次刷新
            cql.append(" limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class,10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return articlesList;
    }

    public List<ConArticle> getArticleById(String articleid){
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select include titlePicObj,* from conarticle where status = ? and objectId = ?";
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

    public List<ConArticle> getTopicsArticlesList(String tid,long ltime, int direct) {//direct 0下拉 1上拉,默认0
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        StringBuffer cql = new StringBuffer();
        cql.append("select include titlePicObj,include publicationObj, include titlePicObjArr, * from conarticle where status = ? and topicObj = pointer('AppTopics', ?)") ;
        String timeAt = "";
        if(ltime != 0){
            if(direct == 0){
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_0);
                cql.append(" and createdAt > date(?) limit ? order by createdAt desc");
            } else if (direct == 1) {
                timeAt = DateUtil.Long2StringUTC(ltime,DateUtil.FORMATER_UTC_YYYY_MM_DD_HH_MM_SS_1);
                logger.info("topic timeAt long===>>"+ltime);
                logger.info("topic timeAt utc1====>>"+timeAt);
                cql.append(" and createdAt < date(?) limit ? order by createdAt desc");
            }
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0, tid, timeAt, 10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        }else {//首次刷新
            cql.append(" limit ? order by createdAt desc");
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql.toString(), ConArticle.class, 0, tid, 10);
                articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return articlesList;
    }

    public List<AVObject> getTopicsArticlesList1(String countryCode,String tid,long ltime, int direct,int limit) throws ParseException {//direct 0下拉 1上拉,默认0
        List<AVObject> articlesList = new ArrayList<AVObject>();
        AVQuery queryConarticles = new AVQuery("conarticle");
        queryConarticles.include("titlePicObj");
        queryConarticles.include("titlePicObjArr");
        queryConarticles.include("publicationObj");
        queryConarticles.include("topicObj");
        queryConarticles.whereEqualTo("status",0);
        queryConarticles.whereEqualTo("countrycode",countryCode);
        if(StringUtils.isNotBlank(tid)){
            queryConarticles.whereEqualTo("topicObj", AVObject.createWithoutData("AppTopics",tid));
        }
        queryConarticles.addDescendingOrder("createdAt");
        queryConarticles.limit(limit);

        int defaultSkip = 100;  // 跳过指定新闻条数
        if(ltime != 0){
            if(direct == 0){
                // 1、下拉刷新时，新闻条数大于指定条数，则直接从跳过条数开始，否则正常下拉刷新加载
                int newsArticleCount = getNewsArticleCount(countryCode, tid, ltime);
                logger.info("newsArticleCount: {}", newsArticleCount);
                if (newsArticleCount > defaultSkip) {
                    queryConarticles.skip(defaultSkip);
                } else {
                    queryConarticles.skip(newsArticleCount - limit);

                    logger.info("下拉刷新");
                    Date date = DateUtil.long2Date(ltime + 1000);
                    logger.info("date time====>" + DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS, date));
                    queryConarticles.whereGreaterThan("createdAt", date);
                }
            } else if (direct == 1) {
                logger.info("上拉加载");
                Date date = DateUtil.long2Date(ltime-1000);
                logger.info("date time====>"+date);
                queryConarticles.whereLessThan("createdAt",date);
            }
        }else {//首次刷新
            queryConarticles.skip(defaultSkip);  // 首次加载 跳过指定条数最新新闻
        }

        try {
            articlesList = queryConarticles.find();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }

        return articlesList;
    }

    /**
     * 查询最新新闻条数
     * @param countryCode
     * @param topicId
     * @param updateTime
     * @return
     */
    public int getNewsArticleCount(String countryCode, String topicId, long updateTime) {
        int newArticleCount = 0;

        AVQuery query = new AVQuery("conarticle");
        query.whereEqualTo("status", 0);
        query.whereEqualTo("countrycode",countryCode);

        if(StringUtils.isNotBlank(topicId)) {
            query.whereEqualTo("topicObj", AVObject.createWithoutData("AppTopics", topicId));
        }

        Date date = DateUtil.long2Date(updateTime + 1000);
        query.whereGreaterThan("createdAt", date);
        logger.info("date: {}, date str: {}", date, DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS, date));
        try {
            newArticleCount = query.count();
        } catch (AVException e) {
            e.printStackTrace();
        }

        return newArticleCount;
    }


    public List<ConArticlesContent> getArtContentById(String articleid){
        List<ConArticlesContent> articlesList = new ArrayList<ConArticlesContent>();
        String cql = " select include articleObj,include articleObj.topicObj,include articleObj.publicationObj,include articleObj.titlePicObjArr,* from con_articles_content where articleObj = pointer('conarticle', ?)";
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticlesContent.class,articleid);
            articlesList = (List<ConArticlesContent>)avCloudQueryResult.getResults();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return articlesList;
    }

    /**
     * 按标题模糊查询
     * @param keywords 关键字
     * @param date 查询时间
     * @param direct 0下拉 1上拉, 默认0
     * @return
     */
    public List<AVObject> findArticleListByKeywords(String keywords, Date date, int direct) {
        List<AVObject> articleList = null;
        if (StringUtil.isNotEmpty(keywords)) {
            AVQuery query = new AVQuery("conarticle");
            query.include("titlePicObjArr");
            query.include("publicationObj");
            query.whereContains("title", keywords);
            query.addDescendingOrder("createdAt");
            query.limit(10);

            if (direct == 0) {
                query.whereGreaterThan("createdAt", date);
            } else {
                query.whereLessThan("createdAt", date);
            }
            try {
                articleList = query.find();
            } catch (AVException e) {
                e.printStackTrace();
            }
        }
        return articleList;
    }

    public List<AVObject> getArtByPubId(String pubId,long time,int direct){
        List<AVObject> ls = null;
        AVQuery query = new AVQuery("conarticle");
        query.include("titlePicObjArr");
        query.include("publicationObj");
        query.addDescendingOrder("createdAt");
        query.limit(10);
        if (StringUtils.isNotBlank(pubId)){
            query.whereEqualTo("publicationObj",AVObject.createWithoutData("con_publications",pubId));
        }
        if (time!=0){
            if(direct == 0){
                Date date = DateUtil.long2Date(time+1000);
                query.whereGreaterThan("createdAt",date);
            } else if (direct == 1) {
                Date date = DateUtil.long2Date(time-1000);
                query.whereLessThan("createdAt",date);
            }
        }
        try {
            ls = query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return ls;
    }
}
