package org.zhongweixian.cc.web;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String test() {
        File file = new File("/Users/caoliang/Downloads/lyk0417/callid.txt");
        if (!file.exists()) {
            logger.info("file is not exist");
            return "is error";
        }
        try {
            List<String> callIds = FileUtils.readLines(file, "UTF-8");
            int i = 0;
            for (String callId : callIds) {
                logger.info("index:{} ,callId:{}", i, callId);
                i++;
                jdbcTemplate.update(" insert into test2(callid) values(?)", callId);
            }

        } catch (IOException e) {

        }
        return "is ok";
    }

    @RequestMapping("index")
    public String test2() {
        File file = new File("/Users/caoliang/Downloads/lyk0417/cc-push.2021-04-16.0.log");
        if (!file.exists()) {
            logger.info("file is not exist");
            return "is error";
        }
        try {
            List<String> contents = FileUtils.readLines(file, "UTF-8");
            int i = 0;
            for (String content : contents) {
                if (StringUtils.isNoneBlank(content) && !StringUtils.contains(content, "success")) {
                    continue;
                }
                logger.info("index:{} ,cts:{} , callId:{}", i, content.substring(0, 23), content.substring(content.length() - 21, content.length()));
                i++;
                jdbcTemplate.update(" insert into test1(cts,callid) values(?,?)", content.substring(0, 23), content.substring(content.length() - 21, content.length()));
            }

        } catch (IOException e) {

        }
        return "is ok";
    }

    @PostMapping("agent")
    public CommonResponse login(HttpServletRequest request) {
        logger.info("{}", request.getParameterMap().toString());
        Map<String, Object> params = new HashMap<>();
        params.put("transferAgent", 1001);
        return new CommonResponse(params);
    }
}
