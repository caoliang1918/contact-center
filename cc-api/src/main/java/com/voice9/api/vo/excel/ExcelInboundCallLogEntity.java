package com.voice9.api.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by caoliang on 2021/9/21
 */
public class ExcelInboundCallLogEntity {


    /**
     * 企业id
     */
    @Excel(name = "企业id", width = 8)
    private Long companyId;

    /**
     * 话单id
     */
    @Excel(name = "话单id", width = 22)
    private Long callId;

    /**
     * 接入号码
     */
    @Excel(name = "接入号码", width = 15)
    private String callerDisplay;

    /**
     * 主叫(用户号码)
     */
    @Excel(name = "主叫", width = 15)
    private String caller;

    /**
     * 被叫(坐席号码)
     */
    @Excel(name = "被叫", width = 15)
    private String called;

    /**
     * 坐席
     */
    @Excel(name = "坐席", width = 12)
    private String agentKey;

    /**
     * 技能组
     */
    @Excel(name = "技能组id", width = 10)
    private Long groupId;

    /**
     * 1:sip号,2:webrtc,3:手机
     */
    @Excel(name = "话机", width = 10, replace = {"sip_1", "webrtc_2", "手机_3"})
    private Integer loginType;

    /**
     * ivr
     */
    @Excel(name = "ivr", width = 10)
    private Long ivrId;

    /**
     * 机器人id
     */
    @Excel(name = "机器人", width = 10)
    private Long botId;

    /**
     * 呼叫开始时间
     */
    @Excel(name = "开始时间", width = 20)
    private String callTime;

    /**
     * 接听时间
     */
    @Excel(name = "接听时间", width = 20)
    private String answerTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 20)
    private String endTime;

    /**
     * 呼叫方向
     */
    @Excel(name = "呼叫方向", width = 10, replace = {"外呼_OUTBOUND", "呼入_INBOUND"})
    private String direction;

    /**
     * 通话结果(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)
     */
    @Excel(name = "通话结果", width = 15, replace = {"接通_0", "坐席未接通_1", "用户未接通_2", "坐席未接通_3"})
    private Integer answerFlag;

    /**
     * 累计等待时长
     */
    @Excel(name = "等待时长", width = 10)
    private Long waitTime;

    /**
     * 应答设备数
     */
    @Excel(name = "应答数", width = 10)
    private Integer answerCount;

    /**
     * 挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)
     */
    @Excel(name = "挂机方向", width = 10, replace = {"主叫挂机_1", "被叫挂机_2", "系统挂机_3"})
    private Integer hangupDir;

    /**
     * 挂机原因
     */
    @Excel(name = "挂机原因", width = 25)
    private String hangupCause;

    /**
     * 录音地址
     */
    @Excel(name = "录音地址", width = 15)
    private String record;

    /**
     * 录音开始时间
     */
    @Excel(name = "录音开始时间", width = 20)
    private String recordTime;

    /**
     * 通话时长(秒)
     */
    @Excel(name = "通话时长", width = 10)
    private Long talkTime;

    /**
     * 第一次进队列时间
     */
    @Excel(name = "第一次进队列时间", width = 20)
    private String fristQueueTime;

    /**
     * 进入技能组时间
     */
    @Excel(name = "进入技能组时间", width = 20)
    private String queueStartTime;

    /**
     * 出技能组时间
     */
    @Excel(name = "出技能组时间", width = 20)
    private String queueEndTime;

    /**
     * 通话随路数据(2048)
     */
    @Excel(name = "随路数据", width = 15)
    private String followData;

    /**
     * 扩展1
     */
    @Excel(name = "uuid1", width = 15)
    private String uuid1;

    /**
     * 扩展2
     */
    @Excel(name = "uuid2", width = 15)
    private String uuid2;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCallerDisplay() {
        return callerDisplay;
    }

    public void setCallerDisplay(String callerDisplay) {
        this.callerDisplay = callerDisplay;
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

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Long getIvrId() {
        return ivrId;
    }

    public void setIvrId(Long ivrId) {
        this.ivrId = ivrId;
    }

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(Integer answerFlag) {
        this.answerFlag = answerFlag;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getHangupDir() {
        return hangupDir;
    }

    public void setHangupDir(Integer hangupDir) {
        this.hangupDir = hangupDir;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public String getFristQueueTime() {
        return fristQueueTime;
    }

    public void setFristQueueTime(String fristQueueTime) {
        this.fristQueueTime = fristQueueTime;
    }

    public String getQueueStartTime() {
        return queueStartTime;
    }

    public void setQueueStartTime(String queueStartTime) {
        this.queueStartTime = queueStartTime;
    }

    public String getQueueEndTime() {
        return queueEndTime;
    }

    public void setQueueEndTime(String queueEndTime) {
        this.queueEndTime = queueEndTime;
    }

    public String getFollowData() {
        return followData;
    }

    public void setFollowData(String followData) {
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
}
