package com.voice9.core.enums;

/**
 * Created by caoliang on 2020/06/06
 */
public enum Direction {
    /**
     * 呼入
     */
    INBOUND(1),

    /**
     * 外呼
     */
    OUTBOUND(2);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    Direction(Integer code) {
        this.code = code;
    }
}
