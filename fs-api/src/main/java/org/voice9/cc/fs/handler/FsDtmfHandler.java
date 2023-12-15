package org.voice9.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsDtmfEvent;

/**
 * Created by caoliang on 2020/10/31
 * <p>
 * 按键收号
 */

@Component
@HandlerType("DTMF")
public class FsDtmfHandler extends BaseEventHandler<FsDtmfEvent> {
    @Override
    public void handleEvent(FsDtmfEvent event) {
        logger.info("{}", event.toString());
    }
}
