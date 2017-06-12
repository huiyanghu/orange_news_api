package com.it7890.orange.api.service;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.dto.UserLikeFavoriteDTO;
import java.util.List;


public interface IUserLikeFavoriteService {
	public List<UserLikeFavoriteDTO> getUserLFList(int lType,String artId,String userId);

	public List<AVObject> getLikeList(int lType,String artId,String imei);
}
