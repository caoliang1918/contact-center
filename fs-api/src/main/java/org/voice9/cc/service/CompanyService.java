package org.voice9.cc.service;

import com.voice9.core.entity.Company;
import com.voice9.core.po.CompanyInfo;

import java.util.Map;

/**
 * Created by caoliang on 2020/11/6
 */
public interface CompanyService extends BaseService<Company> {

    Map<Long, CompanyInfo> initAll();

    /**
     * 初始化vdn
     *
     * @param companyInfo
     */
    void initVdn(CompanyInfo companyInfo);

}
