package org.cti.cc.mapper;

import org.cti.cc.entity.Company;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CompanyInfo;

import java.util.List;
import java.util.Map;

public interface CompanyMapper extends BaseMapper<Company> {


    /**
     * 逻辑删除
     *
     * @param company
     * @return
     */
    int deleteCompany(Company company);


    /**
     *
     * @return
     */
    List<CompanyInfo> selectCompanyInfoList(Map<String, Object> params);

    CompanyInfo selectById(Long id);
}