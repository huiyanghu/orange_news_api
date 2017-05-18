package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConArticleDao {
    private static Logger logger = LogManager.getLogger(ConArticleDao.class);

    public List<ConArticle> getArticlesList() {
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select * from conarticle where status = ?";
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticle.class, 0);
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
            logger.info("EEEEEEEEEEEEEEEE:::::::::::"+e);
            e.getMessage();
            e.printStackTrace();
        }
        return articlesList;
    }


}
