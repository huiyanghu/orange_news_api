package com.it7890.orange.api.dto;


import com.avos.avoscloud.AVObject;

public class UserRssPublicationDTO {

    private String lid;//列id
    private String pubId;//媒体id
    private String pubName;//媒体名字
    private String pubLogoUrl;//媒体图片
    private String userId;
    private long createDate;
    private String imei;
    private int synTmp;
    private int userPubNum;

    public String getLid() {

        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getSynTmp() {
        return synTmp;
    }

    public void setSynTmp(int synTmp) {
        this.synTmp = synTmp;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubLogoUrl() {
        return pubLogoUrl;
    }

    public void setPubLogoUrl(String pubLogoUrl) {
        this.pubLogoUrl = pubLogoUrl;
    }

    public int getUserPubNum() {
        return userPubNum;
    }

    public void setUserPubNum(int userPubNum) {
        this.userPubNum = userPubNum;
    }

    public static UserRssPublicationDTO avobjectToDto(AVObject tmp) {
        UserRssPublicationDTO userRssPublicationDTO = null;
        if(null != tmp) {
            userRssPublicationDTO = new UserRssPublicationDTO();
            userRssPublicationDTO.setLid(tmp.getObjectId());
            userRssPublicationDTO.setPubId(tmp.getAVObject("publicationObj").getObjectId());
            userRssPublicationDTO.setPubName(tmp.getAVObject("publicationObj").getString("name"));
            userRssPublicationDTO.setPubLogoUrl(tmp.getAVObject("publicationObj").getString("logourl"));
            userRssPublicationDTO.setCreateDate(tmp.getCreatedAt().getTime());
            userRssPublicationDTO.setUserId(tmp.getAVObject("userObj").getObjectId());
            userRssPublicationDTO.setImei(tmp.getString("imei"));
            userRssPublicationDTO.setSynTmp(tmp.getInt("synTmp"));
            userRssPublicationDTO.setUserPubNum(tmp.getAVObject("publicationObj").getInt("subCount"));
        }
        return userRssPublicationDTO;
    }
}
