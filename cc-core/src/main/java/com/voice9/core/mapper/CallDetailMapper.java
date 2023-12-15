package com.voice9.core.mapper;

import com.voice9.core.entity.CallDetail;
import com.voice9.core.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface CallDetailMapper extends BaseMapper<CallDetail> {

    /**
     *
     * @param callId
     * @param month
     * @return
     */
    CallDetail selectByCallId(Long callId , String month);

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
    int clearTable( @Param("end") Long end);
}