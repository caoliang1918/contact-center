package org.zhongweixian.web.call;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.Agent;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.*;
import org.cti.cc.vo.AgentPreset;
import org.cti.cc.vo.AgentVo;
import org.cti.cc.util.AuthUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.exception.BusinessException;
import org.zhongweixian.cc.util.BcryptUtil;
import org.zhongweixian.cc.websocket.event.WsLogoutEvent;
import org.zhongweixian.cc.websocket.event.WsNotReadyEvent;
import org.zhongweixian.cc.websocket.event.WsReadyEvent;
import org.zhongweixian.web.base.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

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
     * 3.1.1 坐席登录
     *
     * @param agentVo
     * @return
     */
    @PostMapping("login")
    public CommonResponse<AgentInfo> login(HttpServletRequest request, @RequestBody @Validated AgentVo agentVo) {
        AgentInfo agentInfo = agentService.getAgentInfo(agentVo.getAgentKey());
        if (agentInfo == null || agentInfo.getStatus() != 1) {
            logger.warn("agentKey:{} is not exist", agentVo.getAgentKey());
            throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
        }
        if (agentInfo.getCallId() != null && !agentVo.isForceLogin()) {
            logger.warn("agentKey:{} is talking , callId:{}", agentInfo.getAgentKey(), agentInfo.getCallId());
            return new CommonResponse<>(ErrorCode.AGENT_CALLING);
        }
        //坐席所在的主技能组
        GroupInfo groupInfo = cacheService.getGroupInfo(agentInfo.getGroupId());
        if (groupInfo == null || groupInfo.getStatus() == 0 || CollectionUtils.isEmpty(agentInfo.getGroupIds())) {
            logger.warn("agentKey:{} group is null", agentInfo.getAgentKey());
            return new CommonResponse<>(ErrorCode.AGENT_GROUP_NULL);
        }
        if (!BcryptUtil.checkPwd(agentVo.getPasswd(), agentInfo.getPasswd())) {
            logger.error("agent:{}  password {} is error", agentVo.getAgentKey(), agentVo.getPasswd());
            return new CommonResponse<>(ErrorCode.ACCOUNT_ERROR);
        }
        //删除旧的token
        if (!StringUtils.isBlank(agentInfo.getToken())) {
            cacheService.deleteKey(Constant.AGENT_TOKEN + agentInfo.getToken());
        }

        CompanyInfo companyInfo = cacheService.getCompany(agentInfo.getCompanyId());
        String token = AuthUtil.createToken(agentInfo.getAgentKey(), companyInfo.getId(), companyInfo.getSecretKey());
        agentInfo.setBeforeState(AgentState.LOGOUT);
        agentInfo.setBeforeTime(agentInfo.getLogoutTime());
        agentInfo.setStateTime(agentInfo.getLoginTime());
        agentInfo.setLoginTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.LOGIN);
        agentInfo.setHost(request.getLocalAddr());
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(agentVo.getLoginType());
        agentInfo.setWorkType(agentVo.getWorkType());
        agentInfo.setWebHook(agentVo.getWebHook());
        agentInfo.setToken(token);
        cacheService.refleshAgentToken(agentInfo.getAgentKey(), token);
        cacheService.addAgentInfo(agentInfo);
        AgentInfo agentInfo1 = new AgentInfo();
        BeanUtils.copyProperties(agentInfo, agentInfo1);
        agentInfo1.setPasswd(null);
        logger.info("agent:{} login success", agentInfo.getAgentKey());

        /**
         * 广播坐席状态
         */
        agentService.syncAgentStateMessage(agentInfo);

        /**
         * 坐席在线
         */
        Agent agent = new Agent();
        agent.setId(agentInfo.getId());
        agent.setCompanyId(agentInfo.getCompanyId());
        agent.setState(1);
        agent.setHost(request.getRemoteAddr());
        agentService.editById(agent);
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

    /**
     * 3.1.6 话后状态预测
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
