package org.voice9.web;

import com.voice9.core.po.*;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.Agent;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.util.AuthUtil;
import com.voice9.core.vo.AgentLoginVo;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.util.BcryptUtil;
import org.voice9.cc.exception.BusinessException;
import org.voice9.cc.service.AgentService;
import org.voice9.cc.service.CallCdrService;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * Created by caoliang on 2020/8/24
 */
@RestController
@RequestMapping("index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private CacheService cacheService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private CallCdrService callCdrService;

    @Autowired
    private StringEncryptor encrypt;


    /**
     * @param callId
     * @return
     */
    @GetMapping("getcall")
    public CommonResponse<CallLogPo> getCall(@RequestParam Long callId) {
        return new CommonResponse<>(callCdrService.getCall(null, callId));
    }


    @GetMapping("/encrypt")
    public CommonResponse encrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.encrypt(text));
    }


    @GetMapping("/decrypt")
    public CommonResponse decrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.decrypt(text));
    }


    @GetMapping("health")
    public Health health() {
        return Health.up().build();
    }

    @GetMapping("clearAgent/{agentKey}")
    public CommonResponse clearAgent(@PathVariable String agentKey) {
        AgentInfo agentInfo = cacheService.getAgentInfo(agentKey);
        if (agentInfo == null) {
            logger.warn("agentInfo:{} is null", agentKey);
            return new CommonResponse();
        }
        logger.info("agentKey:{}, callId:{}, staet:{}", agentKey, agentInfo.getCallId(), agentInfo.getAgentState());
        if (agentInfo.getCallId() != null) {
            cacheService.removeCallInfo(agentInfo.getCallId());
            agentInfo.setCallId(null);
            agentInfo.setDeviceId(null);
            agentInfo.setAgentState(null);
            cacheService.addAgentInfo(agentInfo);
        }
        return new CommonResponse();
    }


    /**
     * 5.1.1 坐席登录
     *
     * @param agentLoginVo
     * @return
     */
    @PostMapping("login")
    public CommonResponse<AgentInfo> login(HttpServletRequest request, @RequestBody @Validated AgentLoginVo agentLoginVo) {
        AgentInfo agentInfo = agentService.getAgentInfo(agentLoginVo.getAgentKey());
        if (agentInfo == null || agentInfo.getStatus() != 1) {
            logger.warn("agentKey:{} is not exist", agentLoginVo.getAgentKey());
            throw new BusinessException(ErrorCode.ACCOUNT_ERROR);
        }
        if (agentInfo.getCallId() != null && !agentLoginVo.isForceLogin()) {
            logger.warn("agentKey:{} is talking , callId:{}", agentInfo.getAgentKey(), agentInfo.getCallId());
            return new CommonResponse<>(ErrorCode.AGENT_CALLING);
        }
        //坐席所在的主技能组
        GroupInfo groupInfo = cacheService.getGroupInfo(agentInfo.getGroupId());
        if (groupInfo == null || groupInfo.getStatus() == 0 || CollectionUtils.isEmpty(agentInfo.getGroupIds())) {
            logger.warn("agentKey:{} group is null", agentInfo.getAgentKey());
            return new CommonResponse<>(ErrorCode.AGENT_GROUP_NULL);
        }
        if (!BcryptUtil.checkPwd(agentLoginVo.getPasswd(), agentInfo.getPasswd())) {
            logger.error("agent:{}  password {} is error", agentLoginVo.getAgentKey(), agentLoginVo.getPasswd());
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
        agentInfo.setLoginTime(Instant.now().getEpochSecond());
        agentInfo.setAgentState(AgentState.LOGIN);
        agentInfo.setHost(request.getLocalAddr());
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(agentLoginVo.getLoginType());
        agentInfo.setWorkType(agentLoginVo.getWorkType());
        agentInfo.setWebHook(agentLoginVo.getWebHook());
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


}

