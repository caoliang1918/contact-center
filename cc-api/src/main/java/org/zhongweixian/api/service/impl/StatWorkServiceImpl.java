package org.zhongweixian.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.entity.StatHourAgent;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.mapper.StatHourAgentMapper;
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
