package com.voice9.api.service;

import com.voice9.core.entity.AgentStateLog;
import com.voice9.core.entity.StatHourAgent;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
public interface StatWorkService extends BaseService<StatHourAgent> {

    /**
     * 坐席坐席操作日志
     *
     * @param params
     * @return
     */
    List<AgentStateLog> getListByMap(Map<String, Object> params);

    /**
     * 状态统计
     *
     * @param params
     * @return
     */
    List<StatHourAgent> statHour(Map<String, Object> params);


    /**
     * @param agentWorkList
     * @return
     */
    int saveStateHoutAgent(List<StatHourAgent> agentWorkList);


    /**
     * @param statTime
     */
    void deleteAgentHourStat(Long statTime);
}
