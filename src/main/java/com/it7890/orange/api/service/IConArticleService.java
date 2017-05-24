package com.it7890.orange.api.service;

import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.ConArticle;

import java.io.IOException;
import java.util.List;


public interface IConArticleService {
	public List<ConArticleDTO> getArticlesList(String time,int direct);

	public List<ConArticleDTO> getArticleById(String articleid);

	public List<ConArticleDTO> getTopicsArticlesList(String id,String time,int direct);

	public  ConArticleDetailDTO getArtContentById(String articleid) throws IOException;
}
