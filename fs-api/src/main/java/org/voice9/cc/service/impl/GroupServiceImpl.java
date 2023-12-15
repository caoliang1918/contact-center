package org.voice9.cc.service.impl;

import com.voice9.core.entity.Group;
import com.voice9.core.mapper.GroupMapper;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.strategy.AgentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voice9.cc.acd.assign.*;
import org.voice9.cc.acd.lineup.CustomLineupStrategy;
import org.voice9.cc.acd.lineup.DefaultLineupStrategy;
import org.voice9.cc.acd.lineup.VipLineupStrategy;
import org.voice9.cc.service.GroupService;

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
                    break;
                default:
                    break;
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
