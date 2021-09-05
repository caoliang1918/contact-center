package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CallLog;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CallLogPo;


public interface CallLogMapper extends BaseMapper<CallLog> {

    /**
     * 查询话单
     *
     * @param companyId
     * @param callId
     * @return
     */
    CallLogPo getCall(@Param("companyId") Long companyId, @Param("callId") Long callId);


    /**
     * 查询话单
     *
     * @param startTime
     * @param endTime
     * @param uuid1
     * @return
     */
    CallLogPo getCallByUuid1(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("uuid1") Long uuid1);


    /**
     * 更新话单
     *
     * @param callLog
     * @return
     */
    int updateByCallId(CallLog callLog);

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