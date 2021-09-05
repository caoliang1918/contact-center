package org.zhongweixian.api.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.cti.cc.util.DateTimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.api.service.CallLogService;
import org.zhongweixian.api.service.StatWorkService;

/**
 * Created by caoliang on 2021/9/5
 */
@Component
public class TaskJobOfDay implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfDay.class);

    /**
     * 坐席日志表保留天数
     */
    @Value("${agent.state.work.retain:10}")
    private Integer agentStateWork;

    @Autowired
    private CallLogService callLogService;

    @Autowired
    private StatWorkService statWorkService;

    /**
     * 每天定时任务，凌晨延时30分钟执行
     */
    public final static String CRON = "0 30 0 * * ? *";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long start = DateTimeUtil.getBeforDay();
        Long end = DateTimeUtil.getNowDay();
        logger.info("Day jon start: {} - end:{}", DateFormatUtils.format(start, DateTimeUtil.YYYYMMDD_HHMMSS), DateFormatUtils.format(end, DateTimeUtil.YYYYMMDD_HHMMSS));


    }

    /**
     * 清理坐席日志表
     *
     * @param start
     * @param end
     */
    private void clearAgentStateLog(Long start, Long end) {
        //获取前N天的时间
        Long time = DateTimeUtil.getBeforeDay(agentStateWork);
        int result = statWorkService.clearAgentStateWork(time);
        logger.info("delete {} day of {} size cc_agent_state_log", agentStateWork, result);
    }
}
