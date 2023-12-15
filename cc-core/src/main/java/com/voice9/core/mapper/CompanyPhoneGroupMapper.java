package com.voice9.core.mapper;

import com.voice9.core.entity.CompanyPhoneGroup;
import com.voice9.core.mapper.base.BaseMapper;

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