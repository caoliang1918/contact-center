package org.cti.cc.enums;

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
     * 自动外呼
     */
    BOT_CALL(3),

    /**
     * 半自动外呼
     */
    PREVIEW_CALL(4),

    /**
     * 内呼
     */
    INNER_CALL(5),

    /**
     * 转接
     */
    TRANSFER_CALL(6),

    /**
     * 咨询
     */
    CONSULT_CALL(7),

    /**
     * 转外线
     */
    TRANSFER_OUT_CALL(10);

    private Integer code;

    CallType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
