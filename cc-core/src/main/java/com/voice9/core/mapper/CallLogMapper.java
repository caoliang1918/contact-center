package com.voice9.core.mapper;

import com.voice9.core.entity.CallLog;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.CallLogPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface CallLogMapper extends BaseMapper<CallLog> {

    /**
     * 查询话单
     *
     * @param companyId
     * @param callId
     * @param month
     * @return
     */
    CallLogPo getCall(@Param("companyId") Long companyId, @Param("callId") Long callId, @Param("month") String month);


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
     * @param month
     */
    void updateTableMonth(String month);

    /**
     * 查询话单列表
     *
     * @param params
     * @return
     */
    List<CallLogPo> getCallList(Map<String, Object> params);
}