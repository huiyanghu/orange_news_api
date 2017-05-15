package com.it7890.orange.api.util;

/**
 * Created by Astro on 17/5/15.
 */
public class Constants {

	/**-------------------- 操作结果code --------------------**/

	/** 操作成功 **/
	public final static int CODE_SUCCESS = 0;
	/** 参数错误 **/
	public final static int CODE_PARAMS_FAIL = 1;
	/** 操作失败，服务端异常 **/
	public final static int CODE_SERVER_FAIL = 2;
	/** 重复操作提示 **/
	public final static int CODE_REPEAT = 3;
	/** 目标未找到，如帖子比存在  **/
	public final static int CODE_CANNOT_FIND = 4;
	/** 授权过期 **/
	public final static int CODE_AUTHORIZE_OVERDUE = 101;
	/** 无效授权 **/
	public final static int CODE_AUTHORIZE_FAIL = 102;
}
