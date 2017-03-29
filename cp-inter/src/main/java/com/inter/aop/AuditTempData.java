package com.inter.aop;

import com.inter.aop.queue.MessageQueue;
import com.inter.aop.queue.MessageQueueFactory;

import java.util.Map;

public class AuditTempData {

	public static MessageQueue<Map<String, Object>> getQueue(String key) {
		return MessageQueueFactory.createQueue(key);
	}
}
