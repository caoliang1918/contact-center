package com.voice9.core.mapper;

import com.voice9.core.entity.VdnDtmf;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;

public interface VdnDtmfMapper extends BaseMapper<VdnDtmf> {

    List<VdnDtmf> selectByNavagite(Long id);

}