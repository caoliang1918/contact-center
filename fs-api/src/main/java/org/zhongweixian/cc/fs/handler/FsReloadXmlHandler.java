package org.zhongweixian.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsReloadXmlEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

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
