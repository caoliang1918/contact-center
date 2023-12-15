package com.voice9.api.configration;

import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2022/4/15
 */

@Component
public class NaconListenService implements EventListener {
    private Logger logger = LoggerFactory.getLogger(NaconListenService.class);


    @Override
    public void onEvent(Event event) {
        logger.info("{}", event);
    }
}
