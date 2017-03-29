package com.inter.aop.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消息队列
 *
 */
public class MessageQueue<T> {

    // 消息对列 FIFO原则，线程安全
    private BlockingQueue<T> msgQueue;

    private int size;
    // 毫秒
    private long timeout;

//	MessageQueue() {
//		this(CallConst.DEFAULT_MAX_SIZE_VM_QUEUE, CallConst.DEFAULT_TIMEOUT_BLOCKING_VM_QUEUE);
//	}

    MessageQueue(int size, long timeout) {
        setSize(size);
        setTimeout(timeout);
        this.msgQueue = new ArrayBlockingQueue<T>(size);
    }

    /**
     * 获取消息队列中消息数量(存在差异，不是绝对的数据)
     *
     * @return
     */
    public int size() {
        return this.msgQueue.size();
    }

    /**
     * 获取队列最上层消息(获取后并删除该消息)
     *
     * @return
     */
    public T poll() {
        try {
            return msgQueue.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取队列最上层消息(获取后并不删除该消息)
     *
     * @return
     */
    public T peek() {
        return msgQueue.peek();
    }

    /**
     * 添加消息
     *
     * @param msg
     * @return
     */
    public boolean offer(T msg) {
        boolean in = false;
        try {
            in = msgQueue.offer(msg, timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return in;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

}
