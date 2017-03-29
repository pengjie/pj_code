package com.inter.specification.result;

import java.io.Serializable;

import com.inter.specification.Constants;
import com.inter.specification.FailureCode;

/**
 * 接口统一返回的对象
 *
 */
public class ResultMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 接口返回SUCCESS 为成功
	 * 接口返回ERROR 为失败
	 * 如果为ERROR，则查看errorCode和 errorMessage 了解具体的失败原因 
	 */
	private String status;
	/**
	 * 接口返回消息 可选 
	 */
	private String message;
	/**
	 * 当接口访问不能获取想要的正确结果，则用errorCode标识详细错误代码
	 * 错误代码参考com.huinong.commerce.specification.FailureCode
	 */
	private String errorCode;
	/**
	 * errorCode有值，则此属性必有值
	 * 参考 com.huinong.commerce.specification.FailureCode
	 */
	private String errorMessage;
	/**
	 * 接口要返回的数据
	 */
	private Object data;
	/**
	 * 分页信息
	 */
	private PageInfo pageInfo;
	
	public ResultMessage(){
		
	}
	
	private ResultMessage(String status, String message, FailureCode failureCode, Object data, PageInfo pageInfo, Object... args) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.pageInfo = pageInfo;
		if (null != failureCode) {
			this.errorCode = failureCode.getCode();
			if (null == args || args.length == 0) {
				this.errorMessage = failureCode.getMessage();	
			}else{
				this.errorMessage = String.format(failureCode.getMessage(), args);
			}
		}
	}
	
	/**
	 * 接口调用正常时，应返回成功状态 及 数据
	 * @param message
	 * @param message
	 */
	public ResultMessage(String message){
		this(Constants.RESULT_SUCCESS, message, null, null, null);
	}
	
	/**
	 * 接口调用正常时，应返回成功状态 及 数据
	 * @param data
	 * @param message
	 */
	public ResultMessage(Object data, String message){
		this(Constants.RESULT_SUCCESS, message, null, data, null);
	}
	
	/**
	 * 接口调用正常时，应返回成功状态 及 数据
	 * @param data
	 * @param message
	 */
	public ResultMessage(Object data, PageInfo pageInfo, String message){
		this(Constants.RESULT_SUCCESS, message, null, data, pageInfo);
	}
	
	/**
	 * 接口调用异常时，应返回错误状态 及 错误状态码
	 * @param message
	 * @param failureCode
	 * @param args
	 */
	public ResultMessage(String message, FailureCode failureCode, Object... args){
		this(Constants.RESULT_ERROR, message, failureCode, null, null, args);
	}

	/**
	 * 接口调用异常时，应返回错误状态 及 错误状态码
	 * @param message
	 * @param errorCode
	 * @param errorMessage
	 */
	public ResultMessage(String message, String errorCode, String errorMessage){
		this.status = Constants.RESULT_ERROR;
		this.message = message;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;	
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
