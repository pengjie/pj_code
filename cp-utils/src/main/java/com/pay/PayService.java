/**
 * 支付服务
 */
package com.pay;

import java.util.Map;

/**
 * @author peng
 *
 */
public interface PayService {

    /**
     * 回调校验
     *
     * @param params 回调回来的参数集
     * @return
     */
    boolean verify(Map<String, String> params);
    
    /**
     * 返回创建的订单信息
     *
     * @param order 支付订单
     * @return
     * @see PayOrder
     */
    Map orderInfo(PayOrder order);
    
    
}
