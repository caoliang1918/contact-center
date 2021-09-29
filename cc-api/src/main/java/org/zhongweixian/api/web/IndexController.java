package org.zhongweixian.api.web;

import oracle.jdbc.proxy.annotation.Post;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/12/15
 */

@RestController
@RequestMapping
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @PostMapping("index")
    public CommonResponse index(@RequestBody String payload){
        logger.info("{}" , payload);
        return new CommonResponse();
    }


}
