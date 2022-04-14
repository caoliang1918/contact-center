package org.zhongweixian.cc.command;

import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by caoliang on 2020/12/3
 */
@Component
public class TransferIvrHandler extends BaseHandler {

    /**
     * @param callInfo
     * @param deviceInfo
     * @param ivrId
     */
    public void handler(CallInfo callInfo, DeviceInfo deviceInfo, Long ivrId) {
        try {
            cacheService.addCallInfo(callInfo);
            Map<String, Object> params = new HashMap<>();
            params.put("callId", callInfo.getCallId());
            params.put("deviceId", deviceInfo.getDeviceId());
            params.put("ivrId", ivrId);
            ResponseEntity<String> responseEntity = httpClient.getForEntity("http://cc-ivr:7300/cc-ivr/index/start?callId={callId}&deviceId={deviceId}&ivrId={ivrId}", String.class, params);
            logger.info("transfer ivr success {}", callInfo.getCallId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
