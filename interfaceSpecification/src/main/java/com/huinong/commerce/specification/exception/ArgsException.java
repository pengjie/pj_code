/**create by liuhua at 2016年7月7日 下午2:34:57**/
package com.huinong.commerce.specification.exception;

import com.huinong.commerce.specification.FailureCode;

/**
 * 参数错误异常
 * @author liuhua
 *
 */
public class ArgsException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public ArgsException(){
		super("参数错误");
	}
	
	public ArgsException(String message){
		super(message);
		this.code = "000";
	}
	
	public ArgsException(FailureCode failureCode){
		super(failureCode.getMessage());
		this.code = failureCode.getCode();
	}
	
	public ArgsException(FailureCode failureCode, String message){
		super(message);
		this.code = failureCode.getCode();
	}
	
	public ArgsException(String code, String message){
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
