package org.voice9.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsReloadXmlEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2022/5/4
 */
@Component
@HandlerType("RELOADXML")
public class FsReloadXmlHandler extends BaseEventHandler<FsReloadXmlEvent> {

    @Override
    public void handleEvent(FsReloadXmlEvent event) {
        logger.info("{}" , event);
    }
}
