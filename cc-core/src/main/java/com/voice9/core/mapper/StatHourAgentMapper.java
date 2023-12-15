package com.voice9.core.mapper;

import com.voice9.core.entity.StatHourAgent;
import com.voice9.core.mapper.base.BaseMapper;

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