package org.zhongweixian.cc.fs.handler.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.FsConstant;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.*;
import org.zhongweixian.cc.command.base.BaseHandler;
import org.zhongweixian.cc.configration.Handler;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.util.Constants;
import org.zhongweixian.cc.util.SnowflakeIdWorker;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;
import org.zhongweixian.esl.transport.SendMsg;

/**
 * Created by caoliang on 2020/8/23
 */
public abstract class BaseEventHandler<T extends FsBaseEvent> extends BaseHandler implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(BaseEventHandler.class);

    @Autowired
    protected CacheService cacheService;

    @Autowired
    protected FsListen fsListen;

    @Autowired
    protected CallCdrService callCdrService;

    @Autowired
    protected WebSocketHandler webSocketHandler;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected VdnHandler vdnHandler;

    @Autowired
    protected TransferIvrHandler transferIvrHandler;

    @Autowired
    protected TransferAgentHandler transferAgentHandler;

    @Autowired
    protected TransferCallHandler transferCallHandler;

    @Autowired
    protected OverFlowHandler overFlowHandler;

    @Autowired
    protected SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 发送消息到ws客户端
     *
     * @param agentKey
     * @param responseEntity
     * @return
     */
    protected int sendAgentMessage(String agentKey, WsResponseEntity responseEntity) {
        return webSocketHandler.sendMessgae(agentKey, JSON.toJSON(responseEntity).toString());
    }


    /**
     * 坐席状态变更
     *
     * @param agentInfo
     */
    protected void sendAgentStateMessage(AgentInfo agentInfo) {
        agentService.sendAgentStateMessage(agentInfo);
    }


    /**
     * bgapi originate {return_ring_ready=true,sip_contact_user=01017718388,ring_asr=true,absolute_codec_string=^^:G729:PCMU:PCMA,origination_caller_id_number=01017718388,origination_caller_id_name=01017718388,origination_uuid=sswitch-25-5f8fe34b-1bb-76,sip_auto_answer=true}sofia/external/8731279@192.168.177.183:8880 &park()
     * Job-UUID: sswitch-25-5f8fe34b-1bb-76
     *
     * @param media
     * @param caller
     * @param external
     * @param device
     */
    public void makeCall(String media, String caller, String external, String device) {
        fsListen.makeCall(media, caller, external, device);
    }


    /**
     * 桥接device
     *
     * @param media
     * @param device1
     * @param device2
     */
    protected void callBridge(String media, String device1, String device2) {
        fsListen.sendArgs(media, device1, FsConstant.SET, FsConstant.PARK_AFTER_BRIDGE);
        fsListen.sendArgs(media, device1, FsConstant.SET, FsConstant.HANGUP_AFTER_BRIDGE);
        fsListen.sendArgs(media, device2, FsConstant.SET, FsConstant.HANGUP_AFTER_BRIDGE);
        fsListen.sendArgs(media, device2, FsConstant.SET, FsConstant.PARK_AFTER_BRIDGE);
        StringBuilder builder = new StringBuilder();
        builder.append(device1).append(Constants.SPACE).append(device2);
        fsListen.sendBgapiMessage(media, Constants.BRIDGE, builder.toString());
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
     * 开始录音
     * <p>
     * 手动外呼全程录音：被叫振铃开始录音
     * 手动外呼非全程录音：被叫接起开始录音
     *
     * @param media
     * @param deviceId
     * @param file
     */
    protected void record(String media, Long callId, String deviceId, String file) {
//        fsListen.sendArgs(media, deviceId, "record_sample_rate", "8000");
       /* logger.info("开始录音 callId:{}, deviceId:{}, file:{}", callId, deviceId, file);
        StringBuilder sb = new StringBuilder();
        sb.append(deviceId).append(Constants.SPACE).append(Constants.START).append(Constants.SPACE).append(file);
        fsListen.sendBgapiMessage(media, Constants.RECORD, sb.toString());*/
    }

    protected void transferCall(String media, String from, String to) {
        fsListen.transferCall(media, from, to);
    }


    /**
     * 放音
     *
     * @param media
     * @param deviceId
     * @param file
     */
    protected void playback(String media, String deviceId, String file) {
        SendMsg playback = new SendMsg(deviceId);
        playback.addCallCommand(FsConstant.EXECUTE);
        playback.addExecuteAppName(FsConstant.PLAYBACK);
        playback.addExecuteAppArg(file);
        fsListen.sendMessage(media, playback);
    }

    /**
     * 被叫挂机时，需要把主叫挂机，主叫挂机时不需要单独挂被叫
     *
     * @param callId
     * @param deviceId
     */
    protected void hangupCall(String media, Long callId, String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            return;
        }
        SendMsg hangupMsg = new SendMsg(deviceId);
        hangupMsg.addCallCommand(FsConstant.EXECUTE);
        hangupMsg.addExecuteAppName(FsConstant.HANGUP);
        hangupMsg.addExecuteAppArg(FsConstant.NORMAL_CLEARING);
        logger.info("hangup callId:{}, device:{}", callId, deviceId);
        fsListen.sendMessage(media, hangupMsg);
    }

    /**
     * uuid应答
     *
     * @param media
     * @param deviceId
     */
    protected void answer(String media, String deviceId) {
        SendMsg answer = new SendMsg(deviceId);
        answer.addCallCommand(FsConstant.EXECUTE);
        answer.addExecuteAppName(FsConstant.ANSWER);
        fsListen.sendMessage(media, answer);
    }

    /**
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void receiveDtmf(String media, Long callId, String deviceId) {
        SendMsg play = new SendMsg(deviceId);
        play.addCallCommand(FsConstant.EXECUTE);
        play.addExecuteAppName(FsConstant.SET);
        play.addExecuteAppArg(FsConstant.PLAYBACK_DELIMITER);
        fsListen.sendMessage(media, play);

        SendMsg digits = new SendMsg(deviceId);
        digits.addCallCommand(FsConstant.EXECUTE);
        digits.addExecuteAppName(FsConstant.PLAY_AND_GET_DIGITS);
        digits.addExecuteAppArg("1 1 1 5000 # /app/clpms/sounds/1295e6a58f9e2115332666.wav silence_stream://250 SYMWRD_DTMF_RETURN [\\*0-9#]+ 3000");
        fsListen.sendMessage(media, digits);
    }

    protected String getDeviceId(CallInfo callInfo, String... deviceIds) {
        for (String deviceId : callInfo.getDeviceList()) {
            for (String str : deviceIds) {
                if (!deviceId.equals(str)) {
                    return deviceId;
                }
            }
        }
        return null;
    }

    protected String hiddenNumber(String number) {
        if (number.length() == 11) {
            return number.substring(0, 3) + "****" + number.substring(7, 11);
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
