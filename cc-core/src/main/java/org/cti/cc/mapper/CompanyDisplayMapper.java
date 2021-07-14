package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.CompanyDisplay;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CompanyDisplayPo;

import java.util.List;
import java.util.Map;

public interface CompanyDisplayMapper extends BaseMapper<CompanyDisplay> {

    /**
     * 分页
     *
     * @param params
     * @return
     */
    List<CompanyDisplayPo> selectPage(Map<String, Object> params);

    /**
     * 查询
     *
     * @param companyId
     * @param id
     * @return
     */
    CompanyDisplay selectById(@Param("companyId") Long companyId, @Param("id") Long id);
}