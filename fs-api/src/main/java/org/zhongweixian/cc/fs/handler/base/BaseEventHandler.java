package org.zhongweixian.cc.fs.handler.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.FsConstant;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.zhongweixian.cc.command.base.BaseHandler;
import org.zhongweixian.cc.configration.Handler;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;
import org.zhongweixian.esl.transport.SendMsg;

/**
 * Created by caoliang on 2020/8/23
 */
public abstract class BaseEventHandler<T extends FsBaseEvent> extends BaseHandler implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(BaseEventHandler.class);

    @Value("${SAMPLE_RATE:8000}")
    private String sampleRate;

    /**
     * 给坐席客户端发送消息
     *
     * @param agentInfo
     * @param responseEntity
     */
    protected void sendAgentStateMessage(AgentInfo agentInfo, WsResponseEntity responseEntity) {
        if (StringUtils.isBlank(agentInfo.getHost())) {
            return;
        }
        webSocketHandler.sendMessgae(agentInfo, JSON.toJSONString(responseEntity));
    }

    /**
     * 桥接device
     *
     * @param media
     * @param device1
     * @param device2
     */
    protected void callBridge(String media, String device1, String device2) {
        fsListen.callBridge(media, device1, device2);
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
        //设置8kHz采样率
        SendMsg msg = new SendMsg(deviceId);
        msg.addCallCommand(FsConstant.EXECUTE);
        msg.addExecuteAppName(FsConstant.SET);
        msg.addExecuteAppArg(FsConstant.RECORD_SAMPLE_RATE + sampleRate);
        msg.addAsync();
        fsListen.sendSyncMessage(media, msg);
        //双声道录音,默认是单声道录音
        fsListen.sendBgapiMessage(media, FsConstant.SETVAR, deviceId + FsConstant.RECORD_STEREO);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deviceId).append(FsConstant.SPACE).append(FsConstant.START).append(FsConstant.SPACE).append(file);
        fsListen.sendBgapiMessage(media, FsConstant.RECORD, stringBuilder.toString());
        logger.info("开始录音 callId:{}, deviceId:{}, record:{}", callId, deviceId, file);
    }

    protected void transferCall(String media, String from, String to) {
        fsListen.transferCall(media, from, to);
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
