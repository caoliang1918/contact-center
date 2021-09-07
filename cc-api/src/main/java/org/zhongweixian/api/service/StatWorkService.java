package org.zhongweixian.api.service;

import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.entity.StatHourAgentWork;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
public interface StatWorkService extends BaseService<StatHourAgentWork> {

    /**
     * 坐席坐席操作日志
     *
     * @param params
     * @return
     */
    List<AgentStateLog> getListByMap(Map<String, Object> params);

    /**
     * @param agentWorkList
     * @return
     */
    int saveStateHoutAgentWork(List<StatHourAgentWork> agentWorkList);


    int clearAgentStateWork(Long time);

    /**
     *
     * @param statTime
     */
    void deleteAgentHourStat(Long statTime);
}
