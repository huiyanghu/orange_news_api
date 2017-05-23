package com.it7890.orange.api.dto;


import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.util.DateUtil;
import com.it7890.orange.api.util.StringUtil;

public class ConArticleDTO {

    private String abstracts;

    private String articleId;

    private String countryCode;

    private String title;

    private String titlePic;

    public String getTitlePicId() {
        return titlePicId;
    }

    public void setTitlePicId(String titlePicId) {
        this.titlePicId = titlePicId;
    }

    private String titlePicId;

    private int topicsId;

    private String langId;

    private String linkUrl;

    private int attr;

    private String author;

    private int channelId;

    private int createuId;

    private int cType;

    private int imgCount;

    private String keyWords;

    private int latitude;

    private int longitude;

    private String mediaLink;

    private int publicationId;

    private int rank;

    private String source;

    private String sourceUrl;

    private int status;

    private int subuid;

    private String writer;

    private String sourceTitilePic;

    private String starttime;

    private String endtime;

    private int pushNum;

    private String plogo;

    private String authorheadimg;

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    private String creatTime;

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public int getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(int topicsId) {
        this.topicsId = topicsId;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getAttr() {
        return attr;
    }

    public void setAttr(int attr) {
        this.attr = attr;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getcType() {
        return cType;
    }

    public void setcType(int cType) {
        this.cType = cType;
    }

    public int getImgCount() {
        return imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSubuid() {
        return subuid;
    }

    public void setSubuid(int subuid) {
        this.subuid = subuid;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSourceTitilePic() {
        return sourceTitilePic;
    }

    public void setSourceTitilePic(String sourceTitilePic) {
        this.sourceTitilePic = sourceTitilePic;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getPushNum() {
        return pushNum;
    }

    public void setPushNum(int pushNum) {
        this.pushNum = pushNum;
    }

    public String getPlogo() {
        return plogo;
    }

    public void setPlogo(String plogo) {
        this.plogo = plogo;
    }

    public String getAuthorheadimg() {
        return authorheadimg;
    }

    public void setAuthorheadimg(String authorheadimg) {
        this.authorheadimg = authorheadimg;
    }

    public static ConArticleDTO objectToDto(ConArticle tmp) {
        ConArticleDTO conArticleDTO = null;
        if(null != tmp) {
            conArticleDTO = new ConArticleDTO();
            conArticleDTO.setAbstracts(tmp.getAbstracts());
            conArticleDTO.setArticleId(tmp.getArticleid());
            conArticleDTO.setAttr(tmp.getAttr());
            conArticleDTO.setAuthor(tmp.getAuthor());
            conArticleDTO.setAuthorheadimg(tmp.getAuthorheadimg());
            conArticleDTO.setChannelId(tmp.getChannelid());
            conArticleDTO.setCountryCode(tmp.getCountrycode());
            conArticleDTO.setCreateuId(tmp.getCreateuid());
            conArticleDTO.setcType(tmp.getCtype());
            conArticleDTO.setImgCount(tmp.getImgcount());
            conArticleDTO.setKeyWords(tmp.getKeywords());
            conArticleDTO.setLangId(tmp.getLangid());
            conArticleDTO.setLatitude(tmp.getLatitude());
            conArticleDTO.setLinkUrl(tmp.getLinkurl());
//            conArticleDTO.setLinkUrl("");
            conArticleDTO.setLongitude(tmp.getLongitude());
            conArticleDTO.setMediaLink(tmp.getMedialink());
            conArticleDTO.setWriter(tmp.getWriter());
            conArticleDTO.setTopicsId(tmp.getTopicsid());
            conArticleDTO.setTitlePic(tmp.getTitlepic());
            conArticleDTO.setTitle(tmp.getTitle());
            conArticleDTO.setSubuid(tmp.getSubuid());
            conArticleDTO.setStatus(tmp.getStatus());
            conArticleDTO.setSourceUrl(tmp.getSourceurl());
            conArticleDTO.setSourceTitilePic(tmp.getSourcetitilepic());
            conArticleDTO.setRank(tmp.getRank());
            conArticleDTO.setPushNum(tmp.getPushnum());
            conArticleDTO.setPublicationId(tmp.getPublicationid());
            conArticleDTO.setPlogo(tmp.getPlogo());
            conArticleDTO.setCreatTime(DateUtil.formatFromDate(DateUtil.FORMATER_YYYY_MM_DD_HH_MM_SS,tmp.getCreatedAt()));
            conArticleDTO.setTitlePic(null != tmp.getTitlePicObj() ? tmp.getTitlePicObj().getString("ulr") : "");
            conArticleDTO.setTitlePicId(null != tmp.getTitlePicObj() ? tmp.getTitlePicObj().getObjectId() : "");
        }
        return conArticleDTO;
    }
}
