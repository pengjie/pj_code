package com.huinong.commerce.aop.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息队列工厂
 * @author liuhua
 *
 */
public class MessageQueueFactory {

    private static Map<String, MessageQueue<?>> queues = new ConcurrentHashMap<String, MessageQueue<?>>();

    public static <T> MessageQueue<T> createQueue() {
        return createQueue("");
    }

    public static <T> MessageQueue<T> createQueue(String name) {
        return createQueue(name, Constants.DEFAULT_MAX_SIZE_VM_QUEUE, Constants.DEFAULT_TIMEOUT_BLOCKING_VM_QUEUE);
    }

    public static synchronized <T> MessageQueue<T> createQueue(String name, int size, long timeout) {
        @SuppressWarnings("unchecked")
        MessageQueue<T> queue = (MessageQueue<T>) queues.get(name);
        if (queue == null) {
            queue = new MessageQueue<T>(size, timeout);
            queues.put(name, queue);
        }
        return queue;
    }
}