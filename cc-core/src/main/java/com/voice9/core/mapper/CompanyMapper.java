package com.voice9.core.mapper;

import com.voice9.core.entity.Company;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.CompanyInfo;

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