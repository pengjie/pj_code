/**create by liuhua at 2016年7月6日 下午5:33:11**/
package com.huinong.commerce.specification;

/**
 * 失败编码 及 说明
 * @author liuhua
 *
 */
public enum FailureCode {
	/**不知道编码的错误，就用这个**/
	ERR_000("000", "操作失败"),
	/**服务繁忙，请稍后再试**/
	ERR_001("001", "服务繁忙，请稍后再试"),
	/**参数错误**/
	ERR_002("002", "参数错误"),
	/**未登录，请先登录**/
	ERR_003("003", "未登录，请先登录"),
	/**未认证手机**/
	ERR_004("004", "未认证手机"),
	/**未实名认证**/
	ERR_005("005", "未实名认证"),
	/**图形验证码错误**/
	ERR_018("018", "图形验证码错误"),
	/**短信验证码错误**/
	ERR_019("019", "短信验证码错误"),
	/**%s分钟内只能发送%s次短信**/
	ERR_020("020", "%s分钟内只能发送%s次短信"),
	/**同一个IP下，24小时只能发送%s条短信**/
	ERR_021("021", "同一个IP下，24小时只能发送%s条短信"),
	/**同一个号码，%s分钟内只能发%s次**/
	ERR_022("022", "同一个号码，%s分钟内只能发%s次"),
	/**短信发送失败**/
	ERR_023("023", "短信发送失败"),

	/*** 从400开始到499之间的code用于 供应 的相关错误 *****/
	ERR_400("400","获取供应信息成功"),
	ERR_401("401","供应信息不存在"),
	ERR_402("402","供应信息被驳回"),
	ERR_403("403","供应信息被删除"),
	ERR_404("404","供应信息被禁"),
	ERR_405("405","用户被禁用"),
    ERR_406("406","未找到相关记录"),
    ERR_407("407","供应信息无品种"),
    ERR_408("408","供应信息无价格"),
	
	/*** 从500开始到599之间的code用于 采购 的相关错误 *****/
	ERR_500("500","获取采购信息成功"),
	ERR_501("501","采购信息不存在"),
	ERR_502("502","采购信息被驳回"),
	ERR_503("503","采购信息被删除"),
	ERR_504("504","采购信息被禁"),
	ERR_505("505","用户被禁用"),
    ERR_506("506","未找到相关记录");
	
	
	private String code;
	private String message;
	
	private FailureCode(String code, String message){
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
