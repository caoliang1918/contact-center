package com.voice9.core.enums;

/**
 * Created by caoliang on 2021/9/7
 */
public enum StationType {

    CC_API(1),

    FS_API(2),

    CC_IVR(3),

    FS_MEDIA(4);

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    StationType(Integer type){
        this.type = type;
    }
}
