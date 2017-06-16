package com.it7890.orange.api.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import com.alibaba.fastjson.JSON;
import com.it7890.orange.api.service.IFeedbackService;
import com.it7890.orange.api.service.impl.FeedbackServiceImpl;
import com.it7890.orange.api.util.Constants;
import com.it7890.orange.api.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Astro on 17/6/16.
 */
public class FeedbackCloud {

	private static final Logger logger = LogManager.getLogger(FeedbackCloud.class);


	/**
	 * 添加
	 * @param content
	 * @param email
	 * @return
	 */
	@EngineFunction("addFeedback")
	public static String addFeedback(@EngineFunctionParam("content") String content, @EngineFunctionParam("email") String email) {
		int resultCode = Constants.CODE_SUCCESS;
		String resultMsg = "成功";

		if (StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(email)) {
			new FeedbackServiceImpl().saveNewFeedback(content, email);
		} else {
			resultCode = Constants.CODE_PARAMS_FAIL;
			resultMsg = "参数错误";
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", resultCode);
		resultMap.put("msg", resultMsg);
		return JSON.toJSONString(resultMap);
	}
}
