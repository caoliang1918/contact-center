package org.cti.cc.mapper;

import org.cti.cc.entity.VdnConfig;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.VdnSchedulePo;

import java.util.List;

public interface VdnConfigMapper extends BaseMapper<VdnConfig> {

    List<VdnSchedulePo> selectByVdn(Long id);

}