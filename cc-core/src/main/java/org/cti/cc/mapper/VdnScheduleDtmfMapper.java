package org.cti.cc.mapper;

import org.cti.cc.entity.VdnScheduleDtmf;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface VdnScheduleDtmfMapper extends BaseMapper<VdnScheduleDtmf> {

    List<VdnScheduleDtmf> selectByNavagite(Long id);

}