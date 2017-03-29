/**
 * 支付回调通知返回消息
 */
package com.pay;

import java.io.Serializable;

/**
 * @author peng
 *
 */
public abstract class PayOutMessage implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	protected String content;
    protected String msgType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
