package com.voice9.core.mapper;

import com.voice9.core.entity.VdnConfig;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.VdnSchedulePo;

import java.util.List;

public interface VdnConfigMapper extends BaseMapper<VdnConfig> {

    List<VdnSchedulePo> selectByVdn(Long id);

}