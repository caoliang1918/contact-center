package com.voice9.core.mapper;

import com.voice9.core.entity.CompanyStat;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;


public interface CompanyStatMapper extends BaseMapper<CompanyStat> {

    /**
     * 查询企业
     *
     * @param companyId
     * @return
     */
    List<CompanyStat> selectByCompanyId(Long companyId);
}