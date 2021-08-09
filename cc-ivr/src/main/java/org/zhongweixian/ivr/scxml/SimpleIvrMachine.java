package org.zhongweixian.ivr.scxml;

import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.model.SCXML;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2021/8/8
 */
@Component
public class SimpleIvrMachine {


    public void runIvr(CallInfo callInfo, Long ivrId) {
        SCXML scxml = null;
        SCXMLExecutor scxmlExecutor = null;




    }
}
