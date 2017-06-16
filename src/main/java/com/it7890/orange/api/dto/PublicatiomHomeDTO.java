package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVObject;

/**
 * @author gg
 * @create 2017/6/16
 **/
public class PublicatiomHomeDTO {
    private String pubName;
    private String pubLogo;
    private int subCount;
    private int tmpPub;//订阅状态

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubLogo() {
        return pubLogo;
    }

    public void setPubLogo(String pubLogo) {
        this.pubLogo = pubLogo;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public int getTmpPub() {
        return tmpPub;
    }

    public void setTmpPub(int tmpPub) {
        this.tmpPub = tmpPub;
    }

    public static PublicatiomHomeDTO buildDTO(AVObject avo,int tmpPub){
        PublicatiomHomeDTO publicatiomHomeDTO = null;
        if (avo!=null){
            publicatiomHomeDTO = new PublicatiomHomeDTO();
            if(avo.getAVFile("logoFileObj")!=null){
                publicatiomHomeDTO.setPubLogo(avo.getAVFile("logoFileObj").getUrl());
            }
            publicatiomHomeDTO.setPubName(avo.getString("name"));
            publicatiomHomeDTO.setSubCount(avo.getInt("subCount"));
            publicatiomHomeDTO.setTmpPub(tmpPub);
        }
        return publicatiomHomeDTO;
    }
}
