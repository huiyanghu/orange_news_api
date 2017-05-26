package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.dao.AppTopDao;
import com.it7890.orange.api.dto.AppTopDTO;
import com.it7890.orange.api.entity.AppTop;
import com.it7890.orange.api.service.IAppTopService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



public class AppTopServiceImpl implements IAppTopService {

	@Override
	public List<AppTopDTO> getAppTopsList(String countryCode,String topCreateTime) throws ParseException {
		List<AppTop> ls =  new AppTopDao().getAppTopsList(countryCode,topCreateTime);
		if (ls.size()>0){
//			return buildDtoList(ls);
		}else {
			return null;
		}
		return null;
	}

//	private static List<AppTopDTO> buildDtoList(List<AppTop> tmp) {
//		AppTopDTO appTopDTO;
//		List<AppTopDTO> appTopsDTOList = new ArrayList<AppTopDTO>();
//		for(AppTop appTop : tmp) {
//			appTopDTO = AppTopDTO.objectToDto(appTop);
//			if (null != appTopDTO) {
//				appTopsDTOList.add(appTopDTO);
//			}
//		}
//		return appTopsDTOList;
//	}
}
