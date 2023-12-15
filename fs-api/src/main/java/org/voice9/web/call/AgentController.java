package org.voice9.web.call;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CommonResponse;
import com.voice9.core.vo.AgentPreset;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voice9.web.base.BaseController;
import org.voice9.cc.exception.BusinessException;
import org.voice9.cc.websocket.event.WsLogoutEvent;
import org.voice9.cc.websocket.event.WsNotReadyEvent;
import org.voice9.cc.websocket.event.WsReadyEvent;

/**
 * Created by caoliang on 2020/12/17
 * <p>
 * rest api for agent
 */
@RestController
@RequestMapping("/cti/agent")
public class AgentController extends BaseController {


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
     * 5.1.2 坐席空闲
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("ready")
    public CommonResponse ready(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        checkAgentState(agentInfo);
        WsReadyEvent event = new WsReadyEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("READY");
        readyHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 5.1.3 坐席忙碌
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("notReady")
    public CommonResponse notReady(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        checkAgentState(agentInfo);
        WsNotReadyEvent event = new WsNotReadyEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("NOT_READY");
        notReadyHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 5.1.4 坐席退出
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("logout")
    public CommonResponse logout(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        WsLogoutEvent event = new WsLogoutEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("LOGOUT");
        logoutHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 5.1.5 坐席自定义状态
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("workNotReady")
    public CommonResponse workNotReady(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        WsLogoutEvent event = new WsLogoutEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("LOGOUT");
        logoutHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 5.1.6 话后状态预测
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("preset")
    public CommonResponse preset(@ModelAttribute("agentInfo") AgentInfo agentInfo, @Validated AgentPreset agentPreset) {
        agentInfo.setAgentPreset(agentPreset);
        return new CommonResponse<>();
    }


    private void checkAgentState(AgentInfo agentInfo) {
        if (agentInfo.getAgentState() == AgentState.LOGOUT) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_LOGIN);
        }
        if (agentInfo.getCallId() != null) {
            throw new BusinessException(ErrorCode.AGENT_CALLING);
        }
    }
}
