package org.zhongweixian.web.call;

import org.cti.cc.entity.Station;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.vo.AgentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.exception.BusinessException;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.util.BcryptUtil;
import org.zhongweixian.cc.websocket.event.WsLogoutEvent;
import org.zhongweixian.cc.websocket.event.WsNotReadyEvent;
import org.zhongweixian.cc.websocket.event.WsReadyEvent;
import org.zhongweixian.cc.websocket.handler.WsLogoutHandler;
import org.zhongweixian.cc.websocket.handler.WsNotReadyHandler;
import org.zhongweixian.cc.websocket.handler.WsReadyHandler;
import org.zhongweixian.web.base.BaseController;

import java.time.Instant;

/**
 * Created by caoliang on 2020/12/17
 * <p>
 * rest api for agent
 */
@RestController
@RequestMapping("/v1/cti/agent")
public class AgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private WsReadyHandler readyHandler;

    @Autowired
    private WsNotReadyHandler notReadyHandler;

    @Autowired
    private WsLogoutHandler logoutHandler;

    @Autowired
    private Station station;


    /**
     * 3.1.1 坐席登录
     *
     * @param agentVo
     * @return
     */
    @PostMapping("login")
    public CommonResponse<AgentInfo> login(@RequestBody @Validated AgentVo agentVo) {
        AgentInfo agentInfo = cacheService.getAgentInfo(agentVo.getAgentKey());
        if (agentInfo == null) {
            agentInfo = agentService.getAgentInfo(agentVo.getAgentKey());
        }
        if (agentInfo == null || agentInfo.getStatus() != 1) {
            logger.warn("agentKey:{} is not exist", agentVo.getAgentKey());
            throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
        }
        if (!BcryptUtil.checkPwd(agentVo.getPasswd(), agentInfo.getPasswd())) {
            logger.error("agent:{}  password {} is error", agentVo.getAgentKey(), agentVo.getPasswd());
            return new CommonResponse<>(ErrorCode.ACCOUNT_ERROR);
        }
        agentInfo.setBeforeState(AgentState.LOGOUT);
        agentInfo.setBeforeTime(agentInfo.getLogoutTime());
        agentInfo.setStateTime(agentInfo.getLoginTime());
        agentInfo.setLoginTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.LOGIN);
        agentInfo.setHost(station.getHost());
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(agentVo.getLoginType());
        agentInfo.setWorkType(agentVo.getWorkType());
        agentInfo.setRemoteAddress(agentVo.getCallBackUrl());
        cacheService.addAgentInfo(agentInfo);
        AgentInfo agentInfo1 = new AgentInfo();
        BeanUtils.copyProperties(agentInfo, agentInfo1);
        agentInfo1.setPasswd(null);
        logger.info("agent:{} login success", agentInfo.getAgentKey());
        return new CommonResponse<AgentInfo>(agentInfo1);
    }


    /**
     * 3.1.2 坐席空闲
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
     * 3.1.3 坐席忙碌
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
     * 3.1.4 坐席退出
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
     * 3.1.5 坐席自定义状态
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


    private void checkAgentState(AgentInfo agentInfo) {
        if (agentInfo.getAgentState() == AgentState.LOGOUT) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_LOGIN);
        }
        if (agentInfo.getCallId() != null) {
            throw new BusinessException(ErrorCode.AGENT_CALLING);
        }
    }
}
