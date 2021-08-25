package org.zhongweixian.ivr.web;

import org.cti.cc.po.CallInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhongweixian.ivr.scxml.SimpleIvrMachine;

/**
 * Created by caoliang on 2021/8/10
 */

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private SimpleIvrMachine ivrMachine;

    @GetMapping("index")
    public String index() {
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(100L).build();
        ivrMachine.runIvr(callInfo, 1L);
        return "is ok";
    }

    @GetMapping("index2")
    public String index2() {
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(100L).build();
        ivrMachine.executeNext(callInfo.getCallId());
        return "is ok";
    }
}
