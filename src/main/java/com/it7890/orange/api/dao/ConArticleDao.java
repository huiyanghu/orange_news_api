package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;

import java.util.ArrayList;
import java.util.List;


public class ConArticleDao {

    public List<ConArticle> getArticlesList() {
        List<ConArticle> articlesList = new ArrayList<ConArticle>();
        String cql = " select * from conarticle where status = ?";
        try {
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, ConArticle.class,0);
            articlesList = (List<ConArticle>) avCloudQueryResult.getResults();
        } catch (Exception e) {
            e.getMessage();
        }
        return articlesList;
    }


}
