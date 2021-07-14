package org.cti.cc.mapper;

import org.cti.cc.entity.VdnSchedule;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.VdnSchedulePo;

import java.util.List;

public interface VdnScheduleMapper extends BaseMapper<VdnSchedule> {

    List<VdnSchedulePo> selectByVdn(Long id);

}