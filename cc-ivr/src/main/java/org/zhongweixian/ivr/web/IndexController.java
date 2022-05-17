package org.zhongweixian.ivr.web;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.CallInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhongweixian.ivr.cache.CacheService;
import org.zhongweixian.ivr.exception.BusinessException;
import org.zhongweixian.ivr.scxml.SimpleIvrMachine;

/**
 * Created by caoliang on 2021/8/10
 */

@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private SimpleIvrMachine ivrMachine;

    @Autowired
    private CacheService cacheService;

    /**
     * @param callId
     * @param deviceId
     * @param ivrId
     * @return
     */
    @GetMapping("start")
    public String index(@RequestParam Long callId, @RequestParam String deviceId, @RequestParam Long ivrId) {
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        ivrMachine.startRun(callInfo, deviceId, ivrId);
        return "is ok";
    }

    @GetMapping("start2")
    public String index2(Long callId) {
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(callId).build();
        // ivrMachine.executeNext(callInfo.getCallId());
        return "is ok";
    }


    @GetMapping("health")
    public Health health() {
        return Health.up().build();
    }
}
