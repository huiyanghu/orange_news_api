package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.entity.HbCountrys;

import java.util.ArrayList;
import java.util.List;


public class HbCountrysDao {

    public List<HbCountrys> getCountryByCountryCode(String countryCode) {
        List<HbCountrys> cList = new ArrayList<HbCountrys>();
        if (countryCode != null && !"".equals(countryCode)) {
            String cql = " select * from hb_countrys where code = ?";
            try {
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql, HbCountrys.class, countryCode);
                cList = (List<HbCountrys>) avCloudQueryResult.getResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cList;
    }
}
