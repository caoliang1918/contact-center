package org.cti.cc.mapper;

import org.cti.cc.entity.CallDetail;
import org.cti.cc.mapper.base.BaseMapper;

public interface CallDetailMapper extends BaseMapper<CallDetail> {

    CallDetail selectByCallId(Long callId);
}