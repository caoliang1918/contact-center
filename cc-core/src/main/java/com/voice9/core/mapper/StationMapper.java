package com.voice9.core.mapper;

import com.voice9.core.entity.Station;
import com.voice9.core.mapper.base.BaseMapper;

public interface StationMapper extends BaseMapper<Station> {

    Station selectByAppId(Integer applicationId);
}