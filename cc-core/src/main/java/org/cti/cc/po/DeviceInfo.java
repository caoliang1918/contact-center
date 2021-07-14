package org.cti.cc.po;

/**
 * Created by caoliang on 2020/8/31
 */
public class DeviceInfo {

    /**
     * 通话唯一标识
     */
    private Long callId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 坐席
     */
    private String agentKey;

    /**
     * 1:坐席,2:客户,3:外线
     */
    private Integer deviceType;

    /**
     * 1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插
     */
    private Integer cdrType;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫
     */
    private String called;

    /**
     * 显号
     */
    private String display;

    /**
     * 被叫归属地
     */
    private String calledLocation;

    /**
     * 被叫归属地
     */
    private String callerLocation;

    /**
     * 呼叫开始时间
     */
    private Long callTime;

    /**
     * 振铃开始时间
     */
    private Long ringStartTime;

    /**
     * 振铃结束时间
     */
    private Long ringEndTime;

    /**
     * 接通时间
     */
    private Long answerTime;

    /**
     * 桥接时间
     */
    private Long bridgeTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 通话时长
     */
    private Long talkTime;

    /**
     * 信令协议(tcp/udp)
     */
    private String sipProtocol;

    /**
     * 呼叫地址
     */
    private String channelName;

    /**
     * 挂机原因
     */
    private String hangupCause;

    /**
     * 回铃音识别
     */
    private String ringCause;

    /**
     * sip状态
     */
    private String sipStatus;

    /**
     * 记录当前设备下一步action
     */
    private NextCommand nextCommand;


    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getCdrType() {
        return cdrType;
    }

    public void setCdrType(Integer cdrType) {
        this.cdrType = cdrType;
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

    public String getCalledLocation() {
        return calledLocation;
    }

    public void setCalledLocation(String calledLocation) {
        this.calledLocation = calledLocation;
    }

    public String getCallerLocation() {
        return callerLocation;
    }

    public void setCallerLocation(String callerLocation) {
        this.callerLocation = callerLocation;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getRingStartTime() {
        return ringStartTime;
    }

    public void setRingStartTime(Long ringStartTime) {
        this.ringStartTime = ringStartTime;
    }

    public Long getRingEndTime() {
        return ringEndTime;
    }

    public void setRingEndTime(Long ringEndTime) {
        this.ringEndTime = ringEndTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
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

    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    public String getRingCause() {
        return ringCause;
    }

    public void setRingCause(String ringCause) {
        this.ringCause = ringCause;
    }

    public String getSipStatus() {
        return sipStatus;
    }

    public void setSipStatus(String sipStatus) {
        this.sipStatus = sipStatus;
    }

    public NextCommand getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    public static final class DeviceInfoBuilder {
        private Long callId;
        private String deviceId;
        private Integer deviceType;
        private Integer cdrType;
        private String agentKey;
        private String caller;
        private String calledLocation;
        private String callerLocation;
        private String called;
        private String display;
        private Long callTime;
        private Long ringStartTime;
        private Long ringEndTime;
        private Long answerTime;
        private Long endTime;
        private NextCommand nextCommand;

        private DeviceInfoBuilder() {
        }

        public static DeviceInfoBuilder builder() {
            return new DeviceInfoBuilder();
        }

        public DeviceInfoBuilder withCallId(Long callId) {
            this.callId = callId;
            return this;
        }

        public DeviceInfoBuilder withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public DeviceInfoBuilder withCdrType(Integer cdrType){
             this.cdrType = cdrType;
             return this;
        }

        public DeviceInfoBuilder withAgentKey(String agentKey){
            this.agentKey = agentKey;
            return this;
        }

        public DeviceInfoBuilder withDeviceType(Integer deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public DeviceInfoBuilder withCaller(String caller) {
            this.caller = caller;
            return this;
        }

        public DeviceInfoBuilder withCallerLocation(String callerLocation) {
            this.callerLocation = callerLocation;
            return this;
        }
        public DeviceInfoBuilder withCalledLocation(String calledLocation) {
            this.calledLocation = calledLocation;
            return this;
        }

        public DeviceInfoBuilder withCalled(String called) {
            this.called = called;
            return this;
        }

        public DeviceInfoBuilder withDisplay(String display) {
            this.display = display;
            return this;
        }

        public DeviceInfoBuilder withCallTime(Long callTime) {
            this.callTime = callTime;
            return this;
        }

        public DeviceInfoBuilder withRingStartTime(Long ringStartTime) {
            this.ringStartTime = ringStartTime;
            return this;
        }

        public DeviceInfoBuilder withRingEndTime(Long ringEndTime) {
            this.ringEndTime = ringEndTime;
            return this;
        }

        public DeviceInfoBuilder withAnswerTime(Long answerTime) {
            this.answerTime = answerTime;
            return this;
        }

        public DeviceInfoBuilder withEndTime(Long endTime) {
            this.endTime = endTime;
            return this;
        }

        public DeviceInfoBuilder withNextCmd(NextCommand nextCommand) {
            this.nextCommand = nextCommand;
            return this;
        }


        public DeviceInfo build() {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.deviceType = this.deviceType;
            deviceInfo.callerLocation = this.callerLocation;
            deviceInfo.calledLocation = this.calledLocation;
            deviceInfo.endTime = this.endTime;
            deviceInfo.cdrType = this.cdrType;
            deviceInfo.agentKey = this.agentKey;
            deviceInfo.deviceId = this.deviceId;
            deviceInfo.called = this.called;
            deviceInfo.callTime = this.callTime;
            deviceInfo.answerTime = this.answerTime;
            deviceInfo.ringEndTime = this.ringEndTime;
            deviceInfo.callId = this.callId;
            deviceInfo.display = this.display;
            deviceInfo.ringStartTime = this.ringStartTime;
            deviceInfo.caller = this.caller;
            deviceInfo.nextCommand = this.nextCommand;
            return deviceInfo;
        }
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "callId=" + callId +
                ", deviceId='" + deviceId + '\'' +
                ", agentKey='" + agentKey + '\'' +
                ", deviceType=" + deviceType +
                ", cdrType=" + cdrType +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", display='" + display + '\'' +
                ", calledLocation='" + calledLocation + '\'' +
                ", callerLocation='" + callerLocation + '\'' +
                ", callTime=" + callTime +
                ", ringStartTime=" + ringStartTime +
                ", ringEndTime=" + ringEndTime +
                ", answerTime=" + answerTime +
                ", bridgeTime=" + bridgeTime +
                ", endTime=" + endTime +
                ", talkTime=" + talkTime +
                ", sipProtocol='" + sipProtocol + '\'' +
                ", channelName='" + channelName + '\'' +
                ", hangupCause='" + hangupCause + '\'' +
                ", ringCause='" + ringCause + '\'' +
                ", sipStatus='" + sipStatus + '\'' +
                ", nextCommand=" + nextCommand +
                '}';
    }
}
