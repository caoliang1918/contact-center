package org.cti.cc.mapper;

import org.cti.cc.entity.CallDtmf;

public interface CallDtmfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CallDtmf record);

    int insertSelective(CallDtmf record);

    CallDtmf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CallDtmf record);

    int updateByPrimaryKey(CallDtmf record);
}