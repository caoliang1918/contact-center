package org.zhongweixian.ivr.action;

import org.apache.commons.scxml2.SCInstance;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.ivr.action.base.BaseAction;

/**
 * Created by caoliang on 2021/9/23
 */
@Component
public class InitAction extends BaseAction {

    @Override
    public void execute(SCInstance scInstance, CallInfo callInfo) {
        logger.info("initAction run :{}", callInfo.getCallId());
    }


}
