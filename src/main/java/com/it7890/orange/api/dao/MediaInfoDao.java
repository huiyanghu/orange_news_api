package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.cloud.ConArtilesCloud;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author gg
 * @create 2017/6/12
 **/
public class MediaInfoDao {
    private static Logger logger = LogManager.getLogger(MediaInfoDao.class);
    public AVObject getByFileId(String fileId){
        List<AVObject> l = null;
        AVQuery<AVObject> query = new AVQuery<AVObject>("MediaInfo");
        query.whereEqualTo("fileObj", AVObject.createWithoutData("_File", fileId));
        try {
            l = query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return l.get(0);
    }
}
