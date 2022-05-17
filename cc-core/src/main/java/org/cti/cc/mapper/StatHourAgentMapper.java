package org.cti.cc.mapper;

import org.cti.cc.entity.StatHourAgent;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface StatHourAgentMapper extends BaseMapper<StatHourAgent> {

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<StatHourAgent> list);

    /**
     * 重复统计时，需要先删除
     *
     * @param statTime
     */
    void deleteAgentHourStat(Long statTime);
}