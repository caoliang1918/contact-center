package org.zhongweixian.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2021/9/3
 */
@Component
public class TaskJobOfHour implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfHour.class);

    public final static String CRON = "0 0 0/1 * * ?";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Hour job frist :{} , next:{}", jobExecutionContext.getFireTime(), jobExecutionContext.getNextFireTime());
    }
}
