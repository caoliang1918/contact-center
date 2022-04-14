package org.zhongweixian.cc.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.*;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.mapper.*;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.*;
import org.cti.cc.util.DateTimeUtil;
import org.cti.cc.util.SnowflakeIdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.entity.MakeCallVo;
import org.zhongweixian.cc.exception.BusinessException;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.util.RandomUtil;
import org.zhongweixian.cc.websocket.WebSocketHandler;

import java.time.Instant;
import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
@Component
public class CallCdrServiceImpl extends BaseServiceImpl<CallLog> implements CallCdrService {

    @Autowired
    private CallLogMapper callLogMapper;

    @Autowired
    private CallDetailMapper callDetailMapper;

    @Autowired
    private CallDeviceMapper callDeviceMapper;

    @Autowired
    private CallDtmfMapper callDtmfMapper;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    @Autowired
    private PushFailLogMapper pushFailLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private FsListen fsListen;

    @Autowired
    protected SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private AgentService agentService;

    @Value("${call.cdr.pressure:0}")
    private Integer callCdrPressure;

    @Value("${oss.server}")
    private String ossServer;


    @Override
    BaseMapper<CallLog> baseMapper() {
        return callLogMapper;
    }

    @Override
    public void subTable(String month) {
        // cc_call_log
        callLogMapper.createNewTable(month);
        //cc_call_device
        callDeviceMapper.createNewTable(month);
        //cc_call_detail
        callDetailMapper.createNewTable(month);
        //cc_call_dtmf
        callDtmfMapper.createNewTable(month);
        //cc_agent_state_log
        agentStateLogMapper.createNewTable(month);
    }

    @Override
    public int saveCallDevice(CallDevice callDevice) {
        if (callCdrPressure == 0) {
            callDeviceMapper.insertSelective(callDevice);
            callDevice.setMonth(DateTimeUtil.getNowMonth());
            return callDeviceMapper.insertMonthSelective(callDevice);
        }
        rabbitTemplate.convertAndSend(Constant.CALL_LOG_EXCHANGE, Constant.DEVOCE_KEY, JSON.toJSONString(callDevice));
        /*kafkaTemplate.send(Constants.CALL_DEVICE, JSON.toJSONString(callDevice));*/
        return 1;
    }

    @Override
    public int saveCallDetail(List<CallDetail> callDetails) {
        if (CollectionUtils.isEmpty(callDetails)) {
            return 0;
        }
        String month = DateTimeUtil.getNowMonth();
        callDetails.forEach(callDetail -> {
            callDetail.setMonth(month);
            if (callCdrPressure == 1) {
                /*kafkaTemplate.send(Constants.CALL_DETAIL, JSON.toJSONString(callDetail));*/
                rabbitTemplate.convertAndSend(Constant.CALL_LOG_EXCHANGE, Constant.DETAIL_KEY, JSON.toJSONString(callDetail));
            } else {
                callDetailMapper.insertSelective(callDetail);
                callDetailMapper.insertMonthSelective(callDetail);
            }
        });
        return 1;
    }

    @Override
    public int saveOrUpdateCallLog(CallLog callLog) {
        if (callLog == null) {
            return 0;
        }
        logger.info("callId:{}, answerTime:{}, endTime:{}", callLog.getCallId(), callLog.getAnswerTime(), callLog.getEndTime());
        if (callCdrPressure == 1) {
            /*kafkaTemplate.send(Constants.CALL_LOG, JSON.toJSONString(callLog));*/
            rabbitTemplate.convertAndSend(Constant.CALL_LOG_EXCHANGE, Constant.CALLLOG_KEY, JSON.toJSONString(callLog));
            return 0;
        }
        if (callLog.getEndTime() != null) {
            callLogMapper.insertMonthSelective(callLog);
        }

        if (callLog.getAnswerTime() != null && callLog.getEndTime() == null) {
            //呼通
            return callLogMapper.insertSelective(callLog);
        }
        if (callLog.getAnswerTime() == null && callLog.getEndTime() != null) {
            //没有呼通
            return callLogMapper.insertSelective(callLog);
        }
        int result = callLogMapper.updateByCallId(callLog);
        if (result == 0) {
            result = callLogMapper.insertSelective(callLog);
        }
        return result;
    }

    @Override
    public CallLogPo getCall(Long companyId, Long callId) {
        Long time = DateTimeUtil.getCallTime(callId);
        CallLogPo callLogPo = callLogMapper.getCall(companyId, callId, DateTimeUtil.isToday(time) ? StringUtils.EMPTY : DateTimeUtil.getMonth(time));
        if (callLogPo != null) {
            callLogPo.setOssServer(ossServer);
        }
        return callLogPo;
    }


    @Override
    public int savePushFailLog(PushFailLog pushFailLog) {
        return pushFailLogMapper.insertSelective(pushFailLog);
    }

    @Override
    public int makeCall(MakeCallVo makeCallVo, AgentInfo agentInfo) {
        Long callId = snowflakeIdWorker.nextId();
        String deviceId = RandomStringUtils.randomNumeric(16);
        Long now = Instant.now().toEpochMilli();
        GroupInfo groupInfo = cacheService.getGroupInfo(agentInfo.getGroupId());

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(callId).withAgentKey(agentInfo.getAgentKey()).withLoginType(agentInfo.getLoginType()).withCompanyId(agentInfo.getCompanyId()).withGroupId(agentInfo.getGroupId()).withCtiHost(agentInfo.getHost())
//                .withCaller(caller)
                .withCalled(makeCallVo.getCalled())
//                .withCallerDisplay(callerDisplay)
//                .withCalledDisplay(calledDisplay)
                .withDirection(Direction.OUTBOUND).withCallType(makeCallVo.getCallType()).withCallTime(now).withGroupId(agentInfo.getGroupId()).withFollowData(makeCallVo.getFollowData()).withUuid1(makeCallVo.getUuid1()).withUuid2(makeCallVo.getUuid2()).build();

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withDeviceId(deviceId).withCaller("").withCalled("").withCallTime(now).withCallId(callId).withCdrType(2).withDeviceType(1).withAgentKey(agentInfo.getAgentKey()).build();


        callInfo.setHiddenCustomer(agentInfo.getHiddenCustomer());
        callInfo.getDeviceList().add(deviceId);
        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_CALL_OTHER, null));

        switch (makeCallVo.getCallType()) {
            case OUTBOUNT_CALL:
                outboundCall(agentInfo, makeCallVo, groupInfo, callInfo, deviceInfo);
                break;

            case BOTH_CALL:
                bothCall(agentInfo, makeCallVo, groupInfo, callInfo, deviceInfo);
                break;

            default:
                throw new BusinessException(ErrorCode.CALLTYPE_ERROR);
        }
        return 0;
    }

    @Override
    public void hangupCall(CallInfo callInfo, String deviceId) {
        fsListen.hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
    }

    @Override
    public void hold(CallInfo callInfo, String deviceId) {
        List<String> list = callInfo.getDeviceList();
        list.remove(deviceId);
        fsListen.bridgeBreak(callInfo.getMediaHost(), list.get(0));
        fsListen.holdPlay(callInfo.getMediaHost(), list.get(0), "/app/clpms/sounds/hold.wav");
    }

    @Override
    public void cancelHold(CallInfo callInfo, String deviceId) {
        fsListen.bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), callInfo.getDeviceList().get(0), callInfo.getDeviceList().get(1));
    }

    @Override
    public void readyMute(CallInfo callInfo, String deviceId) {
        fsListen.sendBgapiMessage(callInfo.getMediaHost(), "uuid_audio", deviceId + " start read mute 1");
    }

    @Override
    public void cancelMute(CallInfo callInfo, String deviceId) {
        fsListen.sendBgapiMessage(callInfo.getMediaHost(), "uuid_audio", deviceId + " stop");
    }

    /**
     * @param agentInfo
     * @param makeCallVo
     * @param groupInfo
     * @param callInfo
     * @param deviceInfo
     */
    private void outboundCall(AgentInfo agentInfo, MakeCallVo makeCallVo, GroupInfo groupInfo, CallInfo callInfo, DeviceInfo deviceInfo) {
        String caller = null, callerDisplay = null, calledDisplay = null;
        switch (agentInfo.getLoginType()) {
            case 1:
            case 2:
                caller = agentInfo.getSips().get(0);
                callerDisplay = agentInfo.getAgentId();
                break;
            case 3:
                //使用手机号码
                caller = agentInfo.getSipPhone();
                if (StringUtils.isNoneBlank(callerDisplay)) {
                    //先从坐席获取，坐席没有则从技能组获取
                    if (StringUtils.isNoneBlank(agentInfo.getDiaplay())) {
                        callerDisplay = agentInfo.getDiaplay();
                    } else {
                        callerDisplay = RandomUtil.getRandom(groupInfo.getCallerDisplays());
                    }
                }
                break;
            default:
                break;
        }
        calledDisplay = RandomUtil.getRandom(groupInfo.getCalledDisplays());
        if (StringUtils.isBlank(callerDisplay)) {
            throw new BusinessException(ErrorCode.CALLER_DISPLAY_ERROR);
        }
        if (StringUtils.isBlank(calledDisplay)) {
            throw new BusinessException(ErrorCode.CALLED_DISPLAY_ERROR);
        }

        //设置显号
        callInfo.setCallerDisplay(callerDisplay);
        callInfo.setCalledDisplay(calledDisplay);

        callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceInfo.getDeviceId(), callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("agent:{} make call:{} origin route error", agentInfo.getAgentKey(), callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentInfo.setAgentState(AgentState.AFTER);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            webSocketHandler.sendMessgae(agentInfo, "");
//            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", agentInfo.getAgentKey(), callInfo.getCallId(), callerDisplay, caller);
        fsListen.makeCall(routeGetway, callerDisplay, caller, callInfo.getCallId(), deviceInfo.getDeviceId());

        /**
         * 通知ws坐席请求外呼
         */
        webSocketHandler.sendMessgae(agentInfo, "");
        //sendMessgae(event, new WsResponseEntity<>(AgentState.OUT_CALL.name(), event.getAgentKey()));

        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.OUT_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceInfo.getDeviceId());

        agentService.saveAgentLog(agentInfo);
    }

    /**
     * 双向外呼
     *
     * @param agentInfo
     * @param makeCallVo
     * @param groupInfo
     * @param callInfo
     * @param deviceId
     */
    private void bothCall(AgentInfo agentInfo, MakeCallVo makeCallVo, GroupInfo groupInfo, CallInfo callInfo, DeviceInfo deviceId) {

    }


}
