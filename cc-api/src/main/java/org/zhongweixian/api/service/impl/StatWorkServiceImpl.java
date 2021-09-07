package org.zhongweixian.api.service.impl;

import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.entity.StatHourAgentWork;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.mapper.StatHourAgentWorkMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.api.service.StatWorkService;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
@Service
public class StatWorkServiceImpl extends BaseServiceImpl<StatHourAgentWork> implements StatWorkService {

    @Autowired
    private StatHourAgentWorkMapper stateHourAgentWorkMapper;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    @Override
    BaseMapper<StatHourAgentWork> baseMapper() {
        return stateHourAgentWorkMapper;
    }


    @Override
    public List<AgentStateLog> getListByMap(Map<String, Object> params) {
        return agentStateLogMapper.selectListByMap(params);
    }

    @Override
    public int saveStateHoutAgentWork(List<StatHourAgentWork> agentWorkList) {
        //每次插入500条
        if (CollectionUtils.isEmpty(agentWorkList)) {
            return 0;
        }
        int size = agentWorkList.size();
        int modulo = size % range;
        for (int i = 0; i < size / 500; i++) {
            stateHourAgentWorkMapper.batchInsert(agentWorkList.subList(i * range, (i + 1) * range));
        }
        if (modulo > 0) {
            stateHourAgentWorkMapper.batchInsert(agentWorkList.subList(size / range * range, size));
        }
        return 0;
    }

    @Override
    public int clearAgentStateWork(Long time) {
        return agentStateLogMapper.clearAgentStateWork(time);
    }

    @Override
    public void deleteAgentHourStat(Long statTime) {
        stateHourAgentWorkMapper.deleteAgentHourStat(statTime);
    }

}
