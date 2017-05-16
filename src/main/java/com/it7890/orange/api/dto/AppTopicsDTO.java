package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.entity.AppAdvert;
import com.it7890.orange.api.entity.AppTopics;

@AVClassName("AppTopics")
public class AppTopicsDTO {

    private String objectId;
    private String countryId;
    private int topicId;
    private String topicIcon;
    private String topicName;
    private String hubiId;
    private int topicType;
    private String keyWords;
    private int rank;
    private int channelId;
    private int createuId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicIcon() {
        return topicIcon;
    }

    public void setTopicIcon(String topicIcon) {
        this.topicIcon = topicIcon;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getHubiId() {
        return hubiId;
    }

    public void setHubiId(String hubiId) {
        this.hubiId = hubiId;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getCreateuId() {
        return createuId;
    }

    public void setCreateuId(int createuId) {
        this.createuId = createuId;
    }

    public static AppTopicsDTO objectToDto(AppTopics tmp) {
        AppTopicsDTO appTopicsDTO = null;
        if(null != tmp) {
            appTopicsDTO = new AppTopicsDTO();
            appTopicsDTO.setObjectId(tmp.getObjectId());
            appTopicsDTO.setChannelId(tmp.getChannelId());
            appTopicsDTO.setCountryId(tmp.getCountryId());
            appTopicsDTO.setCreateuId(tmp.getCreateuId());
            appTopicsDTO.setHubiId(tmp.getHubiId());
            appTopicsDTO.setKeyWords(tmp.getKeyWords());
            appTopicsDTO.setRank(tmp.getRank());
            appTopicsDTO.setTopicIcon(tmp.getTopicIcon());
            appTopicsDTO.setTopicId(tmp.getTopicId());
            appTopicsDTO.setTopicName(tmp.getTopicName());
            appTopicsDTO.setTopicType(tmp.getTopicType());
        }
        return appTopicsDTO;
    }
}
