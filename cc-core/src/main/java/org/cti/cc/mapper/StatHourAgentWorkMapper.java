package org.cti.cc.mapper;

import org.cti.cc.entity.StatHourAgentWork;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;


public interface StatHourAgentWorkMapper extends BaseMapper<StatHourAgentWork> {


    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<StatHourAgentWork> list);

    /**
     * 重复统计时，需要先删除
     *
     * @param statTime
     */
    void deleteAgentHourStat(Long statTime);
}