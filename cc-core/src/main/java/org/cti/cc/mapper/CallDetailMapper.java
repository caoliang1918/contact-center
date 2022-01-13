package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.mapper.base.BaseMapper;

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