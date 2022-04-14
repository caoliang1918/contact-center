package org.cti.cc.mapper;

import org.cti.cc.entity.VdnDtmf;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface VdnDtmfMapper extends BaseMapper<VdnDtmf> {

    List<VdnDtmf> selectByNavagite(Long id);

}