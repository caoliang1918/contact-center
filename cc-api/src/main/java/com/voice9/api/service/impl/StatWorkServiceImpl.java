package com.voice9.api.service.impl;

import com.google.common.collect.Lists;
import com.voice9.api.service.StatWorkService;
import com.voice9.core.entity.AgentStateLog;
import com.voice9.core.entity.StatHourAgent;
import com.voice9.core.mapper.AgentStateLogMapper;
import com.voice9.core.mapper.StatHourAgentMapper;
import com.voice9.core.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
@Service
public class StatWorkServiceImpl extends BaseServiceImpl<StatHourAgent> implements StatWorkService {

    @Autowired
    private StatHourAgentMapper statHourAgentMapper;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    @Override
    BaseMapper<StatHourAgent> baseMapper() {
        return statHourAgentMapper;
    }


    @Override
    public List<AgentStateLog> getListByMap(Map<String, Object> params) {
        return agentStateLogMapper.selectListByMap(params);
    }

    @Override
    public List<StatHourAgent> statHour(Map<String, Object> params) {
        return agentStateLogMapper.statHour(params);
    }

    @Override
    public int saveStateHoutAgent(List<StatHourAgent> agentWorkList) {
        if (CollectionUtils.isEmpty(agentWorkList)) {
            return 0;
        }
        List<List<StatHourAgent>> newList = Lists.partition(agentWorkList, batchInsertCnt);
        Integer cnt = 0;
        for (List<StatHourAgent> list : newList) {
            cnt += statHourAgentMapper.batchInsert(list);
        }
        return cnt;
    }

    @Override
    public void deleteAgentHourStat(Long statTime) {
        statHourAgentMapper.deleteAgentHourStat(statTime);
    }

}
