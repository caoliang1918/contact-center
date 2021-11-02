package org.zhongweixian.cc.configration.mq;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.cti.cc.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by caoliang on 2020/10/30
 */
@Configuration
@Component
public class AmqpConfig {
    private Logger logger = LoggerFactory.getLogger(AmqpConfig.class);

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Autowired
    private List<NewTopic> newTopics;


    @PostConstruct
    public void init() {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        ListTopicsResult listTopics = adminClient.listTopics();
        listTopics.listings().thenApply(topicListings -> {
            List<String> existTopicNames = topicListings.stream().map(TopicListing::name).collect(Collectors.toList());
            List<NewTopic> saveTopics = filterTopics(newTopics, existTopicNames);
            logger.info("saveTopics:{}", saveTopics.stream().map(NewTopic::name));
            return adminClient.createTopics(saveTopics);
        });
    }


    private List<NewTopic> filterTopics(List<NewTopic> newTopicList, List<String> existTopicNames) {
        List<NewTopic> addNewTopic = new ArrayList<>();
        for (NewTopic newTopic : newTopicList) {
            if (!existTopicNames.contains(newTopic.name())) {
                addNewTopic.add(newTopic);
            }
        }
        return addNewTopic;
    }


    /**
     * 坐席状态交换机
     *
     * @return
     */
    @Bean
    public NewTopic agentStateExchange() {
        return new NewTopic(Constants.AGENT_STATE_EXCHANGE, 2, (short) 1);
    }

    /**
     * 配置信息交换机
     *
     * @return
     */
    @Bean
    public NewTopic configTopic() {
        return new NewTopic(Constants.CONFIG_EXCHANGE, 2, (short) 1);
    }


    @Bean
    public NewTopic callDeviceTopic() {
        return new NewTopic(Constants.CALL_DEVICE_EXCHANGE, 2, (short) 1);
    }

    @Bean
    public NewTopic callLogTopic() {
        return new NewTopic(Constants.CALL_LOG_EXCHANGE, 2, (short) 1);
    }

    @Bean
    public NewTopic callDetailTopic() {
        return new NewTopic(Constants.CALL_DETAIL_EXCHANGE, 2, (short) 1);
    }

    @Bean
    public NewTopic agentStateLogTopic() {
        return new NewTopic(Constants.AGENT_STATE_LOG_EXCHANGE, 2, (short) 1);
    }


}
