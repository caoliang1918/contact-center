package org.zhongweixian.api.configration.mq;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by caoliang on 2020/10/30
 */
@Configuration
public class KafkaConfig {

    public final static String API_CONFIG_TOPIC = "api.config.exchange";


    @Bean
    public NewTopic apiConfigTopic() {
        return new NewTopic(API_CONFIG_TOPIC, 2, (short) 1);
    }

}
