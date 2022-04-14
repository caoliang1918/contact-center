package org.zhongweixian.api.quartz;

import io.minio.DeleteBucketPolicyArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.apache.commons.lang3.time.DateFormatUtils;
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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caoliang on 2021/9/5
 */
@Component
public class TaskJobOfDay implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfDay.class);

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

    /**
     * 录音文件保留时间
     */
    @Value("${minio.record.retain:0}")
    private Integer minioRecordRetain;

    @Autowired
    private CallLogService callLogService;

    @Autowired
    private PushFailLogMapper pushFailLogMapper;

    @Autowired
    private MinioClient minioClient;

    /**
     * 每天定时任务，凌晨延时5分钟执行
     */
    public final static String CRON = "0 5 0 * * ? *";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long start = DateTimeUtil.getBeforDay();
        Long end = DateTimeUtil.getNowDay();
        logger.info("Day job start: {} - end:{}", DateFormatUtils.format(start, DateTimeUtil.YYYYMMDD_HHMMSS), DateFormatUtils.format(end, DateTimeUtil.YYYYMMDD_HHMMSS));


        //清理话单数据
        deleteCallLog();

        //删除推送失败的数据
        deletePushFailLog();

        //删除录音
        deleteMinioRecord();
    }

    public void deleteCallLog() {
        //获取前N天的时间
        Long time = DateTimeUtil.getBeforeDay(callRetain);
        int resule = callLogService.cleaDayOfData(time);
        logger.info("delete {} call of logs", resule);
    }

    /**
     * 删除推送失败的数据
     */
    private void deletePushFailLog() {
        Long time = DateTimeUtil.getBeforeDay(pushLogRetain);
        int result = pushFailLogMapper.deletePushFailLog(time);
        logger.info("delete {} day of {} size push_fail_log", pushLogRetain, result);
    }

    private void deleteMinioRecord() {
        if (minioRecordRetain == 0) {
            return;
        }
        //获取前N天的时间
        Long time = DateTimeUtil.getBeforeDay(minioRecordRetain);
        try {
            minioClient.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket("cc-record").region("20210922").build());
        } catch ( Exception e) {
           logger.error(e.getMessage() , e);
        }
    }
}
