package org.zhongweixian.cc.service;

import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.PushFailLog;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.CallLogPo;
import org.zhongweixian.cc.entity.MakeCallVo;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
public interface CallCdrService extends BaseService<CallLog> {

    void subTable(String month);

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
     * @param pushFailLog
     * @return
     */
    int savePushFailLog(PushFailLog pushFailLog);

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
}
