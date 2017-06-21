package com.it7890.orange.api.service;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface IConArticleService {
	public List<ConArticleDTO> getArticlesList(String countryCode,long ltime,int direct,int limit) throws AVException, ParseException;

	public List<ConArticleDTO> getArticleById(String articleid) throws AVException;

	public List<ConArticleDTO> getTopicsArticlesList(String countryCode,String id,long ltime,int direct,int limit) throws AVException, ParseException;

	public  ConArticleDetailDTO getArtContentById(String articleid) throws IOException, AVException;

	List<AVObject> findArticleListByKeywords(String keywords, Date date, int direct);

	public List<ConArticleDTO> getArtByPubId(String pubId, long time, int direct) throws AVException;
}
