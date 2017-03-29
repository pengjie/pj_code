/**create by liuhua at 2016年11月8日 下午5:43:21**/
package com.huinong.commerce.aop;

import com.huinong.commerce.aop.queue.MessageQueue;
import com.huinong.commerce.aop.queue.MessageQueueFactory;

import java.util.Map;

public class AuditTempData {

	public static MessageQueue<Map<String, Object>> getQueue(String key) {
		return MessageQueueFactory.createQueue(key);
	}
}
