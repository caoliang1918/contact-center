package org.cti.cc.mapper;

import org.cti.cc.entity.CompanyPhoneGroup;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface CompanyPhoneGroupMapper extends BaseMapper<CompanyPhoneGroup> {

    /**
     * 通过号码池id查询号码
     *
     * @param displayId
     * @return
     */
    List<String> selectDisplayByGroupId(Long displayId);

}