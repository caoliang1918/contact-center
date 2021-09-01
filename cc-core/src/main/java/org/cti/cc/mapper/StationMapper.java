package org.cti.cc.mapper;

import org.cti.cc.entity.Station;
import org.cti.cc.mapper.base.BaseMapper;

public interface StationMapper extends BaseMapper<Station> {

    Station selectByAppId(Integer applicationId);
}