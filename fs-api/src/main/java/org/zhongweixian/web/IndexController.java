package org.zhongweixian.web;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.cti.cc.po.CallLogPo;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.util.SnowflakeIdWorker;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhongweixian.cc.service.CallCdrService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by caoliang on 2020/8/24
 */
@RestController
@RequestMapping("index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private CallCdrService callCdrService;

    @Autowired
    private StringEncryptor encrypt;


    /**
     * @param callId
     * @return
     */
    @GetMapping("getcall")
    public CommonResponse<CallLogPo> getCall(@RequestParam Long callId) {
        return new CommonResponse<>(callCdrService.getCall(null, callId));
    }


    @GetMapping("/encrypt")
    public CommonResponse encrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.encrypt(text));
    }


    @GetMapping("/decrypt")
    public CommonResponse decrypt(@RequestParam String text) {
        return new CommonResponse(encrypt.decrypt(text));
    }


    @GetMapping("health")
    public Health health() {
        return Health.up().build();
    }

    /**
     * 测试
     *
     * @return
     */
    //@GetMapping("timer")
    public Long hashedWheelTimer() {
        //设置每个格子是 100ms, 总共 102400 个格子
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 102400);

        Long now = Instant.now().toEpochMilli();
        for (int i = 0; i < 10000; i++) {
            logger.info("加入一个任务，ID = {}, time= {}", i, LocalDateTime.now());
            hashedWheelTimer.newTimeout(new QueueTime(snowflakeIdWorker.nextId()), i, TimeUnit.SECONDS);
        }
        logger.info("创建10000个任务耗时 {} 毫秒", Instant.now().toEpochMilli() - now);
        return Instant.now().toEpochMilli() - now;
    }

    class QueueTime implements TimerTask {

        private Long taskId;

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public QueueTime(Long taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            logger.info("任务执行, callId = {}, time= {}", taskId, LocalDateTime.now());
        }
    }


}

