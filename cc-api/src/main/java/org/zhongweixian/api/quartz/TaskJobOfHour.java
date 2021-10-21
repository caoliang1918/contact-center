package org.zhongweixian.api.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.cti.cc.entity.*;
import org.cti.cc.mapper.AgentMapper;
import org.cti.cc.mapper.CompanyMapper;
import org.cti.cc.mapper.PushFailLogMapper;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CompanyInfo;
import org.cti.cc.util.DateTimeUtil;
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
import org.zhongweixian.api.service.StatWorkService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by caoliang on 2021/9/3
 */
@Component
public class TaskJobOfHour implements Job {
    private Logger logger = LoggerFactory.getLogger(TaskJobOfHour.class);

    public final static String CRON = "0 0 0/1 * * ?";

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private StatWorkService agentStateWorkService;

    @Autowired
    private PushFailLogMapper pushFailLogMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Instant instant = Instant.now();
        Long start = instant.toEpochMilli() - 3600 * 1000;
        Long end = instant.toEpochMilli();
        List<CompanyInfo> companyInfoList = companyMapper.selectCompanyInfoList(null);
        if (CollectionUtils.isEmpty(companyInfoList)) {
            return;
        }
        logger.info("Hour job execute start:{} - end:{}", DateFormatUtils.format(start, DateTimeUtil.YYYYMMDD_HHMMSS), DateFormatUtils.format(end, DateTimeUtil.YYYYMMDD_HHMMSS));

        agentHourStat(start, end, companyInfoList);

        pushFailLog(start, end);
    }

    /**
     * 坐席工作报表统计
     *
     * @param start 开始时间
     * @param end   结束时间
     */
    public void agentHourStat(Long start, Long end, List<CompanyInfo> companyInfoList) {
        for (CompanyInfo companyInfo : companyInfoList) {
            if (CollectionUtils.isEmpty(companyInfo.getGroupIds()) || CollectionUtils.isEmpty(companyInfo.getCompanyStats())) {
                continue;
            }
            List<CompanyStat> companyStats = companyInfo.getCompanyStats();
            companyStats.forEach(companyStat -> {
                if (companyStat.getType() == 0) {
                    return;
                }
                //如果当前坐席是属于登录在线，但是这段时间的历史表中无记录，则取最近一次状态
                Map<String, Object> params = new HashMap<>();
                params.put("companyId", companyInfo.getId());
                params.put("start", start);
                params.put("end", end);
                List<AgentStateLog> agentStateLogs = agentStateWorkService.getListByMap(params);
                Map<Long, List<AgentStateLog>> listMap = agentStateLogs.stream().collect(Collectors.groupingBy(stateLog -> stateLog.getAgentId()));
                Long login = 0L, ready = 0L, notReady = 0L, busy = 0L, after = 0L, talk = 0L;
                List<StatHourAgentWork> agentWorkList = new ArrayList<>();
                StatHourAgentWork agentWork = null;
                for (Map.Entry<Long, List<AgentStateLog>> entry : listMap.entrySet()) {
                    List<AgentStateLog> stateLogs = entry.getValue();
                    agentWork = new StatHourAgentWork();
                    agentWork.setCompanyId(companyInfo.getId());
                    agentWork.setStateTime(start);
                    agentWork.setCts(end);
                    agentWork.setStatus(1);

                    for (int i = 0; i < stateLogs.size(); i++) {
                        AgentStateLog stateLog = stateLogs.get(i);
                        boolean last = stateLogs.size() == i + 1;

                        //login 通过做减法，去除退出系统时间
                        if (stateLog.getState().equals(AgentState.LOGIN.name())) {
                            if (i == 0) {
                                login += 3600000L - (start - stateLog.getStateTime());
                            } else if (stateLog.getBeforeState() != null) {
                                login += 3600000L - (stateLog.getStateTime() - stateLog.getBeforeTime());
                            }
                        }
                        //在当前小时内，最后一次logout才需要计算进去
                        if (last && stateLog.getState().equals(AgentState.LOGOUT.name())) {
                            login = 3600000L - (end - stateLog.getStateTime());
                        }

                        if (stateLog.getBeforeState().equals(AgentState.READY.name())) {
                            ready += stateLog.getStateTime() - stateLog.getBeforeTime();
                        }
                        if (last && stateLog.getState().equals(AgentState.READY.name())) {
                            ready += end - stateLog.getStateTime();
                        }

                        if (stateLog.getBeforeState().equals(AgentState.NOT_READY.name())) {
                            notReady += stateLog.getStateTime() - stateLog.getBeforeTime();
                        }
                        if (last && stateLog.getState().equals(AgentState.NOT_READY.name())) {
                            notReady += end - stateLog.getStateTime();
                        }

                        if (stateLog.getBeforeState().equals(AgentState.BUSY_OTHER.name())) {
                            busy += stateLog.getStateTime() - stateLog.getBeforeTime();
                        }
                        if (last && stateLog.getState().equals(AgentState.BUSY_OTHER.name())) {
                            busy += end - stateLog.getStateTime();
                        }

                        if (stateLog.getBeforeState().equals(AgentState.AFTER.name())) {
                            after += stateLog.getStateTime() - stateLog.getBeforeTime();
                        }
                        if (last && stateLog.getState().equals(AgentState.AFTER.name())) {
                            after += end - stateLog.getStateTime();
                        }

                        if (stateLog.getBeforeState().equals(AgentState.TALKING.name())) {
                            talk += stateLog.getStateTime() - stateLog.getBeforeTime();
                        }
                        if (last && stateLog.getState().equals(AgentState.TALKING.name())) {
                            talk += end - stateLog.getStateTime();
                        }
                        agentWork.setAgentKey(stateLog.getAgentKey());
                        agentWork.setAgentName(stateLog.getAgentName());
                    }
                    agentWork.setLoginTime(login);
                    agentWork.setReadyTime(ready);
                    agentWork.setNotReadyTime(notReady);
                    agentWork.setBusyTime(busy);
                    agentWork.setAfterTime(after);
                    agentWork.setTalkTime(talk);
                    agentWorkList.add(agentWork);
                }
                //查询最近一小时没有变更状态但是在线的坐席
                List<Agent> agents = agentMapper.selectAgentOnline(params);
                for (Agent agent : agents) {
                    if (listMap.containsKey(agent.getId())) {
                        continue;
                    }
                    agentWork = new StatHourAgentWork();
                    agentWork.setCompanyId(companyInfo.getId());
                    agentWork.setAgentKey(agent.getAgentKey());
                    agentWork.setAgentName(agent.getAgentName());
                    agentWork.setStateTime(start);
                    agentWork.setCts(end / 1000);
                    agentWork.setStatus(1);
                    if (AgentState.READY.name().equals(agent.getExt1())) {
                        agentWork.setReadyTime(3600000L);
                    }
                    if (AgentState.NOT_READY.name().equals(agent.getExt1())) {
                        agentWork.setNotReadyTime(3600000L);
                    }
                    if (AgentState.BUSY_OTHER.name().equals(agent.getExt1())) {
                        agentWork.setBusyTime(3600000L);
                    }
                    if (AgentState.AFTER.name().equals(agent.getExt1())) {
                        agentWork.setAfterTime(3600000L);
                    }
                    if (AgentState.TALKING.name().equals(agent.getExt1())) {
                        agentWork.setTalkTime(3600000L);
                    }
                    agentWorkList.add(agentWork);
                }
                if (!CollectionUtils.isEmpty(agentWorkList)) {
                    agentStateWorkService.saveStateHoutAgentWork(agentWorkList);
                }
            });
        }
    }


    /**
     * 每小时重推最近1000条数据
     *
     * @param start
     * @param end
     */
    public void pushFailLog(Long start, Long end) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start / 1000);
        params.put("end", end / 1000);
        params.put("status", 1);
        List<PushFailLog> pushFailLogs = pushFailLogMapper.selectListByMap(params);
        if (CollectionUtils.isEmpty(pushFailLogs)) {
            return;
        }
        for (PushFailLog pushFailLog : pushFailLogs) {
            try {
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(pushFailLog.getCdrNotifyUrl(), pushFailLog.getContent(), String.class);
                if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
                    pushFailLog.setPushResponse(responseEntity.getBody());
                    pushFailLog.setStatus(2);
                    pushFailLog.setCts(Instant.now().getEpochSecond());
                    pushFailLogMapper.pushSuccess(pushFailLog);
                    logger.info("push call:{} to {} success:{}", pushFailLog.getCallId(), pushFailLog.getCdrNotifyUrl(), responseEntity.getBody());
                }
            } catch (Exception e) {
                PushFailLog failLog = new PushFailLog();
                failLog.setId(pushFailLog.getId());
                failLog.setPushTimes(pushFailLog.getPushTimes() + 1);
                failLog.setUts(end / 1000 + 3600 * calculate(pushFailLog.getPushTimes()));
                pushFailLogMapper.updateByPrimaryKey(failLog);
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
