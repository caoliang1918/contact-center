package org.zhongweixian.ivr.tcp;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.Station;
import org.cti.cc.enums.StationType;
import org.cti.cc.mapper.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.client.AuthorizationToken;
import org.zhongweixian.client.tcp.NettyClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caoliang on 2021/8/7
 */
@Component
public class TcpClientManager {
    private Logger logger = LoggerFactory.getLogger(TcpClientManager.class);

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;

    @Autowired
    private Station station;

    @Autowired
    private StationMapper stationMapper;

    private Map<String, NettyClient> nettyClientMap = new HashMap<>();

    private Map<Integer, ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();


    @Autowired
    private TcpClientHandler tcpClientHandler;

    /**
     * 建立socket连接
     *
     * @param station
     */
    private void connect(Station station) {
        AuthorizationToken authorizationToken = new AuthorizationToken();
        authorizationToken.setPongTimeout(0);
        JSONObject payload = new JSONObject();
        payload.put("cmd", "login");
        payload.put("stationType", 3);
        payload.put("domain", Constants.HTTP + station.getHost());
        authorizationToken.setPayload(payload.toJSONString());
        NettyClient nettyClient = new NettyClient(station.getApplicationHost(),  7250, authorizationToken, tcpClientHandler);
        nettyClient.setMaxReConnect(2);
        nettyClientMap.put(station.getHost(), nettyClient);
    }

    public void start() {
        NamingService namingService = null;
        try {
            namingService = NamingFactory.createNamingService(serverAddr);
            List<Instance> instances = namingService.getAllInstances("fs-api");
            if (!CollectionUtils.isEmpty(instances)) {
                for (Instance instance : instances) {
                    Station fsApi = stationMapper.selectByAppId(Integer.valueOf(instance.getMetadata().get("appId")));
                    if (fsApi != null && fsApi.getApplicationGroup().equals(station.getApplicationGroup())) {
                        connect(fsApi);
                    }
                }
            }
            namingService.subscribe("fs-api", new EventListener() {
                @Override
                public void onEvent(Event event) {
                    logger.info(" ============== {}", ((NamingEvent) event).getServiceName());
                    logger.info("============== = {}", ((NamingEvent) event).getInstances());
                }
            });
        } catch (NacosException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 停止线程
     */
    public void stop() {
        if (CollectionUtils.isEmpty(nettyClientMap)) {
            return;
        }
        nettyClientMap.forEach((k, v) -> {
            v.close();
        });
    }
}
