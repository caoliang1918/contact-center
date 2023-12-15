package com.voice9.core.mapper;

import com.voice9.core.entity.CompanyDisplay;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.CompanyDisplayPo;
import org.apache.ibatis.annotations.Param;

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
    @Override
    CompanyDisplay selectById(@Param("companyId") Long companyId, @Param("id") Long id);
}