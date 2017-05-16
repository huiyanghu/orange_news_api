package com.it7890.orange.api.service.impl;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.service.IConArticleService;

import java.util.List;


public class ConArticleServiceImpl implements IConArticleService {


	@Override
	public List<ConArticle> getArticlesList() {
		return new ConArticleDao().getArticlesList();
	}
}
