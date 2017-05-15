package com.it7890.orange.api.entity;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.HashMap;
import java.util.Map;


@AVClassName("hb_countrys")
public class HbCountrys extends AVObject {

    public HbCountrys(){
        super();
    }

//    public int getStatus() {
//        return getInt("status");
//    }
//
//    public void setStatus(int status) {
//        this.put("status", status);
//    }
//
//    public int getLangid() {
//        return getInt("langid");
//    }
//
//    public void setLangid(int langid) {
//        this.put("langid", langid);
//    }
//
//    public String getCountryicon() {
//        return getString("countryicon");
//    }
//
//    public void setCountryicon(String countryicon) {
//        this.put("countryicon", countryicon);
//    }
//
//    public String getShortname() {
//        return getString("shortname");
//    }
//
//    public void setShortname(String shortname) {
//        this.put("shortname", shortname);
//    }
//
//    public String getCnname() {
//        return getString("cnname");
//    }
//
//    public void setCnname(String cnname) {
//        this.put("cnname", cnname);
//    }
//
//    public String getEnname() {
//        return getString("enname");
//    }
//
//    public void setEnname(String enname) {
//        this.put("enname", enname);
//    }
//
//    public String getCode() {
//        return getString("code");
//    }
//
//    public void setCode(String code) {
//        this.put("code", code);
//    }
//
//    public String getContinentid() {
//        return getString("continentid");
//    }
//
//    public void setContinentid(String continentid) {
//        this.put("continentid", continentid);
//    }
//
//    public String getContinent() {
//        return getString("continent");
//    }
//
//    public void setContinent(String continent) {
//        this.put("continent", continent);
//    }
//
//    public int getIsopen() {
//        return getInt("isopen");
//    }
//
//    public void setIsopen(int isopen) {
//        this.put("isopen", isopen);
//    }

    @Override
    public String toString() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("cnname", this.getString("cnname"));
        result.put("enname", this.getString("enname"));
        result.put("shortname", this.getString("shortname"));
        result.put("countryicon", this.getString("countryicon"));
        result.put("code", this.getString("code"));
        result.put("continentid", this.getString("continentid"));
        result.put("continent", this.getString("continent"));
        result.put("isopen", this.getInt("isopen"));
        result.put("langid", this.getInt("langid"));
        result.put("status", this.getInt("status"));
        result.put("objectId", this.getObjectId());
        result.put("createdAt", this.getCreatedAt());
        return JSON.toJSONString(result);
    }

}
