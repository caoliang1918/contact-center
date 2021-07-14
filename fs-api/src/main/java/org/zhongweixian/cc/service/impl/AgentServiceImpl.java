package org.zhongweixian.cc.service.impl;

import com.alibaba.fastjson.JSON;
import org.cti.cc.entity.Agent;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.mapper.AgentGroupMapper;
import org.cti.cc.mapper.AgentMapper;
import org.cti.cc.mapper.AgentSipMapper;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.configration.mq.RabbitConfig;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.websocket.response.AgentStateResppnse;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
@Component
public class AgentServiceImpl extends BaseServiceImpl<Agent> implements AgentService {

    @Value("${cc.pressure:0}")
    private Integer pressure;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private AgentGroupMapper agentGroupMapper;

    @Autowired
    private CacheService cacheService;

    @Autowired
    protected AgentStateLogMapper agentStateLogMapper;

    @Autowired
    private AgentSipMapper agentSipMapper;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Override
    BaseMapper<Agent> baseMapper() {
        return agentMapper;
    }

    @Override
    public AgentInfo getAgentInfo(String agentKey) {
        AgentInfo agentInfo = cacheService.getAgentInfo(agentKey);
        Agent agent = agentMapper.selectAgent(agentKey);
        if (agent == null) {
            return null;
        }
        if (agentInfo == null) {
            agentInfo = new AgentInfo();
        }
        BeanUtils.copyProperties(agent, agentInfo);
        agentInfo.setSips(this.getAgentSips(agent.getId()));
        return agentInfo;
    }


    @Override
    public List<Long> getAgentGroups(Long agentId) {
        return agentGroupMapper.selectByAgent(agentId);
    }

    @Override
    public List<String> getAgentSips(Long agentId) {
        return agentSipMapper.selectByAgent(agentId);
    }

    @Override
    public void sendAgentStateMessage(AgentInfo agentInfo) {
        try {
            if (agentInfo.getAgentState() == AgentState.READY) {
                groupHandler.agentFree(agentInfo);
            }
            if (agentInfo.getBeforeState() == AgentState.READY) {
                groupHandler.agentNotReady(agentInfo);
            }
            AgentStateResppnse response = new AgentStateResppnse();
            response.setId(agentInfo.getId());
            response.setAgentKey(agentInfo.getAgentKey());
            response.setCompanyId(agentInfo.getCompanyId());
            response.setSipPhone(agentInfo.getSipPhone());
            response.setGroupId(agentInfo.getGroupId());
            response.setHost(agentInfo.getHost());
            response.setGroupIds(agentInfo.getGroupIds());
            response.setSips(agentInfo.getSips());
            response.setCallId(agentInfo.getCallId());
            response.setLoginTime(agentInfo.getLoginTime());
            response.setAgentState(agentInfo.getAgentState());
            response.setLoginType(agentInfo.getLoginType());
            response.setWorkType(agentInfo.getWorkType());
            response.setStateTime(agentInfo.getStateTime());
            response.setBeforeState(agentInfo.getBeforeState());
            response.setBeforeTime(agentInfo.getBeforeTime());
            response.setMaxFreeTime(agentInfo.getMaxFreeTime());
            response.setTotalFreeTime(agentInfo.getTotalFreeTime());
            response.setMaxTalkTime(agentInfo.getMaxTalkTime());
            response.setTotalTalkTime(agentInfo.getTotalTalkTime());
            response.setTotalRingTime(agentInfo.getTotalRingTime());
            response.setTotalAnswerTime(agentInfo.getTotalAnswerTime());

            logger.info("send mq agent:{} state:{}", agentInfo.getAgentKey(), agentInfo.getAgentState());
            rabbitTemplate.convertAndSend(RabbitConfig.AGENT_STATE_EXCHANGE, RabbitConfig.DEFAULT_KEY, JSON.toJSONString(response));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        try {
            saveAgentLog(agentInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 更新到数据库
     *
     * @param agentInfo
     * @return
     */
    private Integer saveAgentLog(AgentInfo agentInfo) {
        AgentStateLog agentStateLog = new AgentStateLog();
        agentStateLog.setAgentId(agentInfo.getId());
        agentStateLog.setCallId(agentInfo.getCallId());
        agentStateLog.setAgentKey(agentInfo.getAgentKey());
        agentStateLog.setBeforeTime(agentInfo.getBeforeTime());
        agentStateLog.setBeforeState(agentInfo.getBeforeState().name());
        agentStateLog.setState(agentInfo.getAgentState().name());
        agentStateLog.setStateTime(agentInfo.getStateTime());
        agentStateLog.setHost(agentInfo.getHost());
        agentStateLog.setCompanyId(agentInfo.getCompanyId());
        agentStateLog.setGroupId(agentInfo.getGroupId());
        agentStateLog.setCts(agentInfo.getStateTime() / 1000);
        agentStateLog.setUts(agentInfo.getStateTime() / 1000);
        agentStateLog.setLoginType(agentInfo.getLoginType());
        agentStateLog.setWorkType(agentInfo.getWorkType());
        agentStateLog.setRemoteAddress(agentInfo.getRemoteAddress());
        //持续时长
        agentStateLog.setDuration(agentInfo.getBeforeTime() == 0 ? 0 : (int) (agentInfo.getStateTime() - agentInfo.getBeforeTime()));
        if (pressure == 1) {
            return 1;
        }
        return agentStateLogMapper.insertSelective(agentStateLog);
    }
}
