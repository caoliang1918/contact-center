package org.zhongweixian.cc.listen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
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
     * @param record
     */
    @KafkaListener(topics = "sync.config", groupId = "${spring.instance.id}")
    public void listenAgentState(ConsumerRecord<String, String> record) {
        logger.info("receive config message:{}", record.value());
    }
}
