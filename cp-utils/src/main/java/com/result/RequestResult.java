package com.result;

import com.google.gson.Gson;

public class RequestResult {
	
	private final  static Gson gson = new Gson();

	private  static final int SUCCESS = 0 ;

	private static final int FAILURE = -1  ;
	/**
	 * 
	 */
	private int status ;
	
	/**
	 * 错误信息
	 */
	private String message ;
 
	/**
	 * 请求结果对象
	 */
	public RequestResult(int status , String message) {
		this.status = status; 
		this.message = message ;
	}
	
	/**
	 * 默认返回正常结果
	 * @Title: success 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author dingqi 
	 * @date 2016年9月5日 下午7:53:12 
	 * @return String
	 */
	public static String  success(){
		RequestResult result = new RequestResult(RequestResult.SUCCESS , "");
		return gson.toJson(result);
	}
	
	/**
	 * 返回错误结果
	 * @Title: success 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @author dingqi 
	 * @date 2016年9月5日 下午7:53:12 
	 * @return String
	 */
	public static String  failure(String message){
		RequestResult result = new RequestResult(RequestResult.FAILURE , message);
		return gson.toJson(result);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
