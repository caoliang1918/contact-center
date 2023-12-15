package org.voice9.cc.fs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.Channel;
import io.netty.util.HashedWheelTimer;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.constant.FsConstant;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.entity.Station;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.StationMapper;
import com.voice9.core.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.fs.esl.internal.Context;
import org.voice9.cc.fs.esl.internal.IModEslApi;
import org.voice9.cc.fs.esl.transport.SendMsg;
import org.voice9.cc.fs.esl.transport.event.EslEvent;
import org.voice9.cc.fs.esl.transport.message.EslMessage;
import org.voice9.cc.fs.event.FsHangupCompleteEvent;
import org.voice9.cc.fs.event.base.FsBaseEvent;
import org.voice9.cc.util.RandomUtil;
import org.voice9.cc.EventType;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.configration.HandlerContext;
import org.voice9.cc.exception.BusinessException;
import org.voice9.cc.fs.esl.inbound.Client;
import org.voice9.cc.fs.esl.inbound.IEslEventListener;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Create by caoliang on 2020/8/23
 */
@Component
public class FsListen {
    private Logger logger = LoggerFactory.getLogger(FsListen.class);

    @Value("${fs.thread.num:64}")
    private Integer threadNum;

    @Value("${spring.application.group}")
    private String group;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    protected HashedWheelTimer hashedWheelTimer;

    @Value("${audio.codecs:^^:G729:PCMU:PCMA}")
    private String codecs;

    @Value("${server.port}")
    private Integer localPort;

    private String localAddress;


    private ScheduledExecutorService checkFsThread = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("check-freeswitch-pool-%d").build());

    /**
     * 媒体集合
     */
    private Map<String, Client> fsClient = new HashMap<>();

    /**
     * 线程组
     */
    private Map<Integer, ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();


    @Autowired
    private HandlerContext handlerContext;

    @Autowired
    private CacheService cacheService;


    public void start() {
        for (int i = 0; i < threadNum; i++) {
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("fs-pool-" + i).build();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
            executorMap.put(i, executor);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("applicationType", 4);
        params.put("applicationGroup", group);
        List<Station> fsStations = stationMapper.selectListByMap(params);
        for (Station station : fsStations) {
            if (station.getStatus() == 1) {
                connect(station.getApplicationHost(), station.getApplicationPort(), station.getPwd());
            }
        }

        checkFsThread.scheduleAtFixedRate(() -> {
            try {
                checkConnect();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }, 2, 1, TimeUnit.MINUTES);
    }

    /**
     *
     */
    private void checkConnect() {
        Map<String, Object> params = new HashMap<>();
        params.put("applicationType", 4);
        params.put("applicationGroup", group);
        List<Station> fsStations = stationMapper.selectListByMap(params);

        for (Station station : fsStations) {
            Client client = fsClient.get(station.getHost());
            try {
                if (station.getStatus() != 1 && client == null) {
                    continue;
                }

                if (station.getStatus() == 1 && client == null) {
                    connect(station.getApplicationHost(), station.getApplicationPort(), station.getPwd());
                    continue;
                }

                //断开连接，fs下线
                if (station.getStatus() != 1 && client != null) {
                    logger.info("fs :{} off line", station.getHost());
                    fsClient.remove(station.getHost());
                    client.close();
                    continue;
                }

                //失败重连
                if (!client.isActivate()) {
                    logger.info("freeswitch {}:{} is close", station.getApplicationHost(), station.getApplicationPort());
                    client.close();
                    connect(station.getApplicationHost(), station.getApplicationPort(), station.getPwd());
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 连接freeswitch
     *
     * @param host
     * @param port
     * @param password
     * @return
     */
    private void connect(String host, Integer port, String password) {
        Client client = new Client();
        try {
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            logger.info("Connecting to {} passwd:{}", socketAddress, password);
            client.connect(socketAddress, password, 3);
            if (localAddress == null) {
                InetSocketAddress address = (InetSocketAddress) client.getChannel().localAddress();
                localAddress = Constant.HTTP + address.getAddress().getHostAddress() + Constant.CO + localPort + Constant.SK + Constant.FS_API;
                //cacheService.setHost(localAddress);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return;
        }
        fsClient.put(host + ":" + port, client);
        client.setEventSubscriptions(IModEslApi.EventFormat.PLAIN, "all");
        IEslEventListener listener = new IEslEventListener() {
            @Override
            public void onEslEvent(Context ctx, EslEvent event) {
                String eventName = event.getEventName();
                /**
                 * https://freeswitch.org/confluence/display/FREESWITCH/Event+List
                 */
                switch (event.getEventName()) {
                    case FsConstant.RE_SCHEDULE:
                        return;
                    case FsConstant.API:
                        return;
                    case FsConstant.HEARTBEAT:
                        return;
                    case FsConstant.RECV_RTCP_MESSAGE:
                        return;
                    case FsConstant.CODEC:
                        return;
                    case FsConstant.DTMF_SEND:
                        return;
                    case FsConstant.RECV_INFO:
                        return;
                    case FsConstant.CHANNEL_STATE:
                        return;
                    case FsConstant.DEL_SCHEDULE:
                        return;
                    case FsConstant.CHANNEL_CREATE:
                        break;
                    case FsConstant.CHANNEL_ORIGINATE:
                        break;
                    case FsConstant.CHANNEL_PROGRESS:
                        break;
                    case FsConstant.PRESENCE_IN:
                        break;
                    case FsConstant.CHANNEL_CALLSTATE:
                        break;
                    case FsConstant.CALL_UPDATE:
                        break;
                    case FsConstant.DETECTED_SPEECH:
                        break;
                    case FsConstant.CHANNEL_EXECUTE:
                        break;
                    case FsConstant.CHANNEL_PARK:
                        break;
                    case FsConstant.CHANNEL_UNPARK:
                        break;
                    case FsConstant.PRIVATE_COMMAND:
                        break;
                    case FsConstant.CHANNEL_EXECUTE_COMPLETE:
                        break;
                    case FsConstant.CHANNEL_HANGUP:
                        break;
                    case FsConstant.CHANNEL_HANGUP_COMPLETE:
                        break;
                    case FsConstant.CHANNEL_OUTGOING:
                        break;
                    case FsConstant.CHANNEL_ANSWER:
                        break;
                    case FsConstant.CHANNEL_DESTROY:
                        break;
                    case FsConstant.CHANNEL_BRIDGE:
                        break;
                    case FsConstant.RECORD_START:
                        break;
                    case FsConstant.MEDIA_BUG_START:
                        break;
                    case FsConstant.MEDIA_BUG_STOP:
                        break;
                    case FsConstant.PLAYBACK_START:
                        break;
                    case FsConstant.PLAYBACK_STOP:
                        break;
                    case FsConstant.CHANNEL_UNBRIDGE:
                        break;
                    case FsConstant.DTMF:
                        break;
                    case FsConstant.CHANNEL_PROGRESS_MEDIA:
                        break;
                    case FsConstant.RECORD_STOP:
                        break;
                    case FsConstant.CUSTOM:
                        break;
                    case FsConstant.CHANNEL_HOLD:
                        break;
                    case FsConstant.CHANNEL_UNHOLD:
                        break;
                    case FsConstant.RING_ASR:
                        break;
                    case FsConstant.RELOADXML:
                        break;
                    case FsConstant.ADD_SCHEDULE:
                        break;
                    default:
                        logger.info("event:{}, hander:{}", event.getEventName(), JSON.toJSONString(event.getEventHeaders()));
                        return;
                }

                FsBaseEvent formatEvent = formatEvent(ctx, event, eventName);
                if (formatEvent == null) {
                    return;
                }
                formatEvent.setRemoteAddress(host + Constant.CO + port);
                formatEvent.setLocalAddress(localAddress);

                /**
                 * 一个callId挂机处理必须使用一个相同的线程
                 */
                ExecutorService executorService = null;
                if (formatEvent instanceof FsHangupCompleteEvent) {
                    CallInfo callInfo = cacheService.getCallInfo(formatEvent.getDeviceId());
                    if (callInfo == null) {
                        return;
                    }
                    executorService = executorMap.get(RandomUtil.getNum(callInfo.getCallId().toString(), threadNum));
                }
                if (executorService == null) {
                    executorService = executorMap.get(RandomUtil.getNum(formatEvent.getDeviceId(), threadNum));
                }
                executorService.execute(() -> {
                    try {
                        if (logger.isDebugEnabled()) {
                            logger.info("ctx:{}, event:{}", ctx, event);
                        }
                        Handler handler = handlerContext.getInstance(formatEvent.getEventName());
                        if (handler != null) {
                            handler.handleEvent(formatEvent);
                        }
                    } catch (Exception e) {
                        logger.error("deviceId:{} eventName:{} errorMessage:{}", event.getEventHeaders().get("Unique-ID"), eventName, e.getMessage(), e);
                    }
                });
            }

            @Override
            public void onClose(Channel channel) {
                logger.warn("channel close {}", channel);
                client.close();
            }
        };

        client.addEventListener(listener);
        logger.info("connect {}:{} success", host, port);
    }

    /**
     * 消息转换
     *
     * @param context
     * @param eslEvent
     * @param eventName
     * @return
     */
    private FsBaseEvent formatEvent(Context context, EslEvent eslEvent, String eventName) {
        try {
            Class clzss = EventType.getClassByCmd(eventName);
            if (clzss == null) {
                return null;
            }
            Map map = eslEvent.getEventHeaders();
            JSONObject json = new JSONObject(map);
            if (StringUtils.isBlank(eventName)) {
                return null;
            }
            FsBaseEvent event = (FsBaseEvent) JSON.toJavaObject(json, clzss);
            event.setEventName(eventName);
            event.setContext(context);
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
        if (!fsClient.isEmpty()) {
            logger.info("freeswitch client stop");
            fsClient.forEach((i, client) -> {
                if (client.isActivate()) {
                    client.close();
                }
            });
        }
        checkFsThread.shutdown();
        executorMap.forEach((i, executor) -> {
            executor.shutdown();
        });
        hashedWheelTimer.stop();
    }

    /**
     * @param sendMsg
     */
    public void sendMessage(String media, SendMsg sendMsg) {
        if (StringUtils.isBlank(media)) {
            logger.error("send to media is null, sendMsg:{}", sendMsg);
            return;
        }
        fsClient.get(media).sendMessage(sendMsg);
    }


    /**
     * 发起呼叫
     *
     * @param routeGetway
     * @param display
     * @param called
     * @param callId
     * @param deviceId
     * @param timeout
     * @param originateTimeout
     * @param sipHeaders
     */
    public void makeCall(RouteGetway routeGetway, String display, String called, Long callId, String deviceId, Integer timeout, Integer originateTimeout, String... sipHeaders) {
        String media = RandomUtil.getRandomKey(fsClient.keySet());
        if (StringUtils.isBlank(media)) {
            throw new BusinessException(ErrorCode.MEDIA_NOT_AVALIABLE);
        }
        makeCall(media, routeGetway, display, called, callId, deviceId, timeout, originateTimeout, sipHeaders);
    }

    /**
     * 发起呼叫
     * <p>
     * bgapi originate {return_ring_ready=true,sip_contact_user=01017818388,ring_asr=true,absolute_codec_string=^^:G729:PCMU:PCMA,origination_caller_id_number=01017718388,origination_caller_id_name=01017718388,origination_uuid=192.168.1.1-25-5f8fe34b-1bb-76,sip_auto_answer=true}sofia/external/8731279@192.168.1.1:7470 &park()
     *
     * @param media
     * @param routeGetway      网关
     * @param display          主叫
     * @param called           被叫
     * @param deviceId
     * @param originateTimeout
     * @param sipHeaders
     */
    public void makeCall(String media, RouteGetway routeGetway, String display, String called, Long callId, String deviceId, Integer timeout, Integer originateTimeout, String... sipHeaders) {
        Client client = null;
        if (StringUtils.isBlank(media)) {
            media = RandomUtil.getRandomKey(fsClient.keySet());
        }
        if (StringUtils.isBlank(media)) {
            throw new BusinessException(ErrorCode.MEDIA_NOT_AVALIABLE);
        }
        logger.info("callId:{} makeCall routeGetway:{} display:{} called:{}, timeout{}, originateTimeout:{}", callId, routeGetway.getName(), display, called, timeout, originateTimeout);
        if (StringUtils.isBlank(media)) {
            throw new BusinessException(ErrorCode.MEDIA_NOT_AVALIABLE);
        }
        client = fsClient.get(media);

        called = called + Constant.AT + routeGetway.getMediaHost() + Constant.CO + routeGetway.getMediaPort();
        if (StringUtils.isNotBlank(routeGetway.getCallerPrefix())) {
            display = routeGetway.getCallerPrefix() + display;
        }
        if (StringUtils.isNotBlank(routeGetway.getCalledPrefix())) {
            called = routeGetway.getCalledPrefix() + called;
        }
        StringBuffer sipBuffer = new StringBuffer();
        if (sipHeaders != null) {
            for (int i = 0; i < sipHeaders.length; i++) {
                sipBuffer.append(FsConstant.SIP_HEADER + sipHeaders[i]);
                if (i < sipHeaders.length - 1) {
                    sipBuffer.append(FsConstant.SPLIT);
                }
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("callId", callId);
        params.put("deviceId", deviceId);
        params.put("caller", display);
        params.put("called", called);
        if (StringUtils.isNoneBlank(routeGetway.getSipHeader1())) {
            if (StringUtils.isNoneBlank(sipBuffer)) {
                sipBuffer.append(FsConstant.SPLIT);
            }
            String sipHeader1 = routeGetway.getSipHeader1();
            sipHeader1 = expression(sipHeader1, params);
            sipBuffer.append(FsConstant.SIP_HEADER + sipHeader1);
        }
        if (StringUtils.isNoneBlank(routeGetway.getSipHeader2())) {
            if (StringUtils.isNoneBlank(sipBuffer)) {
                sipBuffer.append(FsConstant.SPLIT);
            }
            String sipHeader2 = routeGetway.getSipHeader2();
            sipHeader2 = expression(sipHeader2, params);
            sipBuffer.append(FsConstant.SIP_HEADER + sipHeader2);
        }
        if (StringUtils.isNoneBlank(routeGetway.getSipHeader3())) {
            if (StringUtils.isNoneBlank(sipBuffer)) {
                sipBuffer.append(FsConstant.SPLIT);
            }
            sipBuffer.append(FsConstant.SIP_HEADER + routeGetway.getSipHeader3());
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{return_ring_ready=true").append(FsConstant.SPLIT).append("sip_contact_user=").append(display).append(FsConstant.SPLIT).append("ring_asr=true").append(FsConstant.SPLIT).append("absolute_codec_string=").append(codecs).append(FsConstant.SPLIT).append("origination_caller_id_number=").append(display).append(FsConstant.SPLIT).append("origination_caller_id_name=").append(display).append(FsConstant.SPLIT).append("origination_uuid=").append(deviceId);
        if (originateTimeout != null) {
            builder.append(FsConstant.SPLIT).append("originate_timeout=" + originateTimeout);
        }
        if (StringUtils.isNoneBlank(sipBuffer)) {
            builder.append(FsConstant.SPLIT).append(sipBuffer);
        }
        builder.append("}").append(FsConstant.SOFIA + Constant.SK + routeGetway.getProfile() + Constant.SK).append(called).append(FsConstant.PARK);
        client.sendBackgroundApiCommand(FsConstant.ORIGINATE, builder.toString());
    }


    /**
     * 桥接电话
     *
     * @param media
     * @param callId
     * @param deviceId1
     * @param deviceId2
     */
    public void bridgeCall(String media, Long callId, String deviceId1, String deviceId2) {
        this.sendArgs(media, deviceId1, FsConstant.SET, FsConstant.PARK_AFTER_BRIDGE);
        this.sendArgs(media, deviceId1, FsConstant.SET, FsConstant.HANGUP_AFTER_BRIDGE);
        this.sendArgs(media, deviceId2, FsConstant.SET, FsConstant.HANGUP_AFTER_BRIDGE);
        this.sendArgs(media, deviceId2, FsConstant.SET, FsConstant.PARK_AFTER_BRIDGE);
        StringBuilder builder = new StringBuilder();
        builder.append(deviceId1).append(FsConstant.SPACE).append(deviceId2);
        sendBgapiMessage(media, FsConstant.BRIDGE, builder.toString());
    }

    /**
     * @param media
     * @param from
     * @param to
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void transferCall(String media, String from, String to) {
        StringBuilder builder = new StringBuilder();
        builder.append(from).append("  -both 'set:hangup_after_bridge=false,set:park_after_bridge=true,park:' inline ");
        fsClient.get(media).sendBackgroundApiCommand(FsConstant.TRANSFER, builder.toString());
    }


    /**
     * 不使用线程池发送
     */
    public void sendSyncMessage(String media, SendMsg sendMsg) {
        fsClient.get(media).sendMessage(sendMsg);
    }

    /**
     * @param media
     * @param cmd
     * @param args
     */
    public void sendBgapiMessage(String media, String cmd, String args) {
        fsClient.get(media).sendBackgroundApiCommand(cmd, args);
    }


    /**
     * 应答
     *
     * @param media
     * @param deviceId
     * @return
     */
    public EslMessage answer(String media, String deviceId) {
        return fsClient.get(media).sendApiCommand("uuid_phone_event", deviceId + " talk");
    }


    /**
     * 挂机
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    public void hangupCall(String media, Long callId, String deviceId) {
        if (StringUtils.isBlank(media) || StringUtils.isBlank(deviceId)) {
            logger.warn("media:{} or deviceId:{} is null", media, deviceId);
            return;
        }
        SendMsg hangupMsg = new SendMsg(deviceId);
        hangupMsg.addCallCommand(FsConstant.EXECUTE);
        hangupMsg.addExecuteAppName(FsConstant.HANGUP);
        hangupMsg.addExecuteAppArg(FsConstant.NORMAL_CLEARING);
        logger.info("hangup call:{}, device:{}", callId, deviceId);
        this.sendMessage(media, hangupMsg);
    }

    /**
     * 挂机
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    public void killCall(String media, Long callId, String deviceId) {
        if (StringUtils.isBlank(media) || StringUtils.isBlank(deviceId)) {
            logger.warn("media:{} or deviceId:{} is null", media, deviceId);
            return;
        }
        logger.info("hangup call:{}, device:{}", callId, deviceId);
        fsClient.get(media).sendBackgroundApiCommand("uuid_kill ", deviceId);
    }

    /**
     * update call
     *
     * @param media
     * @param deviceId
     * @param name
     * @param arg
     */
    public void sendArgs(String media, String deviceId, String name, String arg) {
        SendMsg msg = new SendMsg(deviceId);
        msg.addCallCommand(FsConstant.EXECUTE);
        msg.addExecuteAppName(name);
        msg.addExecuteAppArg(arg);
        msg.addAsync();
        this.sendMessage(media, msg);
    }


    /**
     * bgapi uuid_transfer  sswitch-301-60f4c396-59-85 -both 'set:hangup_after_bridge=false,set:park_after_bridge=true,park:' inline
     * <p>
     * 从bridge 拆线
     *
     * @param mediaHost
     * @param deviceId
     */
    public void bridgeBreak(String mediaHost, String deviceId) {
        StringBuilder builder = new StringBuilder();
        builder.append(deviceId).append("  -both 'set:hangup_after_bridge=false,set:park_after_bridge=true,park:' inline ");
        fsClient.get(mediaHost).sendBackgroundApiCommand(FsConstant.TRANSFER, builder.toString());
    }

    /**
     * 保持放音
     *
     * @param media
     * @param deviceId
     * @param play
     */
    public void holdPlay(String media, String deviceId, String play) {
        this.sendArgs(media, deviceId, FsConstant.SET, FsConstant.PLAYBACK_TERMINATORS);
        this.sendArgs(media, deviceId, FsConstant.SET, FsConstant.PLAYBACK_DELIMITER);
        this.sendArgs(media, deviceId, FsConstant.SET, FsConstant.TTS_ENGINE);
        this.sendArgs(media, deviceId, FsConstant.PLAYBACK, play);
    }

    /**
     * 放音
     *
     * @param media
     * @param deviceId
     * @param file
     */
    public void playfile(String media, String deviceId, String file) {
        SendMsg playfile = new SendMsg(deviceId);
        playfile.addCallCommand(FsConstant.EXECUTE);
        playfile.addExecuteAppName(FsConstant.PLAYBACK);
        playfile.addExecuteAppArg(file);
        playfile.addAsync();
        this.sendMessage(media, playfile);
    }


    /**
     * 停止放音
     *
     * @param media
     * @param deviceId
     */
    public void playBreak(String media, String deviceId) {
        SendMsg playback = new SendMsg(deviceId);
        playback.addCallCommand(FsConstant.EXECUTE);
        playback.addExecuteAppName(FsConstant.BREAK_);
        this.sendMessage(media, playback);
    }


    /**
     * 表达式替换
     *
     * @param body   #{[name]}
     * @param params
     */
    protected String expression(String body, Map<String, Object> params) {
        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext();
        String content = null;
        try {
            content = parser.parseExpression(body, parserContext).getValue(params, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return content;
    }
}
