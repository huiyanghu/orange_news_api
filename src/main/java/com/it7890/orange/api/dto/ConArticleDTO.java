package com.it7890.orange.api.dto;


import com.it7890.orange.api.entity.ConArticle;

public class ConArticleDTO {

    private long id;

    private String abstracts;

    private String articleid;

    private String countrycode;

    private String title;

    private String titlepic;

    private int topicsid;

    private String langid;

    private String linkurl;

    private int attr;

    private String author;

    private int channelid;

    private int createuid;

    private int ctype;

    private int imgcount;

    private String keywords;

    private int latitude;

    private int longitude;

    private String medialink;

    private int publicationid;

    private int rank;

    private String source;

    private String sourceurl;

    private int status;

    private int subuid;

    private String writer;

    private String sourcetitilepic;

    private String starttime;

    private String endtime;

    private int pushnum;

    private String plogo;

    private String authorheadimg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlepic() {
        return titlepic;
    }

    public void setTitlepic(String titlepic) {
        this.titlepic = titlepic;
    }

    public int getTopicsid() {
        return topicsid;
    }

    public void setTopicsid(int topicsid) {
        this.topicsid = topicsid;
    }

    public String getLangid() {
        return langid;
    }

    public void setLangid(String langid) {
        this.langid = langid;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
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

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }

    public int getCreateuid() {
        return createuid;
    }

    public void setCreateuid(int createuid) {
        this.createuid = createuid;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public int getImgcount() {
        return imgcount;
    }

    public void setImgcount(int imgcount) {
        this.imgcount = imgcount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getMedialink() {
        return medialink;
    }

    public void setMedialink(String medialink) {
        this.medialink = medialink;
    }

    public int getPublicationid() {
        return publicationid;
    }

    public void setPublicationid(int publicationid) {
        this.publicationid = publicationid;
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

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
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

    public String getSourcetitilepic() {
        return sourcetitilepic;
    }

    public void setSourcetitilepic(String sourcetitilepic) {
        this.sourcetitilepic = sourcetitilepic;
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

    public int getPushnum() {
        return pushnum;
    }

    public void setPushnum(int pushnum) {
        this.pushnum = pushnum;
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
            conArticleDTO.setArticleid(tmp.getArticleid());
            conArticleDTO.setAttr(tmp.getAttr());
            conArticleDTO.setAuthor(tmp.getAuthor());
            conArticleDTO.setAuthorheadimg(tmp.getAuthorheadimg());
            conArticleDTO.setChannelid(tmp.getChannelid());
            conArticleDTO.setCountrycode(tmp.getCountrycode());
            conArticleDTO.setCreateuid(tmp.getCreateuid());
            conArticleDTO.setCtype(tmp.getCtype());
            conArticleDTO.setImgcount(tmp.getImgcount());
            conArticleDTO.setKeywords(tmp.getKeywords());
            conArticleDTO.setLangid(tmp.getLangid());
            conArticleDTO.setLatitude(tmp.getLatitude());
            conArticleDTO.setLinkurl("");
            conArticleDTO.setLongitude(tmp.getLongitude());
            conArticleDTO.setMedialink(tmp.getMedialink());
            conArticleDTO.setWriter(tmp.getWriter());
            conArticleDTO.setTopicsid(tmp.getTopicsid());
            conArticleDTO.setTitlepic(tmp.getTitlepic());
            conArticleDTO.setTitle(tmp.getTitle());
            conArticleDTO.setSubuid(tmp.getSubuid());
            conArticleDTO.setStatus(tmp.getStatus());
            conArticleDTO.setSourceurl(tmp.getSourceurl());
            conArticleDTO.setSourcetitilepic(tmp.getSourcetitilepic());
            conArticleDTO.setRank(tmp.getRank());
            conArticleDTO.setPushnum(tmp.getPushnum());
            conArticleDTO.setPublicationid(tmp.getPublicationid());
            conArticleDTO.setPlogo(tmp.getPlogo());
        }
        return conArticleDTO;
    }
}
