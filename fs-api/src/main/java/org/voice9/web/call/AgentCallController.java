package org.voice9.web.call;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voice9.cc.entity.MakeCallVo;
import org.voice9.cc.exception.BusinessException;
import org.voice9.web.base.BaseController;

/**
 * Created by caoliang on 2020/12/16
 * <p>
 * rest cti for agent
 */
@RestController
@RequestMapping("/cti/call")
public class AgentCallController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AgentCallController.class);


    /**
     * RestTemplate restTemplate = new RestTemplate();
     * <p>
     * restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("user", "pwd"));
     *
     * @return
     */
    @ModelAttribute("agentInfo")
    public AgentInfo agentInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (AgentInfo) authentication.getPrincipal();
    }

    /**
     * 5.2.1 发起呼叫
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("makeCall")
    public CommonResponse makeCall(@ModelAttribute("agentInfo") AgentInfo agentInfo, @RequestBody @Validated MakeCallVo makeCallVo) {
        if (agentInfo.getAgentState() != null) {
            if (agentInfo.getCallId() != null || agentInfo.getAgentState() == AgentState.TALKING) {
                return new CommonResponse(ErrorCode.AGENT_CALLING);
            }
        }
        if (makeCallVo.getCallType() == null) {
            return new CommonResponse(ErrorCode.CALLTYPE_ERROR);
        }
        callCdrService.makeCall(makeCallVo, agentInfo);
        logger.info("agent:{} makecall:{}", agentInfo.getAgentKey(), makeCallVo.toString());
        return new CommonResponse();
    }

    /**
     * 5.2.2 发起呼叫
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("hangup")
    public CommonResponse hangup(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        Long callId = agentInfo.getCallId();
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callId == null || callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        callCdrService.hangupCall(callInfo, agentInfo.getDeviceId());
        logger.info("agent:{} hangupCall callId:{}  deviceId:{}", agentInfo.getAgentKey(), agentInfo.getCallId(), agentInfo.getDeviceId());
        return new CommonResponse();
    }


    /**
     * 5.2.3 保持
     * 坐席听不到用户声音，用户听到的是保持音
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("hold")
    public CommonResponse hold(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        Long callId = agentInfo.getCallId();
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callId == null || callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        callCdrService.hold(callInfo, agentInfo.getDeviceId());
        return new CommonResponse();
    }

    /**
     * 5.2.4 取消保持
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("cancelHold")
    public CommonResponse cancelHold(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        Long callId = agentInfo.getCallId();
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callId == null || callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        callCdrService.cancelHold(callInfo, agentInfo.getDeviceId());
        return new CommonResponse();
    }


    /**
     * 5.2.10 静音
     * 坐席可以听到用户声音，用户听不到坐席声音
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("readyMute")
    public CommonResponse readyMute(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        Long callId = agentInfo.getCallId();
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callId == null || callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        callCdrService.readyMute(callInfo, agentInfo.getDeviceId());
        return new CommonResponse();
    }

    /**
     * 5.2.11 取消静音
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("cancelMute")
    public CommonResponse cancelMute(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        Long callId = agentInfo.getCallId();
        CallInfo callInfo = cacheService.getCallInfo(callId);
        if (callId == null || callInfo == null) {
            throw new BusinessException(ErrorCode.CALL_NOT_EXIST);
        }
        callCdrService.cancelMute(callInfo, agentInfo.getDeviceId());
        return new CommonResponse();
    }
}
