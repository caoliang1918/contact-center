package com.voice9.core.enums;

/**
 * Created by caoliang on 2020/06/06
 */
public enum CauseEnums {

    /**
     * 呼叫正常挂机
     */
    NORMAL_CLEARING(0),


    /**
     * 未知号码
     */
    UNALLOCATED_NUMBER(9001),

    /**
     * 用户忙
     */
    USER_BUSY(9002),

    /**
     * 暂时故障,此原因表明网络无法正常运行
     */
    NORMAL_TEMPORARY_FAILURE(9003),

    /**
     * 媒体超时(定时器挂机)
     */
    RECOVERY_ON_TIMER_EXPIRE(9004),


    /**
     * 目的地故障
     */
    DESTINATION_OUT_OF_ORDER(9005),

    /**
     * 用户未应答
     */
    NO_ANSWER(9006),

    /**
     * 用户未响应(未响应超时)
     */
    NO_USER_RESPONSE(9007),

    /**
     * 呼叫被拒绝
     */
    CALL_REJECTED(9008),

    /**
     * 网络参数错误
     */
    MANDATORY_IE_MISSING(9009),

    /**
     * 取消呼叫
     */
    ORIGINATOR_CANCEL(9010),

    /**
     * 超时
     */
    TIMEOUT(9011),

    /**
     * 溢出挂机
     */
    OVERFLOW_TIMEOUT(9501),

    /**
     * 排队挂机
     */
    QUEUE_TIMEOUT(9502),

    /**
     * 呼入vdn错误
     */
    VDN_ERROR(9503),

    CALL_TIMEOUT(9504),

    /**
     * 参数错误
     */
    PARAMTER_ERROR(9599);

    private Integer huangupCode;

    CauseEnums(Integer causeCode) {
        this.huangupCode = causeCode;
    }

    public Integer getHuangupCode() {
        return huangupCode;
    }
}
