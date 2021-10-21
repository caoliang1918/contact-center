package org.zhongweixian.cc.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.Playback;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.entity.Station;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.mapper.PlaybackMapper;
import org.cti.cc.mapper.RouteCallMapper;
import org.cti.cc.mapper.VdnPhoneMapper;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.service.CompanyService;
import org.zhongweixian.cc.service.GroupService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by caoliang on 2020/8/31
 */
@Service
public class CacheService {
    private Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupHandler groupHandler;

    @Autowired
    private RouteCallMapper routeCallMapper;

    @Autowired
    private VdnPhoneMapper vdnPhoneMapper;

    @Autowired
    private PlaybackMapper playbackMapper;

    @Value("${spring.application.id}")
    private String appId;

    @Value("${spring.instance.id}")
    private String instanceId;

    @Value("${spring.cloud.nacos.server-addr}")
    private String nacosAddr;

    @Autowired
    private RedisTemplate redisTemplate;

    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("nacos=service-check").build());

    private boolean master;

    private NamingService namingService;

    private String applicationName = "fs-api";

    /**
     * callInfo
     */
    protected Map<Long, CallInfo> callInfoMap = new ConcurrentHashMap<>();

    /**
     * callId 与device 映射
     */
    private Map<String, Long> deviceCall = new HashMap<>();

    /**
     * AgentInfo
     */
    private Map<String, AgentInfo> agentInfoMap = new HashMap<>();

    /**
     * Company
     */
    private Map<Long, CompanyInfo> companyMap = new HashMap<>();

    /**
     * 技能组
     */
    private Map<Long, GroupInfo> groupMap = new HashMap<>();

    /**
     * 放音文件
     */
    private Map<Long, Playback> playbackMap = new HashMap<>();

    /**
     * 外呼字冠路由
     */
    private List<RouteCallInfo> routeCallList = null;

    /**
     * 呼入被叫号码
     */
    private Map<String, VdnPhone> vdnPhoneMap = null;

    private Map<Integer, List<Station>> stationMap;

    /**
     * 获取本地缓存坐席
     *
     * @param agentkey
     * @return
     */
    public AgentInfo getAgentInfo(String agentkey) {
        if (StringUtils.isBlank(agentkey)) {
            return null;
        }
        return agentInfoMap.get(agentkey);
    }

    public void addAgentInfo(AgentInfo agentInfo) {
        agentInfoMap.put(agentInfo.getAgentKey(), agentInfo);
    }


    public void addCallInfo(CallInfo callInfo) {
        callInfoMap.put(callInfo.getCallId(), callInfo);
        redisTemplate.opsForValue().set("callInfo:" + callInfo.getCallId(), JSON.toJSONString(callInfo));
    }

    public CallInfo getCallInfo(String deviceId) {
        Long callId = deviceCall.get(deviceId);
        if (callId == null) {
            return null;
        }
        return callInfoMap.get(callId);
    }

    public CallInfo getCallInfo(Long callId) {
        if (callId == null) {
            return null;
        }
        return callInfoMap.get(callId);
    }

    public void removeCallInfo(Long callId) {
        CallInfo callInfo = callInfoMap.remove(callId);
        if (callInfo != null) {
            logger.info("remove callInfo:{}", callId);
            callInfo.getDeviceInfoMap().forEach((k, v) -> {
                deviceCall.remove(k);
            });
        }
        redisTemplate.delete("callInfo:" + callId);
        groupHandler.removeCall(callInfo.getGroupId(), callId);
    }


    public void addDevice(String device, Long callId) {
        deviceCall.put(device, callId);
    }

    public GroupInfo getGroupInfo(Long groupId) {
        return groupMap.get(groupId);
    }

    public CompanyInfo getCompany(Long companyId) {
        return companyMap.get(companyId);
    }


    public void initCompany() {
        try {
            namingService = NamingFactory.createNamingService(nacosAddr);
            List<Instance> instances = namingService.getAllInstances(applicationName);
            instances.forEach(instance -> {
                if (appId.equals(instance.getMetadata().get("appId")) && !instanceId.equals(instance.getMetadata().get("random"))) {
                    logger.error("spring.application.id:{} is exist", appId);
                    System.exit(-1);
                    return;
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        executorService.scheduleAtFixedRate(() -> {
            try {
                List<Instance> instances = namingService.getAllInstances(applicationName);
                if (!CollectionUtils.isEmpty(instances)) {
                    if (appId.equals(instances.get(0).getMetadata().get("appId"))) {
                        setMaster(true);
                    } else {
                        setMaster(false);
                    }
                }
            } catch (Exception e) {

            }

        }, 2, 2, TimeUnit.SECONDS);


        this.companyMap = companyService.initAll();
        if (companyMap.isEmpty()) {
            return;
        }

        /**
         * 查询所以子冠，按照每个子冠所属网关组分
         */
        routeCallList = routeCallMapper.selectListByMap(new HashMap());

        /**
         * 初始化企业数据
         */
        companyMap.forEach((k, v) -> {
            List<GroupInfo> groupInfoList = groupService.getGroupByConpany(k);
            if (!groupInfoList.isEmpty()) {
                groupInfoList.forEach(groupInfo -> {
                    groupService.initGroupStrategy(groupInfo);
                    groupMap.put(groupInfo.getId(), groupInfo);
                });
            }
            //初始化企业字冠路由信息
            Map<String, RouteGroupPo> routeGroupMap = new LinkedHashMap<>();
            for (RouteCallInfo routeCallInfo : routeCallList) {
                if (routeCallInfo.getCompanyId() == 0 || routeCallInfo.getCompanyId().equals(k)) {
                    routeGroupMap.put(routeCallInfo.getRouteNum(), routeCallInfo.getRouteGroupPo());
                }
            }
            v.setRouteGroupMap(routeGroupMap);
            //vdn
            companyService.initVdn(v);
        });
        Map<String, Object> vdnParams = new HashMap<>();
        vdnParams.put("status", 1);

        Map<String, Object> playbackParams = new HashMap<>();
        playbackParams.put("status", 2);
        List<Playback> playbacks = playbackMapper.selectListByMap(playbackParams);
        if (!CollectionUtils.isEmpty(playbacks)) {
            playbackMap.putAll(playbacks.stream().collect(Collectors.toMap(Playback::getId, Function.identity())));
        }

        /**
         * 呼入特服号
         */
        vdnPhoneMap = vdnPhoneMapper.selectListByMap(vdnParams).stream().collect(Collectors.toMap(VdnPhone::getPhone, phone -> phone));
    }

    public RouteGetway getRouteGetway(Long companyId, String called) {
        CompanyInfo companyInfo = companyMap.get(companyId);
        RouteGroupPo routeGroup = null;
        //先匹配最长的。
        for (String route : companyInfo.getRouteGroupMap().keySet()) {
            if (called.contains(route)) {
                routeGroup = companyInfo.getRouteGroupMap().get(route);
                break;
            }
        }
        if (routeGroup == null || CollectionUtils.isEmpty(routeGroup.getRouteGetways())) {
            return null;
        }
        //根据RouteGroup的规则判断
        Integer index = 0;
        return routeGroup.getRouteGetways().get(index);
    }

    public VdnPhone getVdnPhone(String phone) {
        return vdnPhoneMap.get(phone);
    }

    public Playback getPlayback(Long id) {
        return playbackMap.get(id);
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public void stop() {
        executorService.shutdown();
    }
}
