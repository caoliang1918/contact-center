package org.zhongweixian.cc.service;

import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.PushFailLog;
import org.cti.cc.po.CallLogPo;

/**
 * Create by caoliang on 2020/10/28
 */
public interface CallCdrService extends BaseService<CallLog>{

    /**
     *
     * @param callDevice
     * @return
     */
    int saveCallDevice(CallDevice callDevice);

    /**
     *
     * @param callDetail
     * @return
     */
    int saveCallDetail(CallDetail callDetail);



    int saveOrUpdateCallLog(CallLog callLog);


    /**
     *
     * @param companyId
     * @param callId
     * @return
     */
    CallLogPo getCall(Long companyId , Long callId);


    /**
     *
     * @param pushFailLog
     * @return
     */
    int savePushFailLog(PushFailLog pushFailLog);
}
