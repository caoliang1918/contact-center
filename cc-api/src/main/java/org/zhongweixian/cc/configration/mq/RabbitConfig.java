package org.zhongweixian.cc.configration.mq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by caoliang on 2020/10/30
 */
@Configuration
public class RabbitConfig {

    public final static String API_CONFIG_EXCHANGE = "api.config.exchange";
    public final static String API_CONFIG_ROUTING_KEY = "conf";

    @Bean
    public TopicExchange apiConfigExchange() {
        return new TopicExchange(API_CONFIG_EXCHANGE);
    }


}
