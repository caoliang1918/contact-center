package org.zhongweixian.cc.tcp;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.Handler;
import org.zhongweixian.cc.configration.HandlerContext;
import org.zhongweixian.cc.tcp.event.base.SubBaseEvent;
import org.zhongweixian.cc.util.Json;
import org.zhongweixian.cc.websocket.event.base.ChannelEntity;
import org.zhongweixian.cc.EventType;
import org.zhongweixian.listener.ConnectionListener;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Create by caoliang on 2020/9/20
 */
@Component
public class TcpServerHandler implements ConnectionListener {
    private Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);

    @Autowired
    private HandlerContext handlerContext;

    @Value("${socket.timeout:2}")
    private Long timeout;

    private Map<ChannelId , ChannelEntity> channleIds = new HashMap<>();

    /**
     * 为避免恶意链接，建立链接不发送login，需要定时检测
     */
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("check-tcp-login-pool-%d").build());


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("tcp-server-pool-%d").build());

    @Override
    public void onClose(Channel channel, int closeCode, String reason) {
        logger.warn("tcp channle on close  {}", channel);
        channleIds.remove(channel.id());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onFail(int status, String reason) {

    }

    @Override
    public void onMessage(Channel channel, String text) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(text);
        if (jsonObject == null || !jsonObject.containsKey("cmd")) {
            return;
        }
        Handler handler = handlerContext.getInstance("SUB_" + jsonObject.getString("cmd").toUpperCase());
        if (handler == null) {
            logger.warn("channel:{} get handler error , text:{}", channel.id(), text);
            return;
        }
        SubBaseEvent event = formatEvent(jsonObject, channel, jsonObject.getString("cmd").toUpperCase());
        if (event == null) {
            return;
        }
        executor.execute(() -> {
            try {
                logger.info("tcp channel:{} message:{}", channel.id(), text);
                handler.handleEvent(event);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void onMessage(Channel channel, ByteBuf byteBuf) throws Exception {

    }

    @Override
    public void connect(Channel channel) throws Exception {
        logger.info("channel:{} is connected", channel);
    }

    private SubBaseEvent formatEvent(JSONObject jsonObject, Channel channel, String cmd) {
        try {

            JsonNode json = Json.toJson(jsonObject);
            Class clzss = EventType.getClassByCmd("SUB_" + cmd);
            if (clzss == null) {
                return null;
            }
            SubBaseEvent event = (SubBaseEvent) Json.fromJson(json, clzss);
            event.setChannel(channel);
            return event;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     *
     */
    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    /**
     * 检测长时间没有发生login的channel
     */
    public void check() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (CollectionUtils.isEmpty(channleIds)) {
                return;
            }
            Iterator<Map.Entry<ChannelId, ChannelEntity>> iterator = channleIds.entrySet().iterator();
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
        }, 10, 2, TimeUnit.SECONDS);
    }
}
