package org.zhongweixian.cc.entity;

import org.cti.cc.enums.CallType;

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
    private String uuid1;

    /**
     * uuid2
     */
    private String uuid2;

    /**
     * 呼叫类型
     */
    @NotNull(message = "呼叫类型不能为空")
    private CallType callType;

    /**
     * 被叫
     */
    @NotNull(message = "被叫号码不能为空")
    @Size(min = 2, max = 16, message = "号码必须在2,16字符")
    private String called;

    /**
     * 用户侧显号，前端没传就用主技能配置
     */
    @Size(min = 2, max = 16, message = "显号必须在2,16字符")
    private String display;

    /**
     * 坐席侧显号，前端没有传就用主技能组配置
     */
    @Size(min = 2, max = 16, message = "坐席显号必须在2,16字符")
    private String callerDiaplay;

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

    public String getCallerDiaplay() {
        return callerDiaplay;
    }

    public void setCallerDiaplay(String callerDiaplay) {
        this.callerDiaplay = callerDiaplay;
    }
}
