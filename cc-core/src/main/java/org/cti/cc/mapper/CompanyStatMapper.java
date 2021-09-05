package org.cti.cc.mapper;

import org.cti.cc.entity.CompanyStat;
import org.cti.cc.mapper.base.BaseMapper;

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