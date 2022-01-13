package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.mapper.base.BaseMapper;

public interface AgentStateLogMapper extends BaseMapper<AgentStateLog> {

    /**
     * 删除历史数据
     *
     * @param time
     * @return
     */
    int deleteAgentStateWork(Long time);


    /**
     * 迁移表
     *
     * @param month
     */
    void createNewTable(@Param("month") String month);

}