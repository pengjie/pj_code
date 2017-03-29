package com.result;

import java.io.Serializable;

public class ErrorCode implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final ErrorCode SYSTEM_ERROR = new ErrorCode(-1000,"系统错误");
    private int errorCode;
    private String errorMsg;

    public ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
