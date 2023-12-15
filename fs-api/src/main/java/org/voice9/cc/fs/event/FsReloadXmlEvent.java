package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2022/5/4
 */
public class FsReloadXmlEvent extends FsBaseEvent {

    /**
     *
     */
    @JSONField(name = "Event-Calling-Function")
    private String function;

    /**
     *
     */
    @JSONField(name = "Event-Calling-File")
    private String file;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FsReloadXmlEvent{" +
                "function='" + function + '\'' +
                ", file='" + file + '\'' +
                ", eventName='" + eventName + '\'' +
                ", coreUuid='" + coreUuid + '\'' +
                '}';
    }
}
