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
     * 生成临时queue
     *
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "sync.config-" + "${spring.application.id}" , autoDelete = "true"),
            key = Constants.DEFAULT_KEY, exchange = @Exchange(value = Constants.CONFIG_EXCHANGE, type = ExchangeTypes.TOPIC))})
    public void listenAgentState(@Payload String payload) {
        logger.info("receive config message:{}", payload);
    }
}
