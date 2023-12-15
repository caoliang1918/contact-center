package org.voice9.cc.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.voice9.core.po.*;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.Playback;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.entity.Station;
import com.voice9.core.entity.VdnPhone;
import com.voice9.core.mapper.AgentMapper;
import com.voice9.core.mapper.PlaybackMapper;
import com.voice9.core.mapper.RouteCallMapper;
import com.voice9.core.mapper.VdnPhoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.voice9.cc.service.CompanyService;
import org.voice9.cc.service.GroupService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    @Value("${token.cache.day:7}")
    private Integer cacheDay;

    @Value("${spring.datasource.url:}")
    private String salt;

    @Value("${platform.v9.key:pykqu7qfhcs5gz87}")
    private String key;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RouteCallMapper routeCallMapper;

    @Autowired
    private VdnPhoneMapper vdnPhoneMapper;

    @Autowired
    private PlaybackMapper playbackMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("nacos=service-check").build());

    /**
     * callId 与device 映射
     */
    private Map<String, Long> deviceCall = new HashMap<>();

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
     * @param agentKey
     * @return
     */
    public AgentInfo getAgentInfo(String agentKey) {
        if (StringUtils.isBlank(agentKey)) {
            return null;
        }
        Object payload = redisTemplate.opsForValue().get(Constant.AGENT_INFO + agentKey);
        if (payload == null) {
            logger.warn("get agentKey:{} is null", agentKey);
            return null;
        }
        AgentInfo agentInfo = JSON.parseObject(payload.toString(), AgentInfo.class);
        if (agentInfo == null) {
            return null;
        }
        return agentInfo;
    }

    public void refleshAgentToken(String agentKey, String token) {
        logger.info("agent:{}, create token:{}", agentKey, token);
        redisTemplate.opsForValue().set(Constant.AGENT_TOKEN + token, agentKey, 24 * cacheDay, TimeUnit.HOURS);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public Object getAgentKey(String token) {
        if (token == null) {
            return null;
        }
        return redisTemplate.opsForValue().get(Constant.AGENT_TOKEN + token);
    }

    /**
     * 缓存坐席
     *
     * @param agentInfo
     */
    public void addAgentInfo(AgentInfo agentInfo) {
        redisTemplate.opsForValue().set(Constant.AGENT_INFO + agentInfo.getAgentKey(), JSON.toJSONString(agentInfo), 24 * cacheDay, TimeUnit.HOURS);
    }


    /**
     * 缓存CALL_INFO
     *
     * @param callInfo
     */
    public void addCallInfo(CallInfo callInfo) {
        redisTemplate.opsForValue().set(Constant.CALL_INFO + callInfo.getCallId(), JSONObject.toJSONString(callInfo));
    }

    public CallInfo getCallInfo(String deviceId) {
        Long callId = deviceCall.get(deviceId);
        if (callId == null) {
            return null;
        }
        return getCallInfo(callId);
    }

    /**
     * 获取callInfo
     *
     * @param callId
     * @return
     */
    public CallInfo getCallInfo(Long callId) {
        if (callId == null) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(Constant.CALL_INFO + callId);
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(obj.toString(), CallInfo.class);
    }


    public void removeCallInfo(Long callId) {
        CallInfo callInfo = getCallInfo(callId);
        if (callInfo != null) {
            logger.info("remove callInfo:{}", callId);
            callInfo.getDeviceInfoMap().forEach((k, v) -> {
                deviceCall.remove(k);
            });
        }
        redisTemplate.delete(Constant.CALL_INFO + callId);
    }


    public void addDevice(String device, Long callId) {
        deviceCall.put(device, callId);
    }

    public GroupInfo getGroupInfo(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return groupMap.get(groupId);
    }

    public CompanyInfo getCompany(Long companyId) {
        return companyMap.get(companyId);
    }


    public void initCompany() {
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
            if (called.contains(route) || route.equals("*")) {
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

    public void stop() {
        executorService.shutdown();
    }
}
