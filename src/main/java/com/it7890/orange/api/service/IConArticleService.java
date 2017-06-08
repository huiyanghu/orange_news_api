package com.it7890.orange.api.service;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.ConArticle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface IConArticleService {
	public List<ConArticleDTO> getArticlesList(long ltime,int direct) throws AVException, ParseException;

	public List<ConArticleDTO> getArticleById(String articleid) throws AVException;

	public List<ConArticleDTO> getTopicsArticlesList(String id,long ltime,int direct) throws AVException, ParseException;

	public  ConArticleDetailDTO getArtContentById(String articleid) throws IOException, AVException;

	List<AVObject> findArticleListByKeywords(String keywords, Date date, int direct);
}
