package org.zhongweixian.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.cloud.nacos.server-addr}")
    private String nacosAddr;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    public final static String CRON = "0/1 * * * * ?";


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        if (date.after(jobExecutionContext.getNextFireTime())) {
            return;
        }
        logger.info("Second job frist :{} , next:{}", jobExecutionContext.getFireTime(), jobExecutionContext.getNextFireTime());
        ServiceInstance serviceInstance = loadBalancerClient.choose("fs-api");
        if (serviceInstance == null) {
            return;
        }
        restTemplate.getForEntity("http://" + serviceInstance.getServiceId() + ":" + serviceInstance.getPort() + "/index/acd", String.class);
    }
}
