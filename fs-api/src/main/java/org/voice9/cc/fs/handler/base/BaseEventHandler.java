package org.voice9.cc.fs.handler.base;

import com.alibaba.fastjson.JSON;
import io.netty.util.HashedWheelTimer;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.FsConstant;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.voice9.cc.command.base.BaseHandler;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.fs.esl.transport.SendMsg;
import org.voice9.cc.fs.event.base.FsBaseEvent;
import org.voice9.cc.websocket.response.WsResponseEntity;

/**
 * Created by caoliang on 2020/8/23
 */
public abstract class BaseEventHandler<T extends FsBaseEvent> extends BaseHandler implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(BaseEventHandler.class);

    @Value("${fs.sample.rate:8000}")
    protected String sampleRate;

    @Value("${fs.record.file:wav}")
    protected String recordFile;

    @Value("${voice9.minio.server:}")
    protected String ossServer;

    /**
     * 硬话机外呼走的profile
     */
    @Value("${sip.outbound.profile:internal}")
    protected String outboundProfile;

    @Autowired
    protected HashedWheelTimer hashedWheelTimer;


    /**
     * 给坐席客户端发送消息
     *
     * @param agentInfo
     * @param responseEntity
     */
    protected void sendWsMessage(AgentInfo agentInfo, WsResponseEntity responseEntity) {
        if (StringUtils.isBlank(agentInfo.getHost())) {
            return;
        }
        webSocketHandler.sendMessgae(agentInfo, JSON.toJSONString(responseEntity));
    }

    /**
     * 桥接device
     *
     * @param mediaHost
     * @param device1
     * @param device2
     */
    protected void bridgeCall(String mediaHost, Long callId, String device1, String device2) {
        fsListen.bridgeCall(mediaHost, callId, device1, device2);
    }

    protected void playBreak(String mediaHost, String device) {
        fsListen.playBreak(mediaHost, device);
    }

    protected void holdPlay(String mediaHost, String deviceId, String play) {
        fsListen.holdPlay(mediaHost, deviceId, play);
    }

    /**
     * 把通话中的设备拆出来
     *
     * @param mediaHost
     * @param deviceId
     */
    protected void bridgeBreak(String mediaHost, String deviceId) {
        fsListen.bridgeBreak(mediaHost, deviceId);
    }



    /**
     * 开始录音
     * <p>
     * 手动外呼全程录音：被叫振铃开始录音
     * 手动外呼非全程录音：被叫接起开始录音
     *
     * @param mediaHost
     * @param deviceId
     * @param file
     */
    protected void record(String mediaHost, Long callId, String deviceId, String file) {
        //设置8kHz采样率
        SendMsg msg = new SendMsg(deviceId);
        msg.addCallCommand(FsConstant.EXECUTE);
        msg.addExecuteAppName(FsConstant.SET);
        msg.addExecuteAppArg(FsConstant.RECORD_SAMPLE_RATE + sampleRate);
        msg.addAsync();
        fsListen.sendSyncMessage(mediaHost, msg);
        //双声道录音,默认是单声道录音
        fsListen.sendBgapiMessage(mediaHost, FsConstant.SETVAR, deviceId + FsConstant.RECORD_STEREO);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deviceId).append(FsConstant.SPACE).append(FsConstant.START).append(FsConstant.SPACE).append(file);
        fsListen.sendBgapiMessage(mediaHost, FsConstant.RECORD, stringBuilder.toString());
        logger.info("开始录音 callId:{}, deviceId:{}, record:{}", callId, deviceId, file);
    }

    protected void transferCall(String mediaHost, String from, String to) {
        fsListen.transferCall(mediaHost, from, to);
    }


    /**
     * uuid应答
     *
     * @param mediaHost
     * @param deviceId
     */
    protected void answer(String mediaHost, String deviceId) {
        SendMsg answer = new SendMsg(deviceId);
        answer.addCallCommand(FsConstant.EXECUTE);
        answer.addExecuteAppName(FsConstant.ANSWER);
        fsListen.sendMessage(mediaHost, answer);
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

}
