package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.mapper.base.BaseMapper;

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

}