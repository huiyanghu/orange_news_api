package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.ConArticle;

import java.util.ArrayList;
import java.util.List;


public class ConArticleDao {

    public List<ConArticle> getAppTopicsListByCountryCode(String countryCode) {
        List<ConArticle> articlessList = new ArrayList<ConArticle>();
        if (countryCode != null && !"".equals(countryCode)) {
            String cql = " select * from conarticle where status = 0";
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, countryCode);
                articlessList = (List<ConArticle>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return articlessList;
    }


}
