package com.it7890.orange.api;

import com.it7890.orange.api.dao.AppAdvertDao;
import com.it7890.orange.api.service.impl.AppAdvertServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by Astro on 17/5/15.
 */
public class App {
	public static void main(String[] arg1) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		AppAdvertDao appAdvertDao = appContext.getBean(AppAdvertDao.class);

		System.out.println("=======11111111>" + appAdvertDao);

		AppAdvertServiceImpl appAdvertServiceImpl = appContext.getBean(AppAdvertServiceImpl.class);
		System.out.println("=======22222222>" + appAdvertServiceImpl);
		System.out.println("=======33333333>" + appAdvertServiceImpl.appAdvertDao);
	}
}
