package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Create by caoliang on 2020/10/22
 */
public class FsAnswerEvent extends FsBaseEvent {
    /**
     * 被叫号码
     */
    @JSONField(name = "Caller-Destination-Number")
    private String called;


    /**
     * 主叫号码
     */
    @JSONField(name = "Caller-Caller-ID-Number")
    private String caller;

    /**
     * 接通时间
     */
    @JSONField(name = "Caller-Channel-Answered-Time")
    private Long answerTime;


    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "AnswerEvent{" +
                "called='" + called + '\'' +
                ", caller='" + caller + '\'' +
                ", answerTime=" + answerTime +
                ", eventName='" + eventName + '\'' +
                ", context=" + context +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", channelName='" + channelName + '\'' +
                ", map=" + map +
                '}';
    }
}
