package org.voice9.cc.command;

import com.voice9.core.entity.CallDetail;
import com.voice9.core.entity.Playback;
import com.voice9.core.enums.NextType;
import com.voice9.core.po.*;
import org.springframework.stereotype.Component;
import org.voice9.cc.command.base.BaseHandler;

import java.time.Instant;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 电话进vdn流程
 */
@Component
public class VdnHandler extends BaseHandler {

    public void hanlder(CallInfo callInfo, DeviceInfo deviceInfo, Long vdnId) {
        String deviceId = deviceInfo.getDeviceId();
        VdnCodePo vdnCodePo = cacheService.getCompany(callInfo.getCompanyId()).getVdnCodeMap().get(vdnId);
        if (vdnCodePo == null || vdnCodePo.getStatus() == 0) {
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
            return;
        }
        //查询有效日程
        VdnSchedulePo vdnSchedulePo = vdnCodePo.getEffectiveSchedule();
        if (vdnSchedulePo == null) {
            logger.warn("callId:{} not match schedule, vdn:{}", callInfo.getCallId(), vdnCodePo.getName());
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
            return;
        }
        //电话经过vdn
        CallDetail callDetail = new CallDetail();
        callDetail.setCallId(callInfo.getCallId());
        callDetail.setStartTime(Instant.now().toEpochMilli());
        callDetail.setDetailIndex(callInfo.getCallDetails().size() + 1);
        callDetail.setTransferType(1);
        callDetail.setTransferId(vdnSchedulePo.getVdnId());
        callInfo.getCallDetails().add(callDetail);


        logger.info("vdnSchedule:{} routeType:{} routeValue:{}", vdnSchedulePo.getName(), vdnSchedulePo.getRouteType(), vdnSchedulePo.getRouteValue());
        /**
         * 1:技能组,2:放音,3:ivr,4:坐席,5:外呼
         */
        switch (vdnSchedulePo.getRouteType()) {
            case 1:
                GroupInfo groupInfo = cacheService.getGroupInfo(Long.parseLong(vdnSchedulePo.getRouteValue()));
                if (groupInfo == null) {
                    logger.warn("callId:{} join group is null", callInfo.getCallId());
                    hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                    return;
                }
                logger.debug("callId:{} join groupId:{}, groupName:{}", callInfo.getCallId(), vdnSchedulePo.getRouteValue(), groupInfo.getName());
                callInfo.setGroupId(groupInfo.getId());
                groupHandler.hander(callInfo, groupInfo, deviceId);
                break;

            case 2:
                /**
                 * 普通放音或者按键导航音 1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机
                 */
                Playback playback = cacheService.getPlayback(Long.parseLong(vdnSchedulePo.getRouteValue()));
                if (playback == null) {
                    logger.warn("playback is null, callId:{} , device:{}", callInfo.getCallId(), deviceId);
                    hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                    return;
                }
                //放音类型(1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机)
                logger.info("playback tyep:{}, value:{}", vdnSchedulePo.getPlayType(), vdnSchedulePo.getPlayValue());
                if (vdnSchedulePo.getPlayType() != 1) {
                    NextCommand nextCommand = null;
                    switch (vdnSchedulePo.getPlayType()) {
                        case 2:
                            nextCommand = new NextCommand(deviceId, NextType.NEXT_GROUP, vdnSchedulePo.getPlayValue().toString());
                            break;
                        case 3:
                            nextCommand = new NextCommand(deviceId, NextType.NEXT_IVR, vdnSchedulePo.getPlayValue().toString());
                            break;
                        case 4:
                            nextCommand = new NextCommand(deviceId, NextType.NEXT_VDN, vdnSchedulePo.getPlayValue().toString());
                            break;
                        case 5:
                            nextCommand = new NextCommand(deviceId, NextType.NEXT_HANGUP, vdnSchedulePo.getPlayValue().toString());
                            break;
                        default:
                            break;
                    }
                    playback(callInfo.getMediaHost(), deviceId, playback.getPlayback());
                    doNextCommand(callInfo, deviceInfo, nextCommand);
                    return;
                }

                /**
                 * 播放按键导航音
                 */
                receiveDtmf(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                break;

            case 3:
                /**
                 * 转IVR
                 */
                transferIvrHandler.handler(callInfo, deviceInfo, Long.parseLong(vdnSchedulePo.getRouteValue()));
                return;

            case 4:
                /**
                 * 转坐席
                 */
                AgentInfo agentInfo = cacheService.getAgentInfo(vdnSchedulePo.getRouteValue());
                if (agentInfo == null || agentInfo.getAgentState() != AgentState.READY) {
                    hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                    return;
                }
                transferAgentHandler.hanlder(callInfo, agentInfo, deviceId);
                return;

            case 5:
                /**
                 * 转外呼
                 */
                transferCallHandler.hanlder(callInfo, vdnSchedulePo.getRouteValue(), deviceId);
                return;
            default:
                logger.warn("vdnCode not match callId:{} , case:{}", callInfo.getCallId(), vdnSchedulePo.getRouteType());
                break;
        }
    }
}
