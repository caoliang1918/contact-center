package org.zhongweixian.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsDtmfEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

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
