package org.voice9.cc.tcp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.voice9.cc.tcp.event.base.SubBaseEvent;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.configration.HandlerContext;
import org.voice9.cc.tcp.event.SubLoginEvent;
import org.voice9.cc.websocket.event.base.ChannelEntity;
import org.voice9.cc.EventType;
import org.zhongweixian.listener.ConnectionListener;

import java.time.Instant;
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

    private Map<ChannelId, ChannelEntity> channelIds = new ConcurrentHashMap<>();

    /**
     * 为避免恶意链接，建立链接不发送login，需要定时检测
     */
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("check-tcp-login-pool-%d").build());


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("tcp-server-pool-%d").build());

    @Override
    public void onClose(Channel channel, int closeCode, String reason) {
        logger.warn("tcp channle on close  {}", channel);
        channelIds.remove(channel.id());
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
        String cmd = jsonObject.getString("cmd").toUpperCase();
        if (jsonObject == null || StringUtils.isBlank(cmd) || "PING".equals(cmd)) {
            return;
        }
        cmd = "SUB_" + cmd;
        Handler handler = handlerContext.getInstance(cmd);
        if (handler == null) {
            logger.warn("channel:{} get handler error , text:{}", channel.id(), text);
            return;
        }
        SubBaseEvent event = formatEvent(jsonObject, channel, cmd);
        if (event == null) {
            logger.warn("channel:{} get event error , text:{}", channel.id(), text);

            return;
        }
        if (event instanceof SubLoginEvent) {
            ChannelEntity channelEntity = channelIds.get(channel.id());
            if (channelEntity == null) {
                channel.close();
                return;
            }
            channelEntity.setCts(Instant.now().getEpochSecond());
            channelEntity.setAuthorization(true);
            channelEntity.setClient(event.getDomain());
            channelIds.put(channel.id(), channelEntity);
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
        ChannelEntity entity = new ChannelEntity();
        entity.setChannel(channel);
        entity.setCts(Instant.now().getEpochSecond());
        channelIds.put(channel.id(), entity);
    }

    @Override
    public void connect(Channel channel, Map<String, Object> map) throws Exception {
        logger.info("channel:{} connect success, params:{}", JSON.toJSONString(map));
    }

    private SubBaseEvent formatEvent(JSONObject jsonObject, Channel channel, String cmd) {
        try {
            Class clzss = EventType.getClassByCmd(cmd);
            if (clzss == null) {
                return null;
            }
            SubBaseEvent event = (SubBaseEvent) JSON.toJavaObject(jsonObject, clzss);
            if (clzss == null) {
                return null;
            }
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
            if (CollectionUtils.isEmpty(channelIds)) {
                return;
            }
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
        }, 10, 2, TimeUnit.SECONDS);
    }
}
