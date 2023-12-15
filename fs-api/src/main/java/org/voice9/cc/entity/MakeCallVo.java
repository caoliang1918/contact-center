package org.voice9.cc.entity;

import com.voice9.core.enums.CallType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Created by caoliang on 2021/7/26
 */
public class MakeCallVo {


    /**
     * 随路数据
     */
    private Map<String, Object> followData;

    /**
     * uuid1
     */
    @Size(min = 2, max = 32, message = "uuid1必须在2,32字符")
    private String uuid1;

    /**
     * uuid2
     */
    @Size(min = 2, max = 32, message = "uuid2必须在2,32字符")
    private String uuid2;

    /**
     * 呼叫类型
     */
    @NotNull(message = "呼叫类型不能为空")
    private CallType callType;

    /**
     * 主叫，如果没有传，则使用坐席号码
     */
    @Size(min = 2, max = 16, message = "号码必须在2,16字符")
    private String caller;

    /**
     * 被叫
     */
    @NotNull(message = "被叫号码不能为空")
    @Size(min = 2, max = 16, message = "号码必须在2,16字符")
    private String called;

    /**
     * 主叫显号，接口没有传就用主技能组配置
     */
    @Size(min = 2, max = 16, message = "坐席显号必须在2,16字符")
    private String callerDisplay;

    /**
     * 被叫显号，接口没传就用主技能配置
     */
    @Size(min = 2, max = 16, message = "显号必须在2,16字符")
    private String calledDisplay;


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

    public String getCallerDisplay() {
        return callerDisplay;
    }

    public void setCallerDisplay(String callerDisplay) {
        this.callerDisplay = callerDisplay;
    }

    public String getCalledDisplay() {
        return calledDisplay;
    }

    public void setCalledDisplay(String calledDisplay) {
        this.calledDisplay = calledDisplay;
    }

    @Override
    public String toString() {
        return "MakeCallVo{" +
                "followData=" + followData +
                ", uuid1='" + uuid1 + '\'' +
                ", uuid2='" + uuid2 + '\'' +
                ", callType=" + callType +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", callerDisplay='" + callerDisplay + '\'' +
                ", calledDisplay='" + calledDisplay + '\'' +
                '}';
    }
}
