package org.zhongweixian.cc.service.impl;

import org.cti.cc.entity.Group;
import org.cti.cc.mapper.GroupMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.strategy.AgentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.acd.assign.*;
import org.zhongweixian.cc.acd.lineup.CustomLineupStrategy;
import org.zhongweixian.cc.acd.lineup.DefaultLineupStrategy;
import org.zhongweixian.cc.acd.lineup.VipLineupStrategy;
import org.zhongweixian.cc.service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
@Component
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    BaseMapper<Group> baseMapper() {
        return groupMapper;
    }

    @Override
    public List<GroupInfo> getGroupByConpany(Long companyId) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        return groupMapper.selectGroupInfoList(params);
    }

    @Override
    public void initGroupStrategy(GroupInfo groupInfo) {
        /**
         * 电话多媒体排队策略
         */
        groupInfo.getGroupOverflows().forEach(overflowConfig -> {
            //1:先进先出,2:vip,3:自定义
            switch (overflowConfig.getBusyType()) {
                case 1:
                    overflowConfig.setLineupStrategy(new DefaultLineupStrategy());
                    break;
                case 2:
                    overflowConfig.setLineupStrategy(new VipLineupStrategy());
                    break;
                case 3:
                    overflowConfig.setLineupStrategy(new CustomLineupStrategy(overflowConfig.getCalculateExp()));
                    break;
                default:
            }
        });

        /**
         * 坐席在技能组中空闲策略
         */
        if (groupInfo.getGroupAgentStrategyPo() == null) {
            return;
        }
        Integer agentStrategyType = groupInfo.getGroupAgentStrategyPo().getStrategyType();
        Integer agentStrategyValue = groupInfo.getGroupAgentStrategyPo().getStrategyValue();
        AgentStrategy agentStrategy = null;
        if (agentStrategyType == 1) {
            // (1当前最长空闲时间、2空闲次数最多、3最少应答次数、4累计最少通话时长、5累计话后时长、6轮选、7随机)
            switch (agentStrategyValue) {
                case 1:
                    agentStrategy = new LongReadyAssign();
                    break;
                case 2:
                    agentStrategy = new TotalReadyTimesAssign();
                    break;
                case 3:
                    agentStrategy = new LeastAnswerAssign();
                    break;
                case 4:
                    agentStrategy = new LeastTalkAssign();
                    break;
                case 5:
                    agentStrategy = new TotalAfterTimeAssign();
                    break;
                case 6:
                    agentStrategy = new PollAssign();
                    break;
                case 7:
                    agentStrategy = new RandomAssign();
            }
        } else if (agentStrategyType == 2) {
            agentStrategy = new AgentCustomAssign(groupInfo.getGroupAgentStrategyPo().getCustomExpression());
        }
        if (agentStrategy == null) {
            logger.warn("companyId:{} groupName:{} init agentStrategy error", groupInfo.getCompanyId(), groupInfo.getName());
            agentStrategy = new LongReadyAssign();
        }
        groupInfo.getGroupAgentStrategyPo().setAgentStrategy(agentStrategy);
    }
}
