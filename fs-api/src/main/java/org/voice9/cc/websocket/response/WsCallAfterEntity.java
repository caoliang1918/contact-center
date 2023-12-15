package org.voice9.cc.websocket.response;


import com.voice9.core.enums.CallType;
import com.voice9.core.enums.Direction;

/**
 * Created by caoliang on 2020/11/14
 */
public class WsCallAfterEntity {

    /**
     * 通话标识
     */
    private Long callId;

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     * 呼叫开始时间
     */
    private Long callTime;

    /**
     * 呼叫类型
     */
    private CallType callType;

    /**
     * 呼叫方向
     */
    private Direction direction;


    /**
     * 接听时间，被叫 CHANNEL_ANSWER，转接不算
     */
    private Long answerTime;

    /**
     * 最后一侧电话挂机时间
     */
    private Long endTime;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫
     */
    private String called;

    /**
     * 挂机原因
     */
    private String cause;

    /**
     * 媒体
     */
    private String media;

    /**
     * 挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)
     */
    private String hangup;

    /**
     * 通话时长
     */
    private Long talkTime;

    /**
     * 录音开始时间
     */
    private Long recordTime;

    /**
     * 录音地址
     */
    private String record;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getHangup() {
        return hangup;
    }

    public void setHangup(String hangup) {
        this.hangup = hangup;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "WsCallAfterEntity{" +
                "callId=" + callId +
                ", groupId=" + groupId +
                ", callTime=" + callTime +
                ", callType=" + callType +
                ", direction=" + direction +
                ", answerTime=" + answerTime +
                ", endTime=" + endTime +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", cause='" + cause + '\'' +
                ", media='" + media + '\'' +
                ", hangup='" + hangup + '\'' +
                ", talkTime=" + talkTime +
                ", recordTime=" + recordTime +
                ", record='" + record + '\'' +
                '}';
    }
}
