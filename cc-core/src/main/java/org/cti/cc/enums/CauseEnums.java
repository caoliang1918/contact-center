package org.cti.cc.enums;

/**
 * Created by caoliang on 2020/06/06
 */
public enum CauseEnums {

    /**
     * 呼叫正常挂机
     */
    NORMAL_CLEARING(1000),


    /**
     * 未知号码
     */
    UNALLOCATED_NUMBER(1001),

    /**
     * 用户忙
     */
    USER_BUSY(1002),

    /**
     * 暂时故障,此原因表明网络无法正常运行
     */
    NORMAL_TEMPORARY_FAILURE(1003),

    /**
     * 媒体超时(定时器挂机)
     */
    RECOVERY_ON_TIMER_EXPIRE(1004),


    /**
     * 目的地故障
     */
    DESTINATION_OUT_OF_ORDER(1005),

    /**
     * 用户未应答
     */
    NO_ANSWER(1006),

    /**
     * 用户未响应(未响应超时)
     */
    NO_USER_RESPONSE(1007),

    /**
     * 呼叫被拒绝
     */
    CALL_REJECTED(1008),

    /**
     * 网络参数错误
     */
    MANDATORY_IE_MISSING(1009),

    /**
     * 取消呼叫
     */
    ORIGINATOR_CANCEL(1010),

    TIMEOUT(1011);

    private Integer causeCode;

    CauseEnums(Integer causeCode) {
        this.causeCode = causeCode;
    }

    public Integer getCauseCode() {
        return causeCode;
    }
}
