package org.zhongweixian.web;

import org.apache.commons.lang3.RandomStringUtils;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.CallLogPo;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.po.DeviceInfo;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.util.SnowflakeIdWorker;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by caoliang on 2020/8/24
 */
@RestController
@RequestMapping("index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private FsListen fsListen;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CallCdrService callCdrService;

    @Autowired
    private StringEncryptor encrypt;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 模拟手动外呼
     *
     * @return
     */
    @GetMapping("outCall")
    public DeferredResult<CommonResponse<CallInfo>> outCall(@RequestParam() String display, @RequestParam String caller, @RequestParam String called) {
        DeferredResult<CommonResponse<CallInfo>> output = new DeferredResult<>();

        //先呼坐席sip号
        Long callId = snowflakeIdWorker.nextId();
        String device = RandomStringUtils.randomNumeric(16);
        fsListen.makeCall("", display, caller + "@115.159.101.178:8880", device);

        Long now = Instant.now().toEpochMilli();
        CallInfo callInfo = new CallInfo();
        callInfo.setCallId(callId);
        callInfo.setCallTime(now);
        callInfo.setCaller(display);
        callInfo.setCalled(called);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(device);
        deviceInfo.setDisplay(display);
        deviceInfo.setCaller("1002");
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(now);
        callInfo.getDeviceInfoMap().put(device, deviceInfo);
        callInfo.getDeviceList().add(device);
        cacheService.addCallInfo(callInfo);
        logger.info("callout caller:{} , callId:{}, device:{}", caller, callId, device);
        cacheService.addDevice(device, callId);

        ForkJoinPool.commonPool().submit(() -> {
            logger.info("Processing in separate thread");
            Integer timeout = 0;
            while (true) {
                CallInfo callInfoCache = cacheService.getCallInfo(callId);
                if (callInfoCache.getDeviceInfoMap().size() == 2) {
                    output.setResult(new CommonResponse<CallInfo>(callInfoCache));
                    logger.info("get second device");
                    break;
                }
                if (timeout > 10) {
                    output.setResult(new CommonResponse<CallInfo>());
                    break;
                }
                try {
                    logger.info("sleep {}s", timeout);
                    TimeUnit.SECONDS.sleep(1);
                    timeout++;
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        return output;
    }


    /**
     * @param callId
     * @return
     */
    @GetMapping("getCall")
    public CommonResponse<CallLogPo> getCall(@RequestParam Long callId) {
        return new CommonResponse<>(callCdrService.getCall(null, callId));
    }

    @GetMapping("/encrypt")
    public CommonResponse encrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.encrypt(text));
    }


    @GetMapping("/decrypt")
    public CommonResponse decrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.decrypt(text));
    }
}

