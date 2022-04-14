package org.zhongweixian.cc.command.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.po.NextCommand;
import org.cti.cc.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.*;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.service.GroupMemoryService;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.fs.esl.transport.SendMsg;

/**
 * Created by caoliang on 2020/8/23
 */
public class BaseHandler {
    protected Logger logger = LoggerFactory.getLogger(BaseHandler.class);

    @Value("${record.path:/app/clpms/record/}")
    protected String recordPath;

    @Autowired
    protected FsListen fsListen;

    @Autowired
    protected CacheService cacheService;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected TransferIvrHandler transferIvrHandler;

    @Autowired
    protected TransferCallHandler transferCallHandler;

    @Autowired
    protected TransferAgentHandler transferAgentHandler;

    @Autowired
    protected VdnHandler vdnHandler;

    @Autowired
    protected CallCdrService callCdrService;

    @Autowired
    protected WebSocketHandler webSocketHandler;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected GroupMemoryService groupMemoryService;

    @Autowired
    protected OverFlowHandler overFlowHandler;

    @Autowired
    protected SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected RestTemplate httpClient;

    /**
     * 被叫挂机时，需要把主叫挂机，主叫挂机时不需要单独挂被叫
     *
     * @param callId
     * @param deviceId
     */
    protected void hangupCall(String media, Long callId, String deviceId) {
        fsListen.hangupCall(media, callId, deviceId);
    }

    /**
     * 放音
     *
     * @param media
     * @param deviceId
     * @param playback
     */
    protected void playback(String media, String deviceId, String playback) {
        fsListen.playBack(media, deviceId, playback);
    }

    /**
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void receiveDtmf(String media, Long callId, String deviceId) {
        SendMsg play = new SendMsg(deviceId);
        play.addCallCommand("execute");
        play.addExecuteAppName("set");
        play.addExecuteAppArg("playback_delimiter=!");
        fsListen.sendMessage(media, play);

        SendMsg digits = new SendMsg(deviceId);
        digits.addCallCommand("execute");
        digits.addExecuteAppName("play_and_get_digits");
        digits.addExecuteAppArg("3 3 2 5000 # /app/clpms/sounds/1295e6a58f9e2115332666.wav silence_stream://250 SYMWRD_DTMF_RETURN [\\*0-9#]+ 3000");
        fsListen.sendMessage(media, digits);
    }

    /**
     * 随机生成deviceId
     *
     * @return
     */
    protected String getDeviceId() {
        return RandomStringUtils.randomNumeric(16);
    }


    /**
     * @param callInfo
     * @param deviceInfo
     */
    protected void doNextCommand(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand) {
        if (nextCommand == null) {
            return;
        }
        callInfo.getNextCommands().remove(nextCommand);
        switch (nextCommand.getNextType()) {
            case NEXT_QUEUE_PLAY:
                fsListen.playBack(callInfo.getMediaHost(), deviceInfo.getDeviceId(), "/app/clpms/sounds/queue.wav");
                break;

            case NEXT_QUEUE_OVERFLOW_GROUP:
                GroupInfo groupInfo = cacheService.getGroupInfo(Long.parseLong(nextCommand.getNextValue()));
                groupHandler.hander(callInfo, groupInfo, deviceInfo.getDeviceId());
                break;

            case NEXT_QUEUE_OVERFLOW_IVR:
                break;

            case NEXT_QUEUE_OVERFLOW_VDN:
                break;

            case NEXT_HANGUP:
                fsListen.hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId());
                break;

            case NEXT_VDN:
                break;

            case NEXT_GROUP:
                break;

            case NEXT_IVR:
                break;

            default:
                break;
        }
        callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);
        cacheService.addCallInfo(callInfo);
    }


    protected String hiddenNumber(String number) {
        if (StringUtils.isBlank(number)) {
            return null;
        }
        if (number.length() >= 11) {
            return number.substring(0, 3) + "****" + number.substring(7);
        }
        if (number.length() < 3) {
            return number;
        }
        return number.substring(0, 3) + randomHidden(number.length());
    }

    private String randomHidden(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            builder.append("*");
        }
        return builder.toString();
    }
}
