package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.mapper.base.BaseMapper;

public interface CallDeviceMapper extends BaseMapper<CallDevice> {

    /**
     * @param callId
     * @return
     */
    CallDevice selectByCallId(Long callId);


    /**
     * 迁移表
     *
     * @param start
     * @param end
     * @param month
     */
    void createNewTable(@Param("start") Long start, @Param("end") Long end, @Param("month") String month);


    /**
     * 删除数据
     *
     * @param start
     * @param end
     */
    void clearTable(@Param("start") Long start, @Param("end") Long end);
}