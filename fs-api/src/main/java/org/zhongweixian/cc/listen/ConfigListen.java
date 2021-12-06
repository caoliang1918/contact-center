package org.zhongweixian.cc.listen;

import org.cti.cc.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2021/8/3
 */
@Component
public class ConfigListen {
    private Logger logger = LoggerFactory.getLogger(ConfigListen.class);

    /**
     * 接受配置
     *
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(exchange=@Exchange(value = Constants.CC_CONFIG_EXCHANGE, type = ExchangeTypes.TOPIC),key = Constants.DEFAULT_KEY , value =  @Queue( Constants.CC_CONFIG_QUEUE))})
    public void listenAgentState(@Payload String payload) {
        logger.info("receive config message:{}", payload);
    }
}
