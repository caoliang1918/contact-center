package com.voice9.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by caoliang on 2021/9/3
 */
@Component
public class TaskJobOfSecond implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfSecond.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    public final static String NAME = "TaskJobOfSecond";
    public final static String CRON = "0/2 * * * * ?";


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        if (date.after(jobExecutionContext.getNextFireTime())) {
            return;
        }
        logger.debug("second job start :{} , next:{}", jobExecutionContext.getFireTime(), jobExecutionContext.getNextFireTime());
        ServiceInstance serviceInstance = loadBalancerClient.choose("cc-ivr");
        if (serviceInstance == null) {
            return;
        }
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://" + serviceInstance.getServiceId() + ":" + serviceInstance.getPort() + "/cc-ivr/index/health", String.class);
//        logger.info("{} ", responseEntity.getBody());
    }
}
