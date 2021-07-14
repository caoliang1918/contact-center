package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CompanyPhone;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface CompanyPhoneMapper extends BaseMapper<CompanyPhone> {

    /**
     * 查询
     *
     * @param companyId
     * @param id
     * @return
     */
    CompanyPhone selectById(@Param("companyId") Long companyId, @Param("id") Long id);

    /**
     * 通过号码池id查询
     *
     * @param id
     * @return
     */
    List<CompanyPhone> selectByDisplay(Long id);
}