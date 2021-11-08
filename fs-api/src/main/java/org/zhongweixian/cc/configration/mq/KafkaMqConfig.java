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
import org.springframework.kafka.config.TopicBuilder;
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
public class KafkaMqConfig {
    private Logger logger = LoggerFactory.getLogger(KafkaMqConfig.class);

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
        return TopicBuilder.name(Constants.AGENT_STATE).partitions(2).replicas(1).build();
    }

    /**
     * 配置信息交换机
     *
     * @return
     */
    @Bean
    public NewTopic configTopic() {
        return TopicBuilder.name(Constants.CC_CONFIG).partitions(2).replicas(1).build();
    }

    @Bean
    public NewTopic callDeviceTopic() {
        return TopicBuilder.name(Constants.CALL_DEVICE).partitions(2).replicas(1).build();
    }

    @Bean
    public NewTopic callLogTopic() {
        return TopicBuilder.name(Constants.CALL_LOG).partitions(2).replicas(1).build();
    }

    @Bean
    public NewTopic callDetailTopic() {
        return TopicBuilder.name(Constants.CALL_DETAIL).partitions(2).replicas(1).build();
    }

    @Bean
    public NewTopic agentStateLogTopic() {
        return TopicBuilder.name(Constants.AGENT_STATE_LOG).partitions(2).replicas(1).build();
    }


}
