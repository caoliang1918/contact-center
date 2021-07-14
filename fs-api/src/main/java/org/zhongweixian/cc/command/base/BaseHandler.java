package org.zhongweixian.cc.command.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.po.NextCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.command.TransferAgentHandler;
import org.zhongweixian.cc.command.TransferCallHandler;
import org.zhongweixian.cc.command.TransferIvrHandler;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.esl.transport.SendMsg;

/**
 * Created by caoliang on 2020/8/23
 */
public class BaseHandler {
    protected Logger logger = LoggerFactory.getLogger(BaseHandler.class);

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
        fsListen.playback(media, deviceId, playback);
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
        digits.addExecuteAppArg("1 1 1 5000 # /app/clpms/sounds/1295e6a58f9e2115332666.wav silence_stream://250 SYMWRD_DTMF_RETURN [\\*0-9#]+ 3000");
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
    protected void doNextCommand(CallInfo callInfo, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getNextCommand() == null) {
            return;
        }
        NextCommand nextCommand = deviceInfo.getNextCommand();
        switch (nextCommand.getNextType()) {
            case NEXT_QUEUE_PLAY:
                fsListen.playback(callInfo.getMedia(), deviceInfo.getDeviceId(), "/app/clpms/sounds/queue.wav");
                break;

            case NEXT_QUEUE_OVERFLOW_GROUP:
                GroupInfo groupInfo = cacheService.getGroupInfo(Long.parseLong(nextCommand.getNextValue()));
                groupHandler.hander(deviceInfo.getDeviceId(), callInfo, groupInfo);
                break;

            case NEXT_QUEUE_OVERFLOW_IVR:
                break;

            case NEXT_QUEUE_OVERFLOW_VDN:
                break;

            case NEXT_HANGUP:
                fsListen.hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceInfo.getDeviceId());
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
        deviceInfo.setNextCommand(null);
        callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);
        cacheService.addCallInfo(callInfo);
    }

}
