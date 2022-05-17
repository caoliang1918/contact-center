package org.cti.cc.mapper;

import org.cti.cc.entity.PushLog;
import org.cti.cc.mapper.base.BaseMapper;

public interface PushLogMapper extends BaseMapper<PushLog> {


    int deletePushLog(Long cts);

    /**
     * 推送成功
     * @param pushLog
     * @return
     */
    int pushSuccess(PushLog pushLog);

}