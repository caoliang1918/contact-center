package com.voice9.api.web;

import com.voice9.api.quartz.TaskJobOfHour;
import com.voice9.api.service.CompanyService;
import com.voice9.api.service.StatWorkService;
import com.voice9.core.po.CommonResponse;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.voice9.api.configration.QuartzConfig;

import java.time.Instant;
import java.util.List;

/**
 * Created by caoliang on 2021/9/7
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {
    private Logger logger = LoggerFactory.getLogger(QuartzController.class);


    @Autowired
    private QuartzConfig quartzConfig;

    @Autowired
    private TaskJobOfHour taskJobOfHour;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StatWorkService statWorkService;

    @GetMapping()
    public CommonResponse index() {
        return new CommonResponse(quartzConfig.getJobList());
    }


    @GetMapping("/hour/agentstat")
    public CommonResponse<String> agentHourStat(@RequestParam String day) {
        logger.info("execute agent stat on: {}", day);
        Long startTime = DateTimeUtil.getDayStartTime(day);
        if (startTime == null) {
            logger.warn("day format error");
            return new CommonResponse<>();
        }
        List<CompanyInfo> companyInfoList = companyService.getCompanyList(null);
        if (CollectionUtils.isEmpty(companyInfoList)) {
            return new CommonResponse<>();
        }

        Long now = Instant.now().toEpochMilli();
        Long endTime = startTime + 3600 * 24 * 1000L;

        while (startTime < endTime) {
            if (startTime > now - 3600 * 1000L) {
                break;
            }
            try {
                statWorkService.deleteAgentHourStat(startTime / 1000L + 3600L);
                taskJobOfHour.agentHourStat(startTime, startTime + 3600000, companyInfoList);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            startTime += 3600000;
        }
        return new CommonResponse<>();
    }
}
