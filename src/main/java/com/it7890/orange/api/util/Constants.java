package com.it7890.orange.api.util;

/**
 * Created by Astro on 17/5/15.
 */
public class Constants {

	/**-------------------- 操作结果code --------------------**/
	/** 操作成功 **/
	public final static int CODE_SUCCESS = 1;
	/** 参数错误 **/
	public final static int CODE_PARAMS_FAIL = 0;
	/** 操作失败，服务端异常 **/
	public final static int CODE_SERVER_FAIL = 2;
	/** 重复操作提示 **/
	public final static int CODE_REPEAT = 3;
	/** 目标未找到，如用户不存在  **/
	public final static int CODE_CANNOT_FIND = 4;
	/** 授权过期 **/
	public final static int CODE_AUTHORIZE_OVERDUE = 101;
	/** 无效授权 **/
	public final static int CODE_AUTHORIZE_FAIL = 102;

	/**-------------------- Leancloud错误码 --------------------**/
	/** 用户名为空 **/
	public final static int CODE_USERNAME_EMPTY = 200;
	/** 密码为空 **/
	public final static int CODE_PASSWORD_EMPTY = 201;
	/** 用户名已经被占用 **/
	public final static int CODE_USERNAME_EXIST = 202;
	/** 电子邮箱地址已经被占用 **/
	public final static int CODE_EMAIL_EXIST = 203;
	/** 没有提供电子邮箱地址 **/
	public final static int CODE_EMAIL_EMPTY = 204;
	/**  用户名或密码不匹配 **/
	public final static int CODE_USER_UNMATCH = 210;
}
