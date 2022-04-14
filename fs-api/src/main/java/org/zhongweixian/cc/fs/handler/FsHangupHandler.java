package org.zhongweixian.cc.fs.handler;

import com.alibaba.fastjson.JSON;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.PushFailLog;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.CauseEnums;
import org.cti.cc.enums.Direction;
import org.cti.cc.po.*;
import org.cti.cc.vo.AgentPreset;
import org.cti.cc.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsHangupEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.zhongweixian.cc.websocket.response.WsCallAfterEntity;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by caoliang on 2020/8/23
 * <p>
 * 设备挂断处理类
 */
@Component
@HandlerType("CHANNEL_HANGUP")
public class FsHangupHandler extends BaseEventHandler<FsHangupEvent> {
    private Logger logger = LoggerFactory.getLogger(FsHangupHandler.class);

    private static final String YYYYMMDDHH = "yyyyMMddHH";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MinioClient minioClient;

    @Value("${record.ip:}")
    private String record;

    @Override
    public void handleEvent(FsHangupEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        Integer count = callInfo.getDeviceList().size();
        callInfo.getDeviceList().remove(event.getDeviceId());
        String cause = event.getHangupCause();

        /**
         * 挂机原因
         */
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo == null) {
            logger.warn("device:{} is null", event.getDeviceId());
            return;
        }
        logger.info("callId:{} deviceId:{} deviceType:{} diaplay:{} called:{} hangup sipStatus:{} sipProtocol:{} rtpUseCodec:{} hangupCause:{}", callInfo.getCallId(), event.getDeviceId(), deviceInfo.getDeviceType(), event.getCaller(), event.getCalled(), event.getSipStatus(), event.getSipProtocol(), event.getRtpUseCodec(), event.getHangupCause());
        /**
         * 上传录音
         */
        if (StringUtils.isNotBlank(deviceInfo.getRecord())) {
            try {
                String day = DateFormatUtils.format(new Date(), YYYYMMDDHH);
                String recordIp = StringUtils.isBlank(record) ? event.getLocalMediaIp() : record;
                String fileName = day.substring(0, 6) + Constant.SK + day.substring(0, 8) + Constant.SK + day.substring(8, 10) + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + deviceInfo.getDeviceId() + Constant.POINT + recordFile;
                ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(Constant.HTTP + recordIp + deviceInfo.getRecord(), byte[].class);
                logger.info("get record file:{}", deviceInfo.getRecord());
                ObjectWriteResponse writeResponse = minioClient.putObject(PutObjectArgs.builder().stream(new ByteArrayInputStream(responseEntity.getBody()), responseEntity.getBody().length, -1).object(fileName).bucket(Constant.RRCORD_BUCKET).build());
                logger.info("callId:{}, record fileName:{}, minioTag:{}", deviceInfo.getCallId(), fileName, writeResponse.etag());
                deviceInfo.setRecord(fileName);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        deviceInfo.setHangupCause(event.getHangupCause());
        deviceInfo.setSipProtocol(event.getSipProtocol());
        deviceInfo.setSipStatus(event.getSipStatus());
        deviceInfo.setChannelName(event.getChannelName());
        deviceInfo.setEndTime(event.getTimestamp() / 1000);
        //计算通话时长
        if (deviceInfo.getAnswerTime() != null) {
            deviceInfo.setTalkTime(deviceInfo.getEndTime() - deviceInfo.getAnswerTime());
        } else {
            deviceInfo.setRingEndTime(deviceInfo.getEndTime());
        }
        //计算录音时长
        if (deviceInfo.getRecordStartTime() != null) {
            deviceInfo.setRecordTime(deviceInfo.getEndTime() - deviceInfo.getRecordStartTime());
        }

        CallDevice callDevice = new CallDevice();
        BeanUtils.copyProperties(deviceInfo, callDevice);
        callDevice.setCts(deviceInfo.getCallTime() / 1000);
        callDevice.setUts(callDevice.getEndTime() / 1000);
        if (callInfo.getHiddenCustomer() == 1) {
            //隐藏客户侧号码
            if (callInfo.getDirection() == Direction.INBOUND && deviceInfo.getDeviceType() == 2) {
                callDevice.setCaller(hiddenNumber(deviceInfo.getCaller()));
                callDevice.setChannelName(event.getChannelName().replace(deviceInfo.getCaller(), callDevice.getCaller()));
            } else if (callInfo.getDirection() == Direction.OUTBOUND && deviceInfo.getDeviceType() == 2) {
                callDevice.setCalled(hiddenNumber(deviceInfo.getCalled()));
                callDevice.setChannelName(event.getChannelName().replace(deviceInfo.getCalled(), callDevice.getCalled()));
            }
            if (callInfo.getDirection() == Direction.INBOUND && deviceInfo.getDeviceType() != 2) {
                callDevice.setDisplay(hiddenNumber(callDevice.getDisplay()));
            }
        }
        callCdrService.saveCallDevice(callDevice);
        callInfo.getDeviceInfoMap().put(callDevice.getDeviceId(), deviceInfo);

        /**
         * 同步坐席状态
         */
        if (deviceInfo.getDeviceType() == 1) {
            agentState(deviceInfo, callInfo, cause);
        }

        /**
         * 有下一步
         */
        NextCommand nextCommand = callInfo.getNextCommands().size() == 0 ? null : callInfo.getNextCommands().get(0);
        if (nextCommand != null) {
            nextCmd(callInfo, deviceInfo, nextCommand, cause);
            return;
        }


        /**
         * 一般情况下，挂断其他所有设备
         */
        if (deviceInfo.getCdrType() <= 3 && callInfo.getEndTime() == null) {
            callInfo.setEndTime(callDevice.getEndTime());
            if (!CollectionUtils.isEmpty(callInfo.getDeviceList())) {
                callInfo.getDeviceList().forEach(s -> {
                    hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), s);
                });
            }
        }

        if (deviceInfo.getDeviceType() == 2) {
            groupHandler.removeCall(callInfo.getGroupId(), callInfo.getCallId());
        }

        //判断挂机方向
        hangupDir(callInfo, deviceInfo, cause);
        cacheService.addCallInfo(callInfo);

        //最后一个设备挂机
        if (count == 1) {
            hangup(callInfo);
            return;
        }
    }

    /**
     * 全部挂机
     *
     * @param callInfo
     */
    private void hangup(CallInfo callInfo) {
        //计算用户等待总时长
        if (callInfo.getCallType() == CallType.INBOUND_CALL || callInfo.getCallType() == CallType.AUTO_CALL) {
            if (callInfo.getQueueStartTime() != null) {
                if (callInfo.getQueueEndTime() == null) {
                    callInfo.setQueueEndTime(callInfo.getEndTime());
                }
                callInfo.setWaitTime(callInfo.getQueueEndTime() - callInfo.getQueueStartTime());
            }
        }

        List<CallDevice> callDeviceList = new ArrayList<>();
        callInfo.getDeviceInfoMap().forEach((k, v) -> {
            CallDevice callDevice = new CallDevice();
            BeanUtils.copyProperties(v, callDevice);
            callDeviceList.add(callDevice);
        });
        callDeviceList.sort((x, y) -> Long.compare(x.getCallTime(), y.getCallTime()));
        for (CallDevice callDevice : callDeviceList) {
            if (callInfo.getRecord() == null && callDevice.getRecord() != null) {
                callInfo.setRecord(callDevice.getRecord());
                callInfo.setRecordTime(callDevice.getRecordTime());
            }
        }

        CallLog callLog = new CallLog();
        BeanUtils.copyProperties(callInfo, callLog);
        callLog.setCts(callInfo.getCallTime() / 1000);
        callLog.setUts(callInfo.getEndTime() / 1000);
        callLog.setEndTime(callInfo.getEndTime());
        callLog.setMonthTime(DateTimeUtil.getNowMonth());

        callLog.setCallType(callInfo.getCallType().name());
        callLog.setDirection(callInfo.getDirection().name());
        if (callInfo.getFollowData() != null) {
            callLog.setFollowData(JSON.toJSONString(callInfo.getFollowData()));
        }
        if (callInfo.getAnswerTime() == null) {
            //未接通
            callInfo.setTalkTime(0L);
        } else {
            callLog.setTalkTime(callInfo.getEndTime() - callInfo.getAnswerTime());
            callInfo.setTalkTime(callLog.getTalkTime());
            callInfo.setAnswerFlag(0);
        }
        if (callInfo.getHiddenCustomer() == 1) {
            //隐藏客户侧号码
            if (callInfo.getDirection() == Direction.INBOUND) {
                callLog.setCaller(hiddenNumber(callInfo.getCaller()));
            } else if (callInfo.getDirection() == Direction.OUTBOUND) {
                callLog.setCalled(hiddenNumber(callInfo.getCalled()));
            }
        }

        callCdrService.saveCallDetail(callInfo.getCallDetails());
        callCdrService.saveOrUpdateCallLog(callLog);

        //清空电话信息
        cacheService.removeCallInfo(callInfo.getCallId());
        if (StringUtils.isBlank(callInfo.getCdrNotifyUrl())) {
            return;
        }
        //话单推送
        CallLogPo callLogPo = new CallLogPo();
        BeanUtils.copyProperties(callLog, callLogPo);

        callLogPo.setOssServer(ossServer);
        callLogPo.setCallDeviceList(callDeviceList);
        callLogPo.setCaller(callInfo.getCaller());
        callLogPo.setCalled(callInfo.getCalled());
        ResponseEntity<String> responseEntity = null;
        String payload = JSON.toJSONString(callLogPo);
        PushFailLog pushFailLog = new PushFailLog();
        pushFailLog.setCallId(callLogPo.getCallId());
        pushFailLog.setCts(Instant.now().getEpochSecond());
        pushFailLog.setUts(Instant.now().getEpochSecond());
        pushFailLog.setCompanyId(callLogPo.getCompanyId());
        pushFailLog.setPushTimes(1);
        pushFailLog.setCdrNotifyUrl(callInfo.getCdrNotifyUrl());
        pushFailLog.setContent(payload);
        try {
            logger.info("push call data:{}", payload);
            responseEntity = restTemplate.postForEntity(callInfo.getCdrNotifyUrl(), payload, String.class);
            logger.info("push call:{} to {} success, response:{}", callInfo.getCallId(), callInfo.getCdrNotifyUrl(), responseEntity.getBody());
            pushFailLog.setStatus(2);
            pushFailLog.setPushResponse(responseEntity.getBody());
        } catch (Exception e) {
            pushFailLog.setStatus(1);
            logger.error("push call:{} to {} error, payload:{}", callInfo.getCallId(), callInfo.getCdrNotifyUrl(), payload);
        }
        callCdrService.savePushFailLog(pushFailLog);
    }


    /**
     * 判断挂机方向
     *
     * @param callInfo
     * @param deviceInfo
     */
    private void hangupDir(CallInfo callInfo, DeviceInfo deviceInfo, String cause) {
        if (callInfo.getHangupDir() != null || deviceInfo.getCdrType() >= 5) {
            return;
        }

        /**
         * 判断挂机方向
         * 1:主叫挂机，2:被叫挂机
         */
        if (callInfo.getDirection() == Direction.OUTBOUND) {
            if (deviceInfo.getDeviceType() == 1) {
                if (deviceInfo.getAnswerTime() == null) {
                    callInfo.setAnswerFlag(1);
                }
                //主叫挂机
                callInfo.setHangupDir(1);
            } else if (deviceInfo.getDeviceType() == 2) {
                if (deviceInfo.getAnswerTime() == null) {
                    callInfo.setAnswerFlag(2);
                }
                callInfo.setHangupDir(2);
            }

        } else if (callInfo.getDirection() == Direction.INBOUND) {
            if (deviceInfo.getDeviceType() == 2) {
                //主叫挂机
                callInfo.setHangupDir(1);
            } else {
                if (deviceInfo.getAnswerTime() == null) {
                    callInfo.setAnswerFlag(3);
                }
                callInfo.setHangupDir(2);
            }
        }
        logger.info("callId:{} direction:{} hangupDir:{}, cause:{}", callInfo.getCallId(), callInfo.getDirection(), callInfo.getHangupDir(), cause);
    }


    /**
     * 同步坐席状态
     *
     * @param deviceInfo
     * @param callInfo
     */
    private void agentState(DeviceInfo deviceInfo, CallInfo callInfo, String cause) {
        AgentInfo agentInfo = cacheService.getAgentInfo(deviceInfo.getAgentKey());
        if (agentInfo == null) {
            return;
        }
        WsCallAfterEntity afterEntity = new WsCallAfterEntity();
        BeanUtils.copyProperties(callInfo, afterEntity);
        if (callInfo.getHiddenCustomer() == 1) {
            //隐藏客户侧号码
            if (callInfo.getDirection() == Direction.INBOUND) {
                afterEntity.setCaller(hiddenNumber(callInfo.getCaller()));
            } else if (callInfo.getDirection() == Direction.OUTBOUND) {
                afterEntity.setCalled(hiddenNumber(callInfo.getCalled()));
            }
        }
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.AFTER);
        sendWsMessage(agentInfo, new WsResponseEntity<WsCallAfterEntity>(AgentState.AFTER.name(), agentInfo.getAgentKey(), afterEntity));
        agentInfo.setCallId(null);
        agentInfo.setDeviceId(null);
        cacheService.addAgentInfo(agentInfo);

        /**
         * 接口设置了预设状态
         */
        if (agentInfo.getAgentPreset() == null || agentInfo.getAgentPreset().getAgentState() == null) {
            return;
        }
        AgentPreset agentPreset = agentInfo.getAgentPreset();
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        if (agentPreset.getAgentState() == AgentState.READY) {
            agentInfo.setAgentState(AgentState.READY);
        } else if (agentPreset.getAgentState() == AgentState.NOT_READY) {
            agentInfo.setAgentState(AgentState.NOT_READY);
        }
        sendWsMessage(agentInfo, new WsResponseEntity<String>(agentPreset.getAgentState().name(), agentInfo.getAgentKey(), null));
    }

    /**
     * 根据当前挂机事件执行下一步
     *
     * @param callInfo
     * @param deviceInfo
     * @param cause
     */
    private void nextCmd(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, String cause) {
        /**
         * 呼入转到坐席，坐席拒接和坐席sip呼不通的时候，都需要再次转回来到技能组排队。
         */
        switch (nextCommand.getNextType()) {
            case NORNAL:
                break;
            case NEXT_CALL_BRIDGE:
                //桥接
                CauseEnums causeEnums = CauseEnums.valueOf(cause);
                if (causeEnums != null && causeEnums != CauseEnums.NORMAL_CLEARING) {
                    //重新进入技能组
                    String deviceId = nextCommand.getNextValue();
                    GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
                    groupHandler.hander(callInfo, groupInfo, deviceId);
                }
                //恢复原有通话
                playBreak(callInfo.getMediaHost(), nextCommand.getNextValue());
                bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), nextCommand.getDeviceId(), nextCommand.getNextValue());
                break;
            case NEXT_TRANSFER_SUCCESS:
                //完成转接
                logger.info("callId:{}, deviceId:{} NEXT_TRANSFER_SUCCESS", callInfo.getCallId(), deviceInfo.getDeviceId());
                bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), nextCommand.getDeviceId(), nextCommand.getNextValue());
                break;
            case NEXT_TRANSFER_BRIDGE:
                //转接后桥接
                logger.info("callId:{}, deviceId:{} NEXT_TRANSFER_BRIDGE", callInfo.getCallId(), deviceInfo.getDeviceId());
                break;

            case NEXT_CALL_OTHER:
                if (!CauseEnums.NORMAL_CLEARING.name().equals(cause)) {
                    //非正常挂机处理
                    hangupDir(callInfo, deviceInfo, cause);

                    //最后一个设备挂机
                    if (callInfo.getDeviceList().size() == 0) {
                        callInfo.setEndTime(Instant.now().toEpochMilli());
                        hangup(callInfo);
                        return;
                    }
                }
                break;
            default:
                logger.warn("not match nextCommand {}", nextCommand);
                break;
        }
        callInfo.getNextCommands().remove(nextCommand);
        cacheService.addCallInfo(callInfo);
        return;
    }
}
