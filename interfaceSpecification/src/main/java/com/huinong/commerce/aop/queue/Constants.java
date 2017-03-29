package com.huinong.commerce.aop.queue;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class Constants {

    // 收集异步消息最大容量，达到后会阻塞
    public static int DEFAULT_MAX_SIZE_VM_QUEUE = 10000;
    // 收集异步消息时最大阻塞时间
    public static long DEFAULT_TIMEOUT_BLOCKING_VM_QUEUE = 3000l;

    /**
     * 审核的业务类型
     * @author liuhua
     *
     */
    public enum AuditBusinessType {
        supply("供应信息"),
        photo("供应主图"),
        purchase("采购信息"),
        offer("采购单报价");
        private String caption;
        AuditBusinessType(String caption){
            this.caption = caption;
        }

        public String getCaption() {
            return caption;
        }
    }


}
