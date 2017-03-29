/**
 * 支付订单信息
 */
package com.pay;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author peng
 *
 */
public class PayOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//商品名称
    private String subject;
    //商品描述
    private String body;
    //价格
    private BigDecimal price;
    //商户订单号
    private String outTradeNo;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

}
