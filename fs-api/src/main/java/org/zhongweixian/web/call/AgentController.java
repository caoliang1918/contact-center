package org.zhongweixian.web.call;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.vo.AgentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @Value("${server.address}:${server.port}")
    private String host;


    /**
     * 1.1 坐席登录
     *
     * @param agentVo
     * @return
     */
    @PostMapping("login")
    public CommonResponse<AgentInfo> login(@RequestBody @Validated AgentVo agentVo) {
        AgentInfo agentInfo = agentService.getAgentInfo(agentVo.getAgentKey());
        if (!BcryptUtil.checkPwd(agentVo.getPasswd(), agentInfo.getPasswd())) {
            logger.error("agent:{}  password {} is error", agentVo.getAgentKey(), agentVo.getPasswd());
            return new CommonResponse<>(ErrorCode.ACCOUNT_ERROR);
        }
        agentInfo.setBeforeState(AgentState.LOGOUT);
        agentInfo.setBeforeTime(agentInfo.getLogoutTime());
        agentInfo.setStateTime(agentInfo.getLoginTime());
        agentInfo.setLoginTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.LOGIN);
        agentInfo.setHost(host);
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(agentVo.getLoginType());
        agentInfo.setWorkType(agentVo.getWorkType());
        agentInfo.setRemoteAddress(agentVo.getCallBackUrl());
        cacheService.addAgentInfo(agentInfo);
        return new CommonResponse<AgentInfo>(agentInfo);
    }

    /**
     * 1.2 查看坐席信息
     *
     * @param agentInfo
     * @return
     */
    @GetMapping("agentInfo")
    public CommonResponse<AgentVo> agentInfo(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        AgentVo agentVo = new AgentVo();
        BeanUtils.copyProperties(agentInfo, agentVo);
        return new CommonResponse<AgentVo>(agentVo);
    }

    /**
     * 1.3坐席空闲
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("ready")
    public CommonResponse ready(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        WsReadyEvent event = new WsReadyEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("READY");
        readyHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 1.4 坐席忙碌
     *
     * @param agentInfo
     * @return
     */
    @PostMapping("notReady")
    public CommonResponse notReady(@ModelAttribute("agentInfo") AgentInfo agentInfo) {
        WsNotReadyEvent event = new WsNotReadyEvent();
        event.setAgentKey(agentInfo.getAgentKey());
        event.setCmd("NOT_READY");
        notReadyHandler.handleEvent(event);
        return new CommonResponse<>();
    }

    /**
     * 1.5 坐席退出
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
     * 查看技能组
     *
     * @param groupId
     * @return
     */
    @GetMapping("group/{groupId}")
    public CommonResponse groupReadyAgent(@PathVariable Long groupId, @ModelAttribute("agentInfo") AgentInfo agentInfo) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !agentInfo.getGroupIds().contains(groupInfo.getId())) {
            return new CommonResponse();
        }
        return new CommonResponse(groupInfo);
    }

    /**
     * 技能组空闲坐席
     *
     * @param groupId
     * @param agentInfo
     * @return
     */
    @GetMapping("group/{groupId}/ready")
    public CommonResponse readyAgent(@PathVariable Long groupId, @ModelAttribute("agentInfo") AgentInfo agentInfo) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null || !agentInfo.getGroupIds().contains(groupInfo.getId())) {
            return new CommonResponse();
        }
        return new CommonResponse(groupInfo.getOnlineAgents());
    }


}
