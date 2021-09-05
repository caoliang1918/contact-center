package org.zhongweixian.api.service;

import org.cti.cc.entity.CallLog;

/**
 * Created by caoliang on 2021/9/5
 */
public interface CallLogService extends BaseService<CallLog> {


    /**
     * 按月分表
     *
     * @param start 开始时间
     * @param end   结束时间
     * @param month 月份
     */
    void subTable(Long start, Long end, String month);



}
