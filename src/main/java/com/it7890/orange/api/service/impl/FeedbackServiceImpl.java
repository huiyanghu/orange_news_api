package com.it7890.orange.api.service.impl;

import com.it7890.orange.api.dao.FeedbackDao;
import com.it7890.orange.api.service.IFeedbackService;

/**
 * Created by Astro on 17/6/16.
 */
public class FeedbackServiceImpl implements IFeedbackService {

	@Override
	public String saveNewFeedback(String content, String email) {
		return new FeedbackDao().saveNewFeedback(content, email);
	}
}
