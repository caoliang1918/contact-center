package org.cti.cc.po;

import com.alibaba.fastjson.annotation.JSONField;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;

import java.util.List;

/**
 * Created by caoliang on 2020/11/14
 */
public class CallLogPo  {
    /**
     *
     */
    private Long id;

    /**
     * 落单时间
     */
    private Long cts;

    /**
     * 修改时间
     */
    private Long uts;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 话单id
     */
    private Long callId;

    /**
     * 主叫显号
     */
    @JSONField(defaultValue = "")
    private String callerDisplay;

    /**
     * 主叫
     */
    @JSONField(defaultValue = "")
    private String caller;

    /**
     * 被叫显号
     */
    @JSONField(defaultValue = "")
    private String calledDisplay;

    /**
     * 被叫
     */
    @JSONField(defaultValue = "")
    private String called;

    /**
     * 坐席
     */
    @JSONField(defaultValue = "")
    private String agentKey;

    /**
     * 技能组
     */
    @JSONField(defaultValue = "0")
    private Long groupId;

    /**
     * 1:sip号,2:webrtc,3:手机
     */
    @JSONField(defaultValue = "1")
    private Integer loginType;

    /**
     * 任务ID
     */
    @JSONField(defaultValue = "0")
    private Long taskId;

    /**
     * ivr
     */
    @JSONField(defaultValue = "0")
    private Long ivrId;

    /**
     * 机器人id
     */
    @JSONField(defaultValue = "0")
    private Long botId;

    /**
     * 呼叫开始时间
     */
    @JSONField(defaultValue = "0")
    private Long callTime;

    /**
     * 接听时间
     */
    @JSONField(defaultValue = "0")
    private Long answerTime;

    /**
     * 结束时间
     */
    @JSONField(defaultValue = "0")
    private Long endTime;

    /**
     * 呼叫类型
     */
    @JSONField(defaultValue = "")
    private String callType;

    /**
     * 呼叫方向
     */
    @JSONField(defaultValue = "")
    private String direction;

    /**
     * 通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)
     */
    @JSONField(defaultValue = "0")
    private Integer answerFlag;

    /**
     * 累计等待时长
     */
    @JSONField(defaultValue = "0")
    private Long waitTime;

    /**
     * 应答设备数
     */
    @JSONField(defaultValue = "0")
    private Integer answerCount;

    /**
     * 挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)
     */
    @JSONField(defaultValue = "3")
    private Integer hangupDir;

    /**
     * 挂机原因
     */
    @JSONField(defaultValue = "")
    private String hangupCause;

    /**
     * 媒体服务器
     */
    @JSONField(defaultValue = "")
    private String media;

    /**
     * 录音地址
     */
    @JSONField(defaultValue = "")
    private String record;

    /**
     * 通话时长(秒)
     */
    @JSONField(defaultValue = "0")
    private Long talkTime;

    /**
     * 通话随路数据(2048)
     */
    @JSONField(defaultValue = "")
    private String followData;

    /**
     * 扩展1
     */
    @JSONField(defaultValue = "")
    private String uuid1;

    /**
     * 扩展2
     */
    @JSONField(defaultValue = "")
    private String uuid2;

    /**
     * 扩展3
     */
    private String ext1;

    /**
     * 扩展4
     */
    private String ext2;

    /**
     * 扩展5
     */
    private String ext3;

    private List<CallDevice> callDeviceList;

    private List<CallDetail> callDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public Long getUts() {
        return uts;
    }

    public void setUts(Long uts) {
        this.uts = uts;
    }

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

    public String getCalledDisplay() {
        return calledDisplay;
    }

    public void setCalledDisplay(String calledDisplay) {
        this.calledDisplay = calledDisplay;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
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

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
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

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
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

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public List<CallDevice> getCallDeviceList() {
        return callDeviceList;
    }

    public void setCallDeviceList(List<CallDevice> callDeviceList) {
        this.callDeviceList = callDeviceList;
    }

    public List<CallDetail> getCallDetailList() {
        return callDetailList;
    }

    public void setCallDetailList(List<CallDetail> callDetailList) {
        this.callDetailList = callDetailList;
    }
}
