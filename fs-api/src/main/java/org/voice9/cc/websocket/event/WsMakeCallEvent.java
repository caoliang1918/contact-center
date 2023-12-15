package org.voice9.cc.websocket.event;

import com.voice9.core.enums.CallType;
import org.voice9.cc.websocket.event.base.WsBaseEvent;

import java.util.Map;

/**
 * Create by caoliang on 2020/10/26
 * <p>
 * 发起呼叫
 */
public class WsMakeCallEvent extends WsBaseEvent {

    /**
     * 当前状态
     */
    private String state;

    /**
     * 随路数据
     */
    private Map<String, Object> followData;

    /**
     * uuid1
     */
    private String uuid1;

    /**
     * uuid2
     */
    private String uuid2;

    /**
     * 呼叫类型
     */
    private CallType callType;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫
     */
    private String called;

    /**
     * 用户侧显号，前端没传就用主技能配置
     */
    private String display;

    /**
     * 坐席侧显号，前端没有传就用主技能组配置
     */
    private String callerDisplay;

    /**
     * 选择的媒体
     */
    private String media;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getFollowData() {
        return followData;
    }

    public void setFollowData(Map<String, Object> followData) {
        this.followData = followData;
    }

    public String getUuid1() {
        return uuid1;
    }

    public void setUuid1(String uuid1) {
        this.uuid1 = uuid1;
    }

    public String getUuid2() {
        return uuid2;
    }

    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCallerDisplay() {
        return callerDisplay;
    }

    public void setCallerDisplay(String callerDisplay) {
        this.callerDisplay = callerDisplay;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "WsMakeCallEvent{" +
                "state='" + state + '\'' +
                ", followData=" + followData +
                ", uuid1='" + uuid1 + '\'' +
                ", uuid2='" + uuid2 + '\'' +
                ", callType=" + callType +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", display='" + display + '\'' +
                ", callerDisplay='" + callerDisplay + '\'' +
                '}';
    }
}
