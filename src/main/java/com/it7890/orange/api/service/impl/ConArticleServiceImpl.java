package com.it7890.orange.api.service.impl;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dao.ConArticleDao;
import com.it7890.orange.api.dto.ConArticleDTO;
import com.it7890.orange.api.dto.ConArticleDetailDTO;
import com.it7890.orange.api.entity.ConArticle;
import com.it7890.orange.api.entity.ConArticlesContent;
import com.it7890.orange.api.service.IConArticleService;
import com.it7890.orange.api.util.StringUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ConArticleServiceImpl implements IConArticleService {


	@Override
	public List<ConArticleDTO> getArticlesList(String countryCode,long ltime,int direct,int limit) throws AVException, ParseException {
//		List<ConArticle> ls = new ConArticleDao().getArticlesList(ltime,direct);
//		return buildDtoList(ls);
		List<AVObject> ls = new ConArticleDao().getTopicsArticlesList1(countryCode,null, ltime, direct,limit);
		return buildavoDtoList(ls);
	}

	@Override
	public List<ConArticleDTO> getArticleById(String articleid) throws AVException {
		List<ConArticle> ls = new ConArticleDao().getArticleById(articleid);
			return buildDtoList(ls);
	}

	@Override
	public List<ConArticleDTO> getTopicsArticlesList(String countryCode,String id, long ltime, int direct,int limit) throws AVException, ParseException {
//		List<ConArticle> ls = new ConArticleDao().getTopicsArticlesList(id, ltime, direct);
//		return buildDtoList(ls);

		List<AVObject> ls = new ConArticleDao().getTopicsArticlesList1(countryCode,id, ltime, direct,limit);
		return buildavoDtoList(ls);
	}

	@Override
	public ConArticleDetailDTO getArtContentById(String articleId) throws IOException, AVException {
		ConArticleDetailDTO conArticleDetailDTO = null;
		if (StringUtil.isNotEmpty(articleId)) {
			List<ConArticlesContent> ls = new ConArticleDao().getArtContentById(articleId);
			if (null != ls && ls.size() > 0) {
				conArticleDetailDTO = buildContentDtoList(ls.get(0));
			}
		}
		return conArticleDetailDTO;
	}

	@Override
	public List<AVObject> findArticleListByKeywords(String keywords, Date date, int direct) {
		return new ConArticleDao().findArticleListByKeywords(keywords, date, direct);
	}

	@Override
	public List<ConArticleDTO> getArtByPubId(String pubId, long time, int direct) throws AVException {
		List<AVObject> ls = new ConArticleDao().getArtByPubId(pubId,time,direct);
		return buildavoDtoList(ls);
	}

	private static List<ConArticleDTO> buildDtoList(List<ConArticle> tmp) throws AVException {
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

	public static List<ConArticleDTO> buildavoDtoList(List<AVObject> tmp) throws AVException {
		ConArticleDTO conArticleDTO;
		List<ConArticleDTO> DTOList = new ArrayList<ConArticleDTO>();
		for(AVObject aVObject : tmp) {
			conArticleDTO = ConArticleDTO.avobjectToDto(aVObject);
			if (null != conArticleDTO) {
				DTOList.add(conArticleDTO);
			}
		}
		return DTOList;
	}


	private static ConArticleDetailDTO buildContentDtoList(ConArticlesContent tmp) throws IOException, AVException {
		return ConArticleDetailDTO.objectToDto(tmp);
	}

}
