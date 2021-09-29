package org.cti.cc.mapper;

import org.cti.cc.entity.PushFailLog;
import org.cti.cc.mapper.base.BaseMapper;

public interface PushFailLogMapper extends BaseMapper<PushFailLog> {


    int deletePushFailLog(Long cts);

    /**
     * 推送成功
     * @param pushFailLog
     * @return
     */
    int pushSuccess(PushFailLog pushFailLog);

}