package com.voice9.api.quartz;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import com.voice9.core.entity.Agent;
import com.voice9.core.entity.CompanyStat;
import com.voice9.core.entity.PushLog;
import com.voice9.core.entity.StatHourAgent;
import com.voice9.core.mapper.AgentMapper;
import com.voice9.core.mapper.CompanyMapper;
import com.voice9.core.mapper.PushLogMapper;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.util.DateTimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import com.voice9.api.service.StatWorkService;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/9/3
 */
@Component
public class TaskJobOfHour implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfHour.class);

    public final static String NAME = "TaskJobOfHour";
    public final static String CRON = "0 0 0/1 * * ?";

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private StatWorkService statWorkService;

    @Autowired
    private PushLogMapper pushLogMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Instant instant = Instant.now();
        //这里开始时间和结束时间都是毫秒
        Long start = instant.toEpochMilli() - 3600 * 1000;
        Long end = instant.toEpochMilli();
        logger.info("Hour job execute start:{} - end:{}", DateFormatUtils.format(start, DateTimeUtil.YYYYMMDD_HHMMSS), DateFormatUtils.format(end, DateTimeUtil.YYYYMMDD_HHMMSS));
        List<CompanyInfo> companyInfoList = companyMapper.selectCompanyInfoList(null);
        if (CollectionUtils.isEmpty(companyInfoList)) {
            return;
        }

        /**
         * 坐席状态统计
         */
        agentHourStat(start, end, companyInfoList);

        /**
         * 坐席电话统计
         */

        /**
         * 企业话务统计
         */


        /**
         * 定时话单补推
         */
        pushFailLog(start, end);
    }

    /**
     * 坐席工作报表统计
     *
     * @param start 开始时间(毫秒)
     * @param end   结束时间(毫秒)
     */
    public void agentHourStat(Long start, Long end, List<CompanyInfo> companyInfoList) {
        for (CompanyInfo companyInfo : companyInfoList) {
            if (CollectionUtils.isEmpty(companyInfo.getGroupIds()) || CollectionUtils.isEmpty(companyInfo.getCompanyStats())) {
                continue;
            }
            List<CompanyStat> companyStats = companyInfo.getCompanyStats();
            for (CompanyStat companyStat : companyStats) {
                //坐席报表
                if (companyStat.getType() != 1) {
                    continue;
                }
                //如果当前坐席是属于登录在线，但是这段时间的历史表中无记录，则取最近一次状态
                Map<String, Object> params = new HashMap<>();
                params.put("companyId", companyInfo.getId());
                params.put("start", start / 1000);
                params.put("end", end / 1000);
                List<StatHourAgent> statHourAgentList = statWorkService.statHour(params);

                //查询最近一小时没有变更状态但是在线的坐席
                List<Agent> agents = agentMapper.selectAgentLongOnline(params);
                StatHourAgent agentWork = null;
                for (Agent agent : agents) {
                    if (StringUtils.isBlank(agent.getExt1())) {
                        continue;
                    }
                    agentWork = new StatHourAgent();
                    agentWork.setAgentKey(agent.getAgentKey());
                    agentWork.setAgentName(agent.getAgentName());
                    if (AgentState.READY.name().equals(agent.getExt1())) {
                        agentWork.setReadyTime(3600L);
                    }
                    if (AgentState.NOT_READY.name().equals(agent.getExt1())) {
                        agentWork.setNotReadyTime(3600L);
                    }
                    if (AgentState.BUSY_OTHER.name().equals(agent.getExt1())) {
                        agentWork.setBusyTime(3600L);
                    }
                    if (AgentState.AFTER.name().equals(agent.getExt1())) {
                        agentWork.setAfterTime(3600L);
                    }
                    if (AgentState.TALKING.name().equals(agent.getExt1())) {
                        agentWork.setTalkTime(3600L);
                    }
                    statHourAgentList.add(agentWork);
                }
                if (!CollectionUtils.isEmpty(statHourAgentList)) {
                    statHourAgentList.forEach(statHourAgent -> {
                        statHourAgent.setCompanyId(companyInfo.getId());
                        statHourAgent.setStatTime(end / 1000);
                        statHourAgent.setCts(start / 1000);
                        statHourAgent.setStatus(1);
                    });
                    statWorkService.saveStateHoutAgent(statHourAgentList);
                }
            }
        }
    }


    /**
     * 每小时重推最近10000条数据
     *
     * @param start
     * @param end
     */
    public void pushFailLog(Long start, Long end) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start / 1000);
        params.put("end", end / 1000);
        params.put("status", 1);
        List<PushLog> pushFailLogs = pushLogMapper.selectListByMap(params);
        if (CollectionUtils.isEmpty(pushFailLogs)) {
            return;
        }
        for (PushLog pushFailLog : pushFailLogs) {
            try {
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(pushFailLog.getCdrNotifyUrl(), pushFailLog.getContent(), String.class);
                if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
                    pushFailLog.setPushResponse(responseEntity.getBody());
                    pushFailLog.setStatus(2);
                    pushFailLog.setCts(Instant.now().getEpochSecond());
                    pushLogMapper.pushSuccess(pushFailLog);
                    logger.info("push call:{} to {} success:{}", pushFailLog.getCallId(), pushFailLog.getCdrNotifyUrl(), responseEntity.getBody());
                }
            } catch (Exception e) {
                PushLog failLog = new PushLog();
                failLog.setId(pushFailLog.getId());
                failLog.setPushTimes(pushFailLog.getPushTimes() + 1);
                failLog.setUts(end / 1000 + 3600 * calculate(pushFailLog.getPushTimes()));
                pushLogMapper.updateByPrimaryKey(failLog);
            }
        }
    }

    /**
     * 1 次  9:30
     * 2 次  10:00   3600*4
     * 3 次  14:00   3600*8
     * 4 次  22:00   3600*16
     * 5 次  08:00(第二天)  3600*32
     *
     * @param n
     * @return
     */
    private static int calculate(int n) {
        if (n == 0) {
            return 1;
        }
        return 2 * calculate(n - 1);
    }
}
