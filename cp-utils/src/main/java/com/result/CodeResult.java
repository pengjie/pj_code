package com.result;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: CodeResult.java
 * Description: 公用返回值定义
 * Company: hnkj
 * @author lisu
 * @date 2016年12月22日
 * @version 1.0
 */
public class CodeResult {

	public static final int OK = 0;//成功
	public static final int PARAM_EMPTY = 1;//参数为空
	public static final int PARAM_ERROR = 2;//参数错误
	public static final int INVALID_IP = 3;//无效ip
	public static final int SIGN_ERROR = 4;//签名错误
	public static final int TIME_OUT = 5;//请求超时
	public static final int QUERY_FAIL = 6;//查询失败
	public static final int REQUEST_REPEAT = 7;//请求重复
	public static final int SERVER_ERROR = 500;//服务器内部错误
	public static final int NONWON_ERROR = 999;//未知错误
	
	public static final Map<Integer,String> map = new HashMap<Integer,String>();
	
	static{
		map.put(OK, "成功");
		map.put(PARAM_EMPTY, "参数为空");
		map.put(PARAM_ERROR, "参数错误");
		map.put(INVALID_IP, "无效ip");
		map.put(SIGN_ERROR, "签名错误");
		map.put(TIME_OUT, "请求超时");
		map.put(QUERY_FAIL, "查询失败");
		map.put(REQUEST_REPEAT, "请求重复");
		map.put(SERVER_ERROR, "服务器内部错误");
		map.put(NONWON_ERROR, "未知错误");
	}
}
