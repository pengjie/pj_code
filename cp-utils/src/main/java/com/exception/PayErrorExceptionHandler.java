package com.exception;
/**
 * @author peng
 * 引用调用：
 * 调用类，创建构造函数
 * private PayErrorExceptionHandler exceptionHandler;
 * public PayMessageRouter(){
 * 	this.exceptionHandler = new LogExceptionHandler();
 * }
 * 
 * catch(Exception e){
 * 	exceptionHandler.handle(e);
 * }
 *
 */
public interface PayErrorExceptionHandler {
    /**
     * 异常统一处理器
     * @param e
     */
    void handle(PayErrorException e);
}
