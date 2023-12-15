package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2022/2/26
 */
public class FsPresenceInEvent extends FsBaseEvent {

    @JSONField(name = "login")
    private String login;

    @JSONField(name = "from")
    private String from;

    @JSONField(name = "event_type")
    private String eventType;

    @JSONField(name = "Event-Calling-File")
    private String eventFile;

    @JSONField(name = "Event-Calling-Function")
    private String eventCallFunction;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventFile() {
        return eventFile;
    }

    public void setEventFile(String eventFile) {
        this.eventFile = eventFile;
    }

    public String getEventCallFunction() {
        return eventCallFunction;
    }

    public void setEventCallFunction(String eventCallFunction) {
        this.eventCallFunction = eventCallFunction;
    }

    @Override
    public String toString() {
        return "FsPresenceInEvent{" +
                "login='" + login + '\'' +
                ", from='" + from + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventFile='" + eventFile + '\'' +
                ", eventName='" + eventName + '\'' +
                ", context=" + context +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", channelName='" + channelName + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
