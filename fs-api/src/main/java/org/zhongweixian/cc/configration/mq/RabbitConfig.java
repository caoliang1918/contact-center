package org.zhongweixian.cc.configration.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by caoliang on 2020/10/30
 */
@Configuration
public class RabbitConfig {

    public final static String AGENT_STATE_EXCHANGE = "agent.state.exchange";
    public final static String DEFAULT_KEY = "";

    public final static String CALL_DEVICE_EXCHANGE = "call.device.exchange";
    public final static String CALL_DEVICE_QUEUE = "call.device";
    public final static String CALL_DEVICE_ROUTING = "call.device.key";


    public final static String CALL_LOG_EXCHANGE = "call.log.exchange";
    public final static String CALL_LOG_QUEUE = "call.log";
    public final static String CALL_LOG_ROUTING = "call.log.key";



    @Bean
    public TopicExchange agentStateExchange() {
        return new TopicExchange(AGENT_STATE_EXCHANGE);
    }

    @Bean
    public TopicExchange callDeviceExchange() {
        return new TopicExchange(CALL_DEVICE_EXCHANGE);
    }

    @Bean
    public TopicExchange callLogExchange() {
        return new TopicExchange(CALL_LOG_EXCHANGE);
    }

    @Bean
    public Queue callDeviceQueue() {
        return new Queue(CALL_DEVICE_QUEUE);
    }

    @Bean
    public Queue callLogQueue() {
        return new Queue(CALL_LOG_QUEUE);
    }

    @Bean
    public Binding bindCallDevice() {
        return BindingBuilder.bind(callDeviceQueue()).to(callDeviceExchange()).with(CALL_DEVICE_ROUTING);
    }

    @Bean
    public Binding bindCallLog() {
        return BindingBuilder.bind(callLogQueue()).to(callLogExchange()).with(CALL_LOG_ROUTING);
    }
}
