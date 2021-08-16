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
@RequestMapping("index")
public class IndexController {

    @Autowired
    private SimpleIvrMachine ivrMachine;

    @GetMapping()
    public String index() {
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(1L).build();
        ivrMachine.runIvr(callInfo, 1L);
        return "is ok";
    }
}
