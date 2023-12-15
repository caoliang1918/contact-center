package com.voice9.core.mapper;

import com.voice9.core.entity.AgentStateLog;
import com.voice9.core.entity.StatHourAgent;
import com.voice9.core.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AgentStateLogMapper extends BaseMapper<AgentStateLog> {


    /**
     * 迁移表
     *
     * @param month
     */
    void createNewTable(@Param("month") String month);


    /**
     * 删除数据
     *
     * @param end
     */
    int clearTable(@Param("end") Long end);

    /**
     * 坐席状态统计
     *
     * @param params
     * @return
     */
    List<StatHourAgent> statHour(Map<String, Object> params);
}