package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

/**
 * @author gg
 * @create 2017/6/16
 **/
public class PubicationDao {

    public AVObject getById(String id){
        AVObject avObject = new AVObject();
        AVQuery avQuery = new AVQuery("con_publications");
        avQuery.include("logoFileObj");
        try {
            avObject = avQuery.get(id);
        } catch (AVException e) {
            e.printStackTrace();
        }
        return avObject;
    }
}
