package org.zhongweixian.api.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.cti.cc.entity.PushFailLog;
import org.cti.cc.mapper.PushFailLogMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/5
 */
@Component
public class TaskJobOfDay implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfDay.class);

    /**
     * 坐席日志表保留天数
     */
    @Value("${agent.state.work.retain:1}")
    private Integer agentStateWorkRetain;

    /**
     * 话单保留1天
     */
    @Value("${call.retain:1}")
    private Integer callRetain;

    /**
     * 话单推送保留5天
     */
    @Value("${push.log.retain:5}")
    private Integer pushLogRetain;

    @Autowired
    private CallLogService callLogService;

    @Autowired
    private StatWorkService statWorkService;

    @Autowired
    private PushFailLogMapper pushFailLogMapper;

    /**
     * 每天定时任务，凌晨延时5分钟执行
     */
    public final static String CRON = "0 5 0 * * ? *";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long start = DateTimeUtil.getBeforDay();
        Long end = DateTimeUtil.getNowDay();
        logger.info("Day job start: {} - end:{}", DateFormatUtils.format(start, DateTimeUtil.YYYYMMDD_HHMMSS), DateFormatUtils.format(end, DateTimeUtil.YYYYMMDD_HHMMSS));


        deleteAgentStateLog();

        deleteCallLog();

        deletePushFailLog();
    }

    /**
     * 清理坐席日志表
     */
    private void deleteAgentStateLog() {
        //获取前N天的时间
        Long time = DateTimeUtil.getBeforeDay(agentStateWorkRetain);
        int result = statWorkService.deleteAgentStateWork(time);
        logger.info("delete {} day of {} size cc_agent_state_log", agentStateWorkRetain, result);
    }

    public void deleteCallLog() {
        //获取前N天的时间
        Long time = DateTimeUtil.getBeforeDay(callRetain);
        callLogService.clearCallLog(time);
    }

    /**
     * 删除推送失败的数据
     */
    private void deletePushFailLog() {
        Long time = DateTimeUtil.getBeforeDay(pushLogRetain);
        int result = pushFailLogMapper.deletePushFailLog(time);
        logger.info("delete {} day of {} size push_fail_log", pushLogRetain, result);
    }
}
