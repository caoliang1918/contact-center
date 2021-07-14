package org.cti.cc.mapper;

import org.cti.cc.entity.CallDevice;
import org.cti.cc.mapper.base.BaseMapper;

public interface CallDeviceMapper extends BaseMapper<CallDevice> {

    /**
     * @param callId
     * @return
     */
    CallDevice selectByCallId(Long callId);
}