package org.zhongweixian.api.web;

import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by caoliang on 2020/12/15
 */

@RestController
@RequestMapping
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @PostMapping("index")
    public CommonResponse index(@RequestBody String payload) {
        logger.info("{}", payload);
        return new CommonResponse();
    }


}
