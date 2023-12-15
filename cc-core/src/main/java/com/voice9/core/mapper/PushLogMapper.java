package com.voice9.core.mapper;

import com.voice9.core.entity.PushLog;
import com.voice9.core.mapper.base.BaseMapper;

public interface PushLogMapper extends BaseMapper<PushLog> {


    int deletePushLog(Long cts);

    /**
     * 推送成功
     * @param pushLog
     * @return
     */
    int pushSuccess(PushLog pushLog);

}