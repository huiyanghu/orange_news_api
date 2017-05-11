package com.orange.cloud;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import cn.leancloud.demo.todo.Todo;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.orange.entity.AppAdvert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astro on 17/5/11.
 */
public class AppAdvertCloud {

	@EngineFunction("startImg")
	public static List<AppAdvert> startimg(@EngineFunctionParam("countryCode") String countryCode) throws AVException {
		AVQuery<AppAdvert> query = AVObject.getQuery(AppAdvert.class);
		query.orderByDescending("createdAt");
		query.whereEqualTo("countryCode", countryCode);
		try {
			return query.find();
		} catch (AVException e) {
			if (e.getCode() == 101) {
				// 该错误的信息为：{ code: 101, message: 'Class or object doesn\'t exists.' }，说明 Todo 数据表还未创建，所以返回空的
				// Todo 列表。
				// 具体的错误代码详见：https://leancloud.cn/docs/error_code.html
				return new ArrayList<>();
			}
			throw e;
		}
	}
}
