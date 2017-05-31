package com.it7890.orange.api.service.impl;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.service.IConArticleService;
import com.it7890.orange.api.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConArticleServiceImpl implements IConArticleService {


	@Override
	public List<ConArticleDTO> getArticlesList(long ltime,int direct) {
		List<ConArticle> ls = new ConArticleDao().getArticlesList(ltime,direct);
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
	public List<ConArticleDTO> getTopicsArticlesList(String id, long ltime, int direct) {
		List<ConArticle> ls = new ConArticleDao().getTopicsArticlesList(id, ltime, direct);
		return buildDtoList(ls);
	}

	@Override
	public ConArticleDetailDTO getArtContentById(String articleId) throws IOException {
		ConArticleDetailDTO conArticleDetailDTO = null;
		if (StringUtil.isNotEmpty(articleId)) {
			List<ConArticlesContent> ls = new ConArticleDao().getArtContentById(articleId);
			if (null != ls && ls.size() > 0) {
				conArticleDetailDTO = buildContentDtoList(ls.get(0));
			}
		}
		return conArticleDetailDTO;
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

	private static ConArticleDetailDTO buildContentDtoList(ConArticlesContent tmp) throws IOException {
		return ConArticleDetailDTO.objectToDto(tmp);
	}

}
