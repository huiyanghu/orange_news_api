package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * @author gg
 * @create 2017/6/14
 **/
public interface IUserRssPublicationService {
    public List<AVObject> getList(String hid,String imei);
}
