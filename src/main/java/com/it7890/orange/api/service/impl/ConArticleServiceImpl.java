package com.it7890.orange.api.service.impl;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.service.IConArticleService;

import java.util.ArrayList;
import java.util.List;


public class ConArticleServiceImpl implements IConArticleService {


	@Override
	public List<ConArticleDTO> getArticlesList(String time,int direct) {
		List<ConArticle> ls = new ConArticleDao().getArticlesList(time,direct);
		return buildDtoList(ls);
	}

	@Override
	public List<ConArticleDTO> getArticleById(String articleid) {
		List<ConArticle> ls = new ConArticleDao().getArticleById(articleid);
		if(ls.size()>0){
			return buildDtoList(ls);
		}else {
			return null;
		}
	}

	@Override
	public List<ConArticleDTO> getTopicsArticlesList(String id, String time,int direct) {
		List<ConArticle> ls = new ConArticleDao().getTopicsArticlesList(id,time,direct);
		return buildDtoList(ls);
	}

	@Override
	public List<ConArticleDetailDTO> getArtContentById(String articleid) {
		List<ConArticlesContent> ls = new ConArticleDao().getArtContentById(articleid);
		return buildContentDtoList(ls);
	}

	private static List<ConArticleDTO> buildDtoList(List<ConArticle> tmp) {
		ConArticleDTO conArticleDTO;
		List<ConArticleDTO> DTOList = new ArrayList<ConArticleDTO>();
		for(ConArticle conArticle : tmp) {
			conArticleDTO = ConArticleDTO.objectToDto(conArticle);
			if (null != conArticleDTO) {
				DTOList.add(conArticleDTO);
			}
		}
		return DTOList;
	}

	private static List<ConArticleDetailDTO> buildContentDtoList(List<ConArticlesContent> tmp) {
		ConArticleDetailDTO conArticleDetailDTO;
		List<ConArticleDetailDTO> DTOList = new ArrayList<ConArticleDetailDTO>();
		for(ConArticlesContent conArticlesContent : tmp) {
			conArticleDetailDTO = ConArticleDetailDTO.objectToDto(conArticlesContent);
			if (null != conArticleDetailDTO) {
				DTOList.add(conArticleDetailDTO);
			}
		}
		return DTOList;
	}

}
