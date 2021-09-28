package org.zhongweixian.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by caoliang on 2021/9/3
 */
@Component
public class TaskJobOfSecond implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfSecond.class);

    public final static String CRON = "0/2 * * * * ?";


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        if (date.after(jobExecutionContext.getNextFireTime())) {
            return;
        }
        logger.info("Second job frist :{} , next:{}", jobExecutionContext.getFireTime(), jobExecutionContext.getNextFireTime());
    }
}
