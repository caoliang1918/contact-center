package com.voice9.api.configration;

import com.voice9.api.quartz.BaseTask;
import com.voice9.api.quartz.TaskJobOfDay;
import com.voice9.api.quartz.TaskJobOfHour;
import com.voice9.api.quartz.TaskJobOfSecond;
import com.voice9.core.util.DateTimeUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by caoliang on 2021/9/3
 */
@Configuration
public class QuartzConfig {
    private Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    private final static String CC_QUARTZ_GROUP = "cc.quartz";
    private Scheduler scheduler;

    public QuartzConfig(@Autowired SchedulerFactoryBean schedulerFactoryBean) {
        scheduler = schedulerFactoryBean.getScheduler();
    }

    /**
     * 创建和启动 定时任务
     * {@link org.quartz.Scheduler#scheduleJob(JobDetail, Trigger)}
     *
     * @param define 定时任务
     */
    public void scheduleJob(BaseTask define) throws SchedulerException {
        //1.定时任务 的 名字和组名
        JobKey jobKey = define.getJobKey();
        //2.定时任务 的 元数据
        JobDataMap jobDataMap = getJobDataMap(define.getJobDataMap());
        //3.定时任务 的 描述
        String description = define.getDescription();
        //4.定时任务 的 逻辑实现类
        Class<? extends Job> jobClass = define.getJobClass();
        //5.定时任务 的 cron表达式
        String cron = define.getCronExpression();
        JobDetail jobDetail = getJobDetail(jobKey, description, jobDataMap, jobClass);
        Trigger trigger = getTrigger(jobKey, description, jobDataMap, cron);
        scheduler.scheduleJob(jobDetail, trigger);
    }


    /**
     * 暂停Job
     * {@link org.quartz.Scheduler#pauseJob(JobKey)}
     */
    public void pauseJob(JobKey jobKey) throws SchedulerException {
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复Job
     * {@link org.quartz.Scheduler#resumeJob(JobKey)}
     */
    public void resumeJob(JobKey jobKey) throws SchedulerException {
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除Job
     * {@link org.quartz.Scheduler#deleteJob(JobKey)}
     */
    public void deleteJob(JobKey jobKey) {
        try {
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {

        }
    }


    /**
     * @return
     */
    public List<BaseTask> getJobList() {
        List<BaseTask> list = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat(DateTimeUtil.YYYYMMDD_HHMMSS);
        try {
            for (String group : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group))) {
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    if (CollectionUtils.isEmpty(triggers)) {
                        return null;
                    }
                    for (Trigger trigger : triggers) {
                        JobDetail jobDetail = scheduler.getJobDetail(new JobKey(trigger.getJobKey().getName(), trigger.getJobKey().getGroup()));
                        BaseTask baseTask = new BaseTask();
                        baseTask.setName(trigger.getJobKey().getName());
                        baseTask.setGroup(trigger.getJobKey().getGroup());
                        baseTask.setJobKey(jobKey);
                        baseTask.setDescription(jobDetail.getDescription());
                        baseTask.setJobDataMap(jobDetail.getJobDataMap());
                        baseTask.setCronExpression(((CronTriggerImpl) trigger).getCronExpression());
                        baseTask.setJobClass(jobDetail.getJobClass());
                        baseTask.setNextFireTime(sf.format(trigger.getNextFireTime()));
                        list.add(baseTask);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }


    /**
     * 修改Job 的cron表达式
     */
    public boolean modifyJobCron(BaseTask define) {
        String cronExpression = define.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) {
            return false;
        }
        JobKey jobKey = define.getJobKey();
        TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
        try {
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            JobDataMap jobDataMap = getJobDataMap(define.getJobDataMap());
            if (!cronTrigger.getCronExpression().equalsIgnoreCase(cronExpression)) {
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).usingJobData(jobDataMap).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }


    /**
     * 获取定时任务的定义
     * JobDetail是任务的定义,Job是任务的执行逻辑
     *
     * @param jobKey      定时任务的名称 组名
     * @param description 定时任务的 描述
     * @param jobDataMap  定时任务的 元数据
     * @param jobClass    {@link org.quartz.Job} 定时任务的 真正执行逻辑定义类
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap jobDataMap, Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(description).setJobData(jobDataMap).usingJobData(jobDataMap).requestRecovery().storeDurably().build();
    }


    /**
     * 获取Trigger (Job的触发器,执行规则)
     *
     * @param jobKey         定时任务的名称 组名
     * @param description    定时任务的 描述
     * @param jobDataMap     定时任务的 元数据
     * @param cronExpression 定时任务的 执行cron表达式
     */
    public Trigger getTrigger(JobKey jobKey, String description, JobDataMap jobDataMap, String cronExpression) {
        return TriggerBuilder.newTrigger().withIdentity(jobKey.getName(), jobKey.getGroup()).withDescription(description).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).usingJobData(jobDataMap).build();
    }


    public JobDataMap getJobDataMap(Map<?, ?> map) {
        return map == null ? new JobDataMap() : new JobDataMap(map);
    }


    public void initTask(String taskName, String cron, Class classc) throws SchedulerException {
        JobKey jobKey = new JobKey(taskName, CC_QUARTZ_GROUP);
        BaseTask task = new BaseTask();
        task.setJobKey(jobKey);
        task.setJobKey(jobKey);
        task.setCronExpression(cron);
        task.setJobClass(classc);
        task.setDescription(taskName + " 任务");
        this.deleteJob(jobKey);
        this.scheduleJob(task);
    }

    public void create(String taskName, String cron, Class classc) throws SchedulerException {
        JobKey jobKey = new JobKey(taskName, CC_QUARTZ_GROUP);
        BaseTask task = new BaseTask();
        task.setJobKey(jobKey);
        task.setJobKey(jobKey);
        task.setCronExpression(cron);
        task.setJobClass(classc);
        task.setDescription(taskName + " 任务");
        this.scheduleJob(task);
    }


    public void initJob(boolean flag) {
        try {
            List<BaseTask> list = getJobList();
            List<String> jobNames = list.stream().map(BaseTask::getName).collect(Collectors.toList());
            if (flag) {
                //初始化，先删除再创建
                initTask(TaskJobOfSecond.NAME, TaskJobOfSecond.CRON, TaskJobOfSecond.class);
                initTask(TaskJobOfHour.NAME, TaskJobOfHour.CRON, TaskJobOfHour.class);
                initTask(TaskJobOfDay.NAME, TaskJobOfDay.CRON, TaskJobOfDay.class);
                return;
            }

            /**
             * 任务没有再创建
             */
            if (!jobNames.contains(TaskJobOfSecond.NAME)) {
                create(TaskJobOfSecond.NAME, TaskJobOfSecond.CRON, TaskJobOfSecond.class);
            }
            if (!jobNames.contains(TaskJobOfHour.NAME)) {
                create(TaskJobOfHour.NAME, TaskJobOfHour.CRON, TaskJobOfHour.class);
            }
            if (!jobNames.contains(TaskJobOfDay.NAME)) {
                create(TaskJobOfDay.NAME, TaskJobOfDay.CRON, TaskJobOfDay.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
