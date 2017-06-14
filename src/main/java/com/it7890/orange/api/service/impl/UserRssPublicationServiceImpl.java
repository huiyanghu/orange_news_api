package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.UserRssPublicationDao;
import com.it7890.orange.api.service.IUserRssPublicationService;

import java.util.List;

/**
 * @author gg
 * @create 2017/6/14
 **/
public class UserRssPublicationServiceImpl implements IUserRssPublicationService {
    @Override
    public List<AVObject> getList(String hid, String imei) {
        return new UserRssPublicationDao().getList(hid,imei);
    }
}
