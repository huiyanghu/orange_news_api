package com.it7890.orange.api.util;

import redis.clients.jedis.Jedis;

/**
 * Created by Astro on 17/6/27.
 */
public class JedisUtil {

	private static final String redisUrl = System.getenv("REDIS_URL_orange_news");

	public static Jedis REDIS_CLIENT = new Jedis(redisUrl);

}
