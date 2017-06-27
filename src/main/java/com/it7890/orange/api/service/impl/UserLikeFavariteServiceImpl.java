package com.it7890.orange.api.service.impl;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.it7890.orange.api.dao.UserLikeFavoriteDao;
import com.it7890.orange.api.dto.UserLikeFavoriteDTO;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.service.IUserLikeFavoriteService;
import java.util.ArrayList;
import java.util.List;


public class UserLikeFavariteServiceImpl implements IUserLikeFavoriteService {


	@Override
	public List<UserLikeFavoriteDTO> getUserLFList(int lType,String artId,String userId) {
		List<UserLikeFavorite> ls = new UserLikeFavoriteDao().getUserLFListByCountryId(lType,artId,userId);
		return buildDtoList(ls);
	}

	@Override
	public List<AVObject> getLikeList(int lType, String artId, String imei, String userId) {
		return new UserLikeFavoriteDao().getLikeStatusByArtIdAndImei(lType,artId,imei, userId);
	}

	@Override
	public List<UserLikeFavoriteDTO> getFavList(AVUser user, String imei, long createTime) {
		List<AVObject> ls = new UserLikeFavoriteDao().getFavList(user,imei,createTime);
		return buildUserLikeFavoriteDtoList(ls);
	}

	private static List<UserLikeFavoriteDTO> buildDtoList(List<UserLikeFavorite> tmp) {
		UserLikeFavoriteDTO userLikeFavoriteDTO;
		List<UserLikeFavoriteDTO> DTOList = new ArrayList<UserLikeFavoriteDTO>();
		for(UserLikeFavorite userLikeFavorite : tmp) {
			userLikeFavoriteDTO = UserLikeFavoriteDTO.objectToDto(userLikeFavorite);
			if (null != userLikeFavoriteDTO) {
				DTOList.add(userLikeFavoriteDTO);
			}
		}
		return DTOList;
	}

	private static List<UserLikeFavoriteDTO> buildUserLikeFavoriteDtoList(List<AVObject> tmp) {
		UserLikeFavoriteDTO userLikeFavoriteDTO;
		List<UserLikeFavoriteDTO> DTOList = new ArrayList<UserLikeFavoriteDTO>();
		for (AVObject avObject : tmp) {
			userLikeFavoriteDTO = UserLikeFavoriteDTO.avobjectToDto(avObject);
			if (null != userLikeFavoriteDTO) {
				DTOList.add(userLikeFavoriteDTO);
			}
		}
		return DTOList;
	}

}
