package org.voice9.cc.service;

import com.voice9.core.entity.CallDetail;
import com.voice9.core.entity.CallDevice;
import com.voice9.core.entity.CallLog;
import com.voice9.core.entity.PushLog;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.CallLogPo;
import org.voice9.cc.entity.MakeCallVo;

import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
public interface CallCdrService extends BaseService<CallLog> {

    /**
     * @param callDevice
     * @return
     */
    int saveCallDevice(CallDevice callDevice);

    /**
     * @param callDetails
     * @return
     */
    int saveCallDetail(List<CallDetail> callDetails);


    int saveOrUpdateCallLog(CallLog callLog);


    /**
     * @param companyId
     * @param callId
     * @return
     */
    CallLogPo getCall(Long companyId, Long callId);


    /**
     * @param pushLog
     * @return
     */
    int savePushLog(PushLog pushLog);

    /**
     * 接口发起呼叫
     *
     * @param makeCallVo
     * @param agentInfo
     * @return
     */
    int makeCall(MakeCallVo makeCallVo, AgentInfo agentInfo);

    /**
     * 挂机
     *
     * @param deviceId
     */
    void hangupCall(CallInfo callInfo, String deviceId);


    /**
     * 通话保持
     *
     * @param callInfo
     * @param deviceId
     */
    void hold(CallInfo callInfo, String deviceId);

    /**
     * 取消保持
     *
     * @param callInfo
     * @param deviceId
     */
    void cancelHold(CallInfo callInfo, String deviceId);

    /**
     * 静音
     *
     * @param callInfo
     * @param deviceId
     */
    void readyMute(CallInfo callInfo, String deviceId);

    /**
     * 取消静音
     *
     * @param callInfo
     * @param deviceId
     */
    void cancelMute(CallInfo callInfo, String deviceId);


    List<CallDevice> callDeviceList(Map<String, Object> params);
}
