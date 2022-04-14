package org.zhongweixian.ivr.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.scxml2.model.SCXML;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.mapper.PlaybackMapper;
import org.cti.cc.mapper.RouteCallMapper;
import org.cti.cc.mapper.VdnPhoneMapper;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.ivr.service.CompanyService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    private RouteCallMapper routeCallMapper;

    @Autowired
    private VdnPhoneMapper vdnPhoneMapper;

    @Autowired
    private PlaybackMapper playbackMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
     *
     */
    private Map<String, SCXML> scxmlMap = new HashMap<>();

    /**
     * 外呼字冠路由
     */
    private List<RouteCallInfo> routeCallList = null;

    /**
     * 呼入被叫号码
     */
    private Map<String, VdnPhone> vdnPhoneMap = null;

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

        /**
         * 呼入特服号
         */
        vdnPhoneMap = vdnPhoneMapper.selectListByMap(vdnParams).stream().collect(Collectors.toMap(VdnPhone::getPhone, phone -> phone));

    }

    /**
     * 获取号码路由字冠
     *
     * @param companyId
     * @param called
     * @return
     */
    public String getOrigin(Long companyId, String called) {
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
        return routeGroup.getRouteGetways().get(index).getMediaHost() + ":" + routeGroup.getRouteGetways().get(index).getMediaPort();
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


}
