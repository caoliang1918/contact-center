package org.zhongweixian.cc.configration.mq;

import org.cti.cc.constant.Constants;
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


    /**
     * 坐席状态交换机
     *
     * @return
     */
    @Bean
    public TopicExchange agentStateExchange() {
        return new TopicExchange(Constants.AGENT_STATE_EXCHANGE);
    }

    /**
     * 配置信息交换机
     *
     * @return
     */
    @Bean
    public TopicExchange configExchange() {
        return new TopicExchange(Constants.CONFIG_EXCHANGE);
    }


    @Bean
    public TopicExchange callDeviceExchange() {
        return new TopicExchange(Constants.CALL_DEVICE_EXCHANGE);
    }

    @Bean
    public TopicExchange callLogExchange() {
        return new TopicExchange(Constants.CALL_LOG_EXCHANGE);
    }

    @Bean
    public TopicExchange callDetailExchange() {
        return new TopicExchange(Constants.CALL_DETAIL_EXCHANGE);
    }

    @Bean
    public TopicExchange agentStateLogExchange() {
        return new TopicExchange(Constants.AGENT_STATE_LOG_EXCHANGE);
    }

    @Bean
    public Queue callDeviceQueue() {
        return new Queue(Constants.CALL_DEVICE_QUEUE);
    }

    @Bean
    public Queue callLogQueue() {
        return new Queue(Constants.CALL_LOG_QUEUE);
    }

    @Bean
    public Queue callDetailQueue() {
        return new Queue(Constants.CALL_DETAIL_QUEUE);
    }

    @Bean
    Queue agentStateLogQueue() {
        return new Queue(Constants.AGENT_STATE_LOG_QUEUE,true);
    }

    @Bean
    public Binding bindCallDevice() {
        return BindingBuilder.bind(callDeviceQueue()).to(callDeviceExchange()).with(Constants.CALL_CDR_ROUTING);
    }

    @Bean
    public Binding bindCallLog() {
        return BindingBuilder.bind(callLogQueue()).to(callLogExchange()).with(Constants.CALL_CDR_ROUTING);
    }

    @Bean
    public Binding bindCallDetail() {
        return BindingBuilder.bind(callDetailQueue()).to(callDetailExchange()).with(Constants.CALL_CDR_ROUTING);
    }

    @Bean
    public Binding bindAgentStateLog() {
        return BindingBuilder.bind(agentStateLogQueue()).to(agentStateLogExchange()).with(Constants.DEFAULT_KEY);
    }


}
