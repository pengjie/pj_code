/**
 * 支付错误码说明
 */
package com.exception;

/**
 * @author peng
 *
 */
public interface PayError {
	/**
     * 获取错误码
     *
     * @return
     */
    String getErrorCode();

    /**
     * 获取错误消息
     *
     * @return
     */
    String getErrorMsg();

    /**
     * 获取异常信息
     * @return
     */
    String getString();
}
