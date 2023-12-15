package com.voice9.core.po;

import com.voice9.core.entity.CallDetail;
import com.voice9.core.enums.CallType;
import com.voice9.core.enums.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2020/8/31
 */
public class CallInfo implements Serializable {

    private String coreUuid;

    /**
     * 通话唯一标识
     */
    private Long callId;

    /**
     * 会议号
     */
    private String conference;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 所在技能组id
     */
    private Long groupId;

    /**
     * 隐藏客户号码(0:不隐藏;1:隐藏)
     */
    private int hiddenCustomer;

    /**
     * 主叫显号
     */
    private String callerDisplay;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫显号
     */
    private String calledDisplay;

    /**
     * 被叫
     */
    private String called;

    /**
     * 号码归属地
     */
    private String numberLocation;

    /**
     * 坐席
     */
    private String agentKey;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * 坐席登录类型
     */
    private Integer loginType;

    /**
     * ivr
     */
    private Long ivrId;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 媒体
     */
    private String mediaHost;

    /**
     * 服务地址
     */
    private String ctiHost;

    /**
     *
     */
    private String clientHost;

    /**
     * 录音地址
     */
    private String record;

    /**
     * 第三方存储地址
     */
    private String record2;

    /**
     * 录音开始时间
     */
    private Long recordTime;

    /**
     * 呼叫开始时间
     */
    private Long callTime;

    /**
     * 呼叫类型
     */
    private CallType callType;

    /**
     * 呼叫方向(2:外呼,1:呼入)
     */
    private Direction direction;

    /**
     * 通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)
     */
    private Integer answerFlag;

    /**
     * 等待时长
     */
    private Long waitTime;

    /**
     * 应答设备数
     */
    private int answerCount;

    /**
     * 1主叫挂机, 2:被叫挂机, 3:平台挂机
     */
    private Integer hangupDir;

    /**
     * sdk挂机
     */
    private int sdkHangup;

    /**
     * 挂机原因
     */
    private Integer hangupCode;


    /**
     * 接听时间，被叫 CHANNEL_ANSWER，转接不算
     */
    private Long answerTime;

    /**
     * 最后一侧电话挂机时间
     */
    private Long endTime;

    /**
     * 通话时长(秒)
     */
    private Long talkTime;

    /**
     * 第一次进队列时间
     */
    private Long fristQueueTime;

    /**
     * 进入技能组时间
     */
    private Long queueStartTime;

    /**
     * 出技能组时间
     */
    private Long queueEndTime;

    /**
     * 溢出次数
     */
    private int overflowCount;

    /**
     * uuid1
     */
    private String uuid1;

    /**
     * uuid2
     */
    private String uuid2;

    /**
     * 话单通知地址
     */
    private String cdrNotifyUrl;

    /**
     * 排队等级，默认是进队列时间
     */
    private Long queueLevel;


    /**
     * 当前通话的设备
     */
    private List<String> deviceList = new ArrayList<>();

    /**
     * K-V
     */
    private Map<String, DeviceInfo> deviceInfoMap = new HashMap<>();

    /**
     * 呼叫随路数据(作为落单数据)
     */
    private Map<String, Object> followData = new HashMap<>();

    /**
     * 模块流程间数据
     */
    private Map<String, Object> processData = new HashMap<>();

    /**
     * 执行下一步命令
     */
    private List<NextCommand> nextCommands = new ArrayList<>();

    /**
     * 电话流程
     */
    private List<CallDetail> callDetails = new ArrayList<>();

    public String getCoreUuid() {
        return coreUuid;
    }

    public void setCoreUuid(String coreUuid) {
        this.coreUuid = coreUuid;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public int getHiddenCustomer() {
        return hiddenCustomer;
    }

    public void setHiddenCustomer(int hiddenCustomer) {
        this.hiddenCustomer = hiddenCustomer;
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

    public String getNumberLocation() {
        return numberLocation;
    }

    public void setNumberLocation(String numberLocation) {
        this.numberLocation = numberLocation;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getMediaHost() {
        return mediaHost;
    }

    public void setMediaHost(String mediaHost) {
        this.mediaHost = mediaHost;
    }

    public String getCtiHost() {
        return ctiHost;
    }

    public void setCtiHost(String ctiHost) {
        this.ctiHost = ctiHost;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getRecord2() {
        return record2;
    }

    public void setRecord2(String record2) {
        this.record2 = record2;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
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

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getHangupDir() {
        return hangupDir;
    }

    public void setHangupDir(Integer hangupDir) {
        this.hangupDir = hangupDir;
    }

    public int getSdkHangup() {
        return sdkHangup;
    }

    public void setSdkHangup(int sdkHangup) {
        this.sdkHangup = sdkHangup;
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

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public List<String> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<String> deviceList) {
        this.deviceList = deviceList;
    }

    public Map<String, DeviceInfo> getDeviceInfoMap() {
        return deviceInfoMap;
    }

    public void setDeviceInfoMap(Map<String, DeviceInfo> deviceInfoMap) {
        this.deviceInfoMap = deviceInfoMap;
    }

    public Map<String, Object> getFollowData() {
        return followData;
    }

    public void setFollowData(Map<String, Object> followData) {
        this.followData = followData;
    }

    public List<CallDetail> getCallDetails() {
        return callDetails;
    }

    public void setCallDetails(List<CallDetail> callDetails) {
        this.callDetails = callDetails;
    }

    public Long getFristQueueTime() {
        return fristQueueTime;
    }

    public void setFristQueueTime(Long fristQueueTime) {
        this.fristQueueTime = fristQueueTime;
    }

    public Long getQueueStartTime() {
        return queueStartTime;
    }

    public void setQueueStartTime(Long queueStartTime) {
        this.queueStartTime = queueStartTime;
    }

    public Long getQueueEndTime() {
        return queueEndTime;
    }

    public void setQueueEndTime(Long queueEndTime) {
        this.queueEndTime = queueEndTime;
    }

    public int getOverflowCount() {
        return overflowCount;
    }

    public void setOverflowCount(int overflowCount) {
        this.overflowCount = overflowCount;
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

    public Integer getHangupCode() {
        return hangupCode;
    }

    public void setHangupCode(Integer hangupCode) {
        this.hangupCode = hangupCode;
    }

    public String getCdrNotifyUrl() {
        return cdrNotifyUrl;
    }

    public void setCdrNotifyUrl(String cdrNotifyUrl) {
        this.cdrNotifyUrl = cdrNotifyUrl;
    }

    public Map<String, Object> getProcessData() {
        return processData;
    }

    public void setProcessData(Map<String, Object> processData) {
        this.processData = processData;
    }

    public Long getQueueLevel() {
        return queueLevel == null ? queueStartTime : queueLevel;
    }

    public void setQueueLevel(Long queueLevel) {
        this.queueLevel = queueLevel;
    }

    public List<NextCommand> getNextCommands() {
        return nextCommands;
    }

    public void setNextCommands(List<NextCommand> nextCommands) {
        this.nextCommands = nextCommands;
    }


    public static final class CallInfoBuilder {
        private String coreUuid;
        private Long callId;
        private Long companyId;
        private Long groupId;
        private int hiddenCustomer;
        private String callerDisplay;
        private String caller;
        private String calledDisplay;
        private String called;
        private String numberLocation;
        private String agentKey;
        private String agentName;
        private Integer loginType;
        private Long ivrId;
        private String mediaHost;
        private String ctiHost;
        private String clientHost;
        private String record;
        private Long callTime;
        private CallType callType;
        private Direction direction;
        private Integer hangupDir;
        private int sdkHangup;
        private Long answerTime;
        private int answerCount;
        private Long endTime;
        private Long talkTime;
        private String uuid1;
        private String uuid2;
        private List<String> deviceList = new ArrayList<>();
        private Map<String, DeviceInfo> deviceInfoMap = new HashMap<>();
        private Map<String, Object> followData = new HashMap<>();
        private List<NextCommand> nextCommands = new ArrayList<>();

        private CallInfoBuilder() {
        }

        public static CallInfoBuilder builder() {
            return new CallInfoBuilder();
        }

        public CallInfoBuilder withCoreUuid(String coreUuid) {
            this.coreUuid = coreUuid;
            return this;
        }

        public CallInfoBuilder withCallId(Long callId) {
            this.callId = callId;
            return this;
        }

        public CallInfoBuilder withCompanyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public CallInfoBuilder withGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public CallInfoBuilder withHiddenCustomer(int hiddenCustomer) {
            this.hiddenCustomer = hiddenCustomer;
            return this;
        }

        public CallInfoBuilder withCallerDisplay(String callerDisplay) {
            this.callerDisplay = callerDisplay;
            return this;
        }

        public CallInfoBuilder withCaller(String caller) {
            this.caller = caller;
            return this;
        }

        public CallInfoBuilder withCalledDisplay(String calledDisplay) {
            this.calledDisplay = calledDisplay;
            return this;
        }

        public CallInfoBuilder withCalled(String called) {
            this.called = called;
            return this;
        }

        public CallInfoBuilder withAgentKey(String agentKey) {
            this.agentKey = agentKey;
            return this;
        }

        public CallInfoBuilder withNumberLocation(String numberLocation) {
            this.numberLocation = numberLocation;
            return this;
        }

        public CallInfoBuilder withAgentName(String agentName) {
            this.agentName = agentName;
            return this;
        }

        public CallInfoBuilder withLoginType(Integer loginType) {
            this.loginType = loginType;
            return this;
        }

        public CallInfoBuilder withIvrId(Long ivrId) {
            this.ivrId = ivrId;
            return this;
        }

        public CallInfoBuilder withMediaHost(String mediaHost) {
            this.mediaHost = mediaHost;
            return this;
        }

        public CallInfoBuilder withCtiHost(String ctiHost) {
            this.ctiHost = ctiHost;
            return this;
        }

        public CallInfoBuilder withClientHost() {
            this.clientHost = clientHost;
            return this;
        }

        public CallInfoBuilder withRecord(String record) {
            this.record = record;
            return this;
        }

        public CallInfoBuilder withCallTime(Long callTime) {
            this.callTime = callTime;
            return this;
        }

        public CallInfoBuilder withCallType(CallType callType) {
            this.callType = callType;
            return this;
        }

        public CallInfoBuilder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public CallInfoBuilder withAnswerCount(int answerCount) {
            this.answerCount = answerCount;
            return this;
        }

        public CallInfoBuilder withHangupDir(Integer hangupDir) {
            this.hangupDir = hangupDir;
            return this;
        }

        public CallInfoBuilder withSdkHangup(int sdkHangup) {
            this.sdkHangup = sdkHangup;
            return this;
        }

        public CallInfoBuilder withAnswerTime(Long answerTime) {
            this.answerTime = answerTime;
            return this;
        }

        public CallInfoBuilder withEndTime(Long endTime) {
            this.endTime = endTime;
            return this;
        }

        public CallInfoBuilder withTalkTime(Long talkTime) {
            this.talkTime = talkTime;
            return this;
        }

        public CallInfoBuilder withUuid1(String uuid1) {
            this.uuid1 = uuid1;
            return this;
        }

        public CallInfoBuilder withUuid2(String uuid2) {
            this.uuid2 = uuid2;
            return this;
        }

        public CallInfoBuilder withDeviceList(List<String> deviceList) {
            this.deviceList = deviceList;
            return this;
        }

        public CallInfoBuilder withDeviceInfoMap(Map<String, DeviceInfo> deviceInfoMap) {
            this.deviceInfoMap = deviceInfoMap;
            return this;
        }

        public CallInfoBuilder withFollowData(Map<String, Object> followData) {
            this.followData = followData;
            return this;
        }


        public CallInfo build() {
            CallInfo callInfo = new CallInfo();
            callInfo.followData = this.followData;
            callInfo.hangupDir = this.hangupDir;
            callInfo.sdkHangup = this.sdkHangup;
            callInfo.groupId = this.groupId;
            callInfo.hiddenCustomer = this.hiddenCustomer;
            callInfo.callTime = this.callTime;
            callInfo.callType = this.callType;
            callInfo.coreUuid = this.coreUuid;
            callInfo.mediaHost = this.mediaHost;
            callInfo.ctiHost = this.ctiHost;
            callInfo.clientHost = this.clientHost;
            callInfo.answerTime = this.answerTime;
            callInfo.record = this.record;
            callInfo.calledDisplay = this.calledDisplay;
            callInfo.direction = this.direction;
            callInfo.callId = this.callId;
            callInfo.ivrId = this.ivrId;
            callInfo.endTime = this.endTime;
            callInfo.callerDisplay = this.callerDisplay;
            callInfo.deviceList = this.deviceList;
            callInfo.called = this.called;
            callInfo.numberLocation = this.numberLocation;
            callInfo.agentKey = this.agentKey;
            callInfo.agentName = this.agentName;
            callInfo.loginType = this.loginType;
            callInfo.companyId = this.companyId;
            callInfo.caller = this.caller;
            callInfo.talkTime = this.talkTime;
            callInfo.uuid1 = this.uuid1;
            callInfo.uuid2 = this.uuid2;
            callInfo.answerCount = this.answerCount;
            callInfo.deviceInfoMap = this.deviceInfoMap;
            callInfo.nextCommands = this.nextCommands;
            return callInfo;
        }
    }

    @Override
    public String toString() {
        return "CallInfo{" +
                "coreUuid='" + coreUuid + '\'' +
                ", callId=" + callId +
                ", companyId=" + companyId +
                ", groupId=" + groupId +
                ", hiddenCustomer=" + hiddenCustomer +
                ", callerDisplay='" + callerDisplay + '\'' +
                ", caller='" + caller + '\'' +
                ", calledDisplay='" + calledDisplay + '\'' +
                ", called='" + called + '\'' +
                ", agentKey='" + agentKey + '\'' +
                ", numberLocation='" + numberLocation + '\'' +
                ", loginType=" + loginType +
                ", ivrId=" + ivrId +
                ", taskId=" + taskId +
                ", mediaHost='" + mediaHost + '\'' +
                ", ctiHost='" + ctiHost + '\'' +
                ", clientHost='" + clientHost + '\'' +
                ", record='" + record + '\'' +
                ", callTime=" + callTime +
                ", callType=" + callType +
                ", direction=" + direction +
                ", answerFlag=" + answerFlag +
                ", waitTime=" + waitTime +
                ", answerCount=" + answerCount +
                ", hangupDir=" + hangupDir +
                ", hangupCode=" + hangupCode +
                ", answerTime=" + answerTime +
                ", endTime=" + endTime +
                ", talkTime=" + talkTime +
                ", queueStartTime=" + queueStartTime +
                ", queueEndTime=" + queueEndTime +
                ", uuid1='" + uuid1 + '\'' +
                ", uuid2='" + uuid2 + '\'' +
                ", deviceList=" + deviceList +
                ", deviceInfoMap=" + deviceInfoMap +
                ", followData=" + followData +
                ", callDetails=" + callDetails +
                '}';
    }
}