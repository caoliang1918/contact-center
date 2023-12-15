package com.voice9.core.enums;

/**
 * Created by caoliang on 2020/06/06
 */
public enum CallType {

    /**
     * IM
     */
    IM(0),
    /**
     * 呼入
     */
    INBOUND_CALL(1),

    /**
     * 手动外呼
     */
    OUTBOUNT_CALL(2),

    /**
     * 预测外呼
     */
    AUTO_CALL(3),

    /**
     * 机器人外呼
     */
    BOOT_CALL(4),

    /**
     * 双向外呼
     */
    BOTH_CALL(5),

    /**
     * 硬话机外呼
     */
    SIP_OUTBOUND_CALL(6),

    /**
     * 内呼
     */
    INNER_CALL(6);



    private Integer code;

    CallType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
