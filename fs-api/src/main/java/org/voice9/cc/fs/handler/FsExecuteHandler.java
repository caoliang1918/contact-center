package org.voice9.cc.fs.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsExecuteEvent;

/**
 * Created by caoliang on 2020/8/31
 */
@Component
@HandlerType("CHANNEL_EXECUTE")
public class FsExecuteHandler extends BaseEventHandler<FsExecuteEvent> {
    private Logger logger = LoggerFactory.getLogger(FsExecuteHandler.class);

    @Override
    public void handleEvent(FsExecuteEvent event) {

    }
}
