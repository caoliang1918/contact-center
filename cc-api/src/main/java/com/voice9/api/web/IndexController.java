package com.voice9.api.web;

import com.voice9.api.service.AdminService;
import com.voice9.core.po.CommonResponse;
import com.voice9.core.vo.AdminLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Create by caoliang on 2020/12/15
 */

@RestController
@RequestMapping("index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AdminService adminService;


    /**
     * 1.1.1 坐席登录
     *
     * @param adminLogin
     * @return
     */
    @PostMapping("login")
    public CommonResponse login(@Validated @RequestBody AdminLogin adminLogin) {
        return new CommonResponse(adminService.login(adminLogin));
    }

    /**
     * 1.1.2 坐席退出
     *
     * @param token
     * @return
     */
    @GetMapping("logout")
    public CommonResponse logout(@RequestParam String token) {
        return new CommonResponse(adminService.logout(token));
    }


    /**
     * 1.1.3 统一health探活
     *
     * @return
     */
        @GetMapping("health")
    public Health health() {
        return Health.up().build();
    }


    /**
     * 1.1.3 接口测试
     *
     * @param payload
     * @return
     */
    @PostMapping("index")
    public CommonResponse index(@RequestBody String payload) {
        logger.info("{}", payload);
        return new CommonResponse();
    }




}
