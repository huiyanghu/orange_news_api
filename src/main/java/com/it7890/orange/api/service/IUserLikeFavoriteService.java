package com.it7890.orange.api.service;

import com.it7890.orange.api.dto.UserLikeFavoriteDTO;
import java.util.List;


public interface IUserLikeFavoriteService {
	public List<UserLikeFavoriteDTO> getUserLFList(int lType,String artId,String userId);
}