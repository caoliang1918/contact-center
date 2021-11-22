package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CallDtmf;
import org.cti.cc.mapper.base.BaseMapper;

public interface CallDtmfMapper extends BaseMapper<CallDtmf> {



    /**
     * 迁移表
     *
     * @param month
     */
    void createNewTable( @Param("month") String month);


    /**
     * 删除数据
     *
     * @param end
     */
    int clearTable(@Param("end") Long end);
}