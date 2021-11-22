package org.zhongweixian.api.quartz;

import org.cti.cc.util.DateTimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.api.service.CallLogService;

/**
 * Created by caoliang on 2021/9/5
 */
@Component
public class TaskJobOfMonth implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfMonth.class);


    @Autowired
    private CallLogService callLogService;

    /**
     * 每月月初凌晨1点执行
     */
    public final static String CRON = "0 0 1 1 * ?";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("month job execute :{}", jobExecutionContext.getFireTime());
        Long start = DateTimeUtil.getLastMonthStartTime();
        Long end = DateTimeUtil.getLastMonthEndTime();
        subTable(start, end);
    }


    /**
     * 按月分表
     *
     * @param start 上个月开始时间
     * @param end   上个月结束时间
     */
    private void subTable(Long start, Long end) {
        String month = DateTimeUtil.getNowMonth();
        callLogService.subTable(month);
    }
}
