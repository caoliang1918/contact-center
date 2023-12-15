package org.voice9.cc.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.po.AgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.voice9.cc.EventType;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.configration.HandlerContext;
import org.voice9.cc.service.AgentService;
import org.voice9.cc.util.RandomUtil;
import org.voice9.cc.websocket.event.WsLoginEvnet;
import org.voice9.cc.websocket.event.WsLogoutEvent;
import org.voice9.cc.websocket.event.base.ChannelEntity;
import org.voice9.cc.websocket.event.base.WsBaseEvent;
import org.voice9.cc.websocket.handler.WsLogoutHandler;
import org.zhongweixian.listener.ConnectionListener;

import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Create by caoliang on 2020/9/20
 */
@Component
public class WebSocketHandler implements ConnectionListener {
    private Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    /**
     * 从open socket 到发送登录信息的超时时间
     */
    @Value("${ws.login.timeout:2}")
    private Long timeout;

    @Value("${ws.thread.num:16}")
    private Integer threadNum;

    @Autowired
    private AgentService agentService;

    /**
     * 坐席状态http回调，支持超时配置
     */
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private HandlerContext handlerContext;

    @Autowired
    private WsLogoutHandler wsLogoutHandler;

    @Autowired
    private CacheService cacheService;


    /**
     * 线程组
     */
    private Map<Integer, ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();


    /**
     * 为避免恶意链接，建立链接不发送login，需要定时检测
     */
    private ScheduledExecutorService checkThread = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("check-ws-login-pool-%d").build());

    /**
     * channelId - agentKey
     */
    private Map<ChannelId, ChannelEntity> channelIds = new ConcurrentHashMap<>();

    /**
     * agentKey-Channel
     */
    private Map<String, Channel> agentChannel = new ConcurrentHashMap<>();

    /**
     * 构造函数中初始化 restTemplate
     */
    public WebSocketHandler(@Value("${ws.thread.num:32}") Integer threadNum) {
        for (int i = 0; i < threadNum; i++) {
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ws-server-pool-" + i).build();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
            executorMap.put(i, executor);
        }


    }

    @Override
    public void onClose(Channel channel, int i, String s) {
        logger.warn("ws channel on close  {}", channel);
        ChannelEntity channelEntity = channelIds.remove(channel.id());
        if (channelEntity == null || StringUtils.isBlank(channelEntity.getClient())) {
            return;
        }
        Channel cha = agentChannel.get(channelEntity.getClient());
        if (cha != null && !cha.id().equals(channel.id())) {
            return;
        }
        Channel channel1 = agentChannel.remove(channelEntity.getClient());
        if (channel1 == null) {
            return;
        }
        WsLogoutEvent evnet = new WsLogoutEvent();
        evnet.setAgentKey(channelEntity.getClient());
        wsLogoutHandler.handleEvent(evnet);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error(throwable.getMessage());
    }

    @Override
    public void onFail(int i, String s) {
        logger.error("code:{} , message:{}", i, s);
    }

    @Override
    public void onMessage(Channel channel, String text) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(text);
        if (jsonObject == null || !jsonObject.containsKey("cmd")) {
            return;
        }
        String cmd = "WS_" + jsonObject.getString("cmd").toUpperCase();
        Handler handler = handlerContext.getInstance(cmd);
        if (handler == null) {
            logger.warn("channel:{} get handler error , text:{}", channel.id(), text);
            return;
        }
        WsBaseEvent event = formatEvent(jsonObject, channel, cmd);
        if (event == null) {
            return;
        }


        ExecutorService executorService = executorMap.get(RandomUtil.getNum(event.getAgentKey(), threadNum));
         executorService.execute(() -> {
            try {
                logger.info("websocket received channel:{} message:{}", channel.id(), text);
                handler.handleEvent(event);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void onMessage(Channel channel, ByteBuf byteBuf) throws Exception {

    }

    @Override
    public void connect(Channel channel) throws Exception {
       /* logger.info("channel:{} is connected", channel);
        ChannelEntity entity = new ChannelEntity();
        entity.setChannel(channel);
        entity.setCts(Instant.now().getEpochSecond());
        channelIds.put(channel.id(), entity);*/
    }

    @Override
    public void connect(Channel channel, Map<String, Object> map) throws Exception {
        logger.info("channel:{} connect success, params:{}", channel, JSON.toJSONString(map));
        if (!map.containsKey("token")) {
            channel.close();
            return;
        }
        String token = (String) map.get("token");
        Object agentKey = cacheService.getAgentKey(token);
        if (agentKey == null) {
            logger.error("channel:{} get agentKey is null", channel);
            channel.close();
            return;
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(String.valueOf(agentKey));
        if (agentInfo == null) {
            logger.error("channel:{} get agentInfo is null", channel);
            channel.close();
            return;
        }
        ChannelEntity entity = new ChannelEntity();
        entity.setClient(agentInfo.getAgentKey());
        entity.setChannel(channel);
        entity.setAuthorization(true);
        entity.setCts(Instant.now().getEpochSecond());
        channelIds.put(channel.id(), entity);
        logger.info("agent:{}  auth success", agentInfo.getAgentKey());
    }

    private WsBaseEvent formatEvent(JSONObject jsonObject, Channel channel, String cmd) {
        try {
            Class clzss = EventType.getClassByCmd(cmd);
            if (clzss == null) {
                return null;
            }
            WsBaseEvent event = (WsBaseEvent) JSON.toJavaObject(jsonObject, clzss);
            event.setChannel(channel);
            event.setAgentKey(channelIds.get(channel.id()).getClient());
            if (event instanceof WsLoginEvnet == false) {
                if (cacheService.getAgentInfo(event.getAgentKey()) == null) {
                    return null;
                }
            }
            return event;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 检测长时间没有发送login的channel
     */
    public void check() {
        checkThread.scheduleAtFixedRate(() -> {
            Iterator<Map.Entry<ChannelId, ChannelEntity>> iterator = channelIds.entrySet().iterator();
            Long now = Instant.now().getEpochSecond();
            try {
                while (iterator.hasNext()) {
                    ChannelEntity channelEntity = iterator.next().getValue();
                    if (channelEntity.isAuthorization()) {
                        continue;
                    }
                    if (now - channelEntity.getCts() > timeout) {
                        logger.warn("channel {} not send authorization", channelEntity.getChannel().id());
                        channelEntity.getChannel().close();
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }, 10, 5, TimeUnit.SECONDS);
    }

    /**
     * 关闭线程
     */
    public void stop() {
        if (checkThread != null) {
            checkThread.shutdown();
        }
        executorMap.forEach((i, executor) -> {
            executor.shutdown();
        });


    }

    /**
     * @param channel
     * @return
     */
    public ChannelEntity getChanel(ChannelId channel) {
        return channelIds.get(channel);
    }

    /**
     * @param agentKey
     * @return
     */
    public Channel getChannel(String agentKey) {
        return agentChannel.get(agentKey);
    }

    public void addAgentChannel(String agentKey, Channel channel) {
        agentChannel.put(agentKey, channel);
    }


    public void removeAgentChannel(String agentKey) {
        Channel channel = agentChannel.remove(agentKey);
        if (channel != null) {
            channelIds.remove(channel.id());
        }
    }

    /**
     * 主动关闭坐席
     *
     * @param agentKey
     */
    public void close(String agentKey) {
        Channel channel = agentChannel.get(agentKey);
        if (channel != null && channel.isOpen()) {
            logger.info("close agent:{} ws", agentKey);
            channel.close();
        }
    }

    /**
     * 发送坐席消息
     *
     * @param agentInfo
     * @param payload
     */
    public void sendMessgae(AgentInfo agentInfo, String payload) {
        agentService.syncAgentStateMessage(agentInfo);
        Channel channel = agentChannel.get(agentInfo.getAgentKey());
        if (channel != null && channel.isActive()) {
            logger.info("send agent:{} ws message:{}", agentInfo.getAgentKey(), payload);
            channel.writeAndFlush(new TextWebSocketFrame(payload));
            return;
        }
        if (!StringUtils.isBlank(agentInfo.getWebHook())) {
            logger.info("send agent:{} http message:{}", agentInfo.getAgentKey(), payload);
            try {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> requestEntity = new HttpEntity<String>(payload, httpHeaders);
                String response = restTemplate.postForEntity(agentInfo.getWebHook(), requestEntity, String.class).getBody();
                logger.info("send agent:{} http message success, response:{}", agentInfo.getAgentKey(), response);
            } catch (Exception e) {
                logger.error("send agent:{} http message", agentInfo.getAgentKey());
            }
        }
    }

    /**
     * 只发送ws信息
     *
     * @param agentInfo
     * @param payload
     */
    public void sentWsMessage(AgentInfo agentInfo, String payload) {
        Channel channel = agentChannel.get(agentInfo.getAgentKey());
        if (channel != null && channel.isActive()) {
            logger.info("send agent:{} ws message:{}", agentInfo.getAgentKey(), payload);
            channel.writeAndFlush(new TextWebSocketFrame(payload));
            return;
        }
    }
}
