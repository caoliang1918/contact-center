package org.zhongweixian.ivr.tcp;

import com.alibaba.fastjson.JSONObject;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.client.AuthorizationToken;
import org.zhongweixian.client.tcp.NettyClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/8/7
 */
@Component
public class TcpClientManager {
    private Logger logger = LoggerFactory.getLogger(TcpClientManager.class);

    @Value("${fs.api.list}")
    private List<String> hosts;


    @Autowired
    private Station station;

    private Map<String, NettyClient> nettyClientMap = new HashMap<>();

    @Autowired
    private TcpClientHandler tcpClientHandler;

    private void connect(String url, Integer port) {
        AuthorizationToken authorizationToken = new AuthorizationToken();
        authorizationToken.setPongTimeout(0);
        JSONObject payload = new JSONObject();
        payload.put("cmd", "login");
        payload.put("stationType", 3);
        payload.put("domain", Constants.HTTP + station.getHost());
        authorizationToken.setPayload(payload.toJSONString());
        NettyClient nettyClient = new NettyClient(url, port, authorizationToken, tcpClientHandler);
        nettyClientMap.put(url, nettyClient);
    }

    public void start() {
        for (String url : hosts) {
            connect(url.split(":")[0], Integer.parseInt(url.split(":")[1]));
        }
    }


    public void stop() {

    }
}
