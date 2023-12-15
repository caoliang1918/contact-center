package com.voice9.api.quartz;

import org.quartz.Job;
import org.quartz.JobKey;

import java.util.Map;

/**
 * Created by caoliang on 2021/9/3
 */
public class BaseTask {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务所在组
     */
    private String group;

    /**
     * 下次执行时间
     */
    private String nextFireTime;

    /**
     * 定时任务 的名字和分组名 JobKey
     */
    private JobKey jobKey;
    /**
     * 定时任务 的描述
     */
    private String description;
    /**
     * 定时任务 的执行cron
     */
    private String cronExpression;
    /**
     * 定时任务 的元数据
     */
    private Map<?, ?> jobDataMap;
    /**
     * 定时任务 的 具体执行逻辑类
     */
    private Class<? extends Job> jobClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(String nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Map<?, ?> getJobDataMap() {
        return jobDataMap;
    }

    public void setJobDataMap(Map<?, ?> jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }
}
