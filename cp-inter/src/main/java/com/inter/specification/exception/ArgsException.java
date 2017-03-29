package com.inter.specification.exception;

import com.inter.specification.FailureCode;

/**
 * 参数错误异常
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
