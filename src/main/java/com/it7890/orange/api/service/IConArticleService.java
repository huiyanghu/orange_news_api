package com.it7890.orange.api.service;

import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.entity.ConArticle;

import java.util.List;


public interface IConArticleService {
	public List<ConArticleDTO> getArticlesList();

	public List<ConArticleDTO> getArticleById(String articleid);
}
