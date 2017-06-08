package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.HbCountrys;

import java.util.ArrayList;
import java.util.List;


public class HbCountrysDao {

    public List<HbCountrys> getCountryByCountryCode(String countryCode) {
        List<HbCountrys> cList = new ArrayList<HbCountrys>();
        if (countryCode != null && !"".equals(countryCode)) {
            String cql = " select * from hb_countrys where countryCode = ?";
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, HbCountrys.class, countryCode);
                cList = (List<HbCountrys>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cList;
    }

    public List<AVObject> findCountryList() {
        List<AVObject> countryList = new ArrayList<>();
        try {
            AVCloudQueryResult queryResult = AVQuery.doCloudQuery("select include iconFileObj, * from hb_countrys where status = 1 order by orderCode asc limit 1000");
            countryList = (List<AVObject>) queryResult.getResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public AVObject findCodeByLangId(String langId){
        AVObject avObject = new AVObject();
        List<AVObject> ls = new ArrayList<>();
        AVQuery avQuery = new AVQuery("hb_countrys");
        avQuery.whereEqualTo("languageObj",AVObject.createWithoutData("hb_languages",langId));
        try {
            ls = avQuery.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return ls.get(0);
    }

}
