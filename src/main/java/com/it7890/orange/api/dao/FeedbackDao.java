package com.it7890.orange.api.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.util.StringUtil;

/**
 * Created by Astro on 17/6/16.
 */
public class FeedbackDao {

	public String saveNewFeedback(String content, String email) {
		String feedbackId = "";
		if (StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(email)) {
			AVObject feedbackObj = new AVObject("Feedback");
			feedbackObj.put("content", content);
			feedbackObj.put("email", email);
			try {
				feedbackObj.save();
				feedbackId = feedbackObj.getObjectId();
			} catch (AVException e) {
				e.printStackTrace();
			}
		}
		return feedbackId;
	}
}
