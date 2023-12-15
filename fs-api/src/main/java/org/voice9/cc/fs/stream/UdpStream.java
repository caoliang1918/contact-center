package org.voice9.cc.fs.stream;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.apache.commons.io.IOUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.StreamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2021/12/4
 */
@Component
public class UdpStream {
    private Logger logger = LoggerFactory.getLogger(UdpStream.class);

    @Value("${stream.address:}")
    private String streamAddress;

    @Value("${stream.min.port:10000}")
    private Integer minPort;

    @Value("${stream.max.port:20000}")
    private Integer maxPort;

    private Map<Long, StreamEntity> streamEntityMap = new HashMap<>();

    private Map<String, EventLoopGroup> channelMap = new HashMap<>();

    private Bootstrap bootstrap = new Bootstrap();


    /**
     * @param callId
     * @return
     */
    public StreamEntity start(Long callId, String device1, String device2) {
        StreamEntity entity = new StreamEntity();

        Integer port1 = SocketUtils.findAvailableUdpPort(minPort, maxPort);
        Integer port2 = SocketUtils.findAvailableUdpPort(minPort, maxPort);
        entity.setCallerAddress(streamAddress + Constant.CO + port1);
        entity.setCalledAddress(streamAddress + Constant.CO + port2);
        entity.setCallId(callId);
        streamEntityMap.put(callId, entity);

        try {
            /**
             * 使用子线程处理
             */
            startUdp(callId, port1, device1);
            startUdp(callId, port2, device2);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return entity;
    }

    private void startUdp(Long callId, Integer port, String device) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        FileOutputStream stream = new FileOutputStream("../voice/" + callId + device + ".pcm");
        bootstrap.group(workerGroup).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_RCVBUF, 10 * 1024 * 1024).handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    public void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
                        ByteBuf byteBuf = datagramPacket.content();
                        byteBuf.skipBytes(12);
                        byte[] data = new byte[byteBuf.readableBytes()];
                        byteBuf.readBytes(data);

                        byte[] pcm = G711.decode(data);
                        //写到本地
                        IOUtils.write(pcm, stream);
                    }

                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        logger.info("callId:{} port:{} close", callId, port);
                        stream.close();
                    }
                });
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        bootstrap.bind(inetSocketAddress).sync();
        channelMap.put(device, workerGroup);
        logger.info("callId:{}, device:{}, port:{}", callId, device, port);
    }

    @Async
    public void stop(Long callId) {
        StreamEntity entity = streamEntityMap.get(callId);
        if (entity == null) {
            return;
        }
        EventLoopGroup group1 = channelMap.remove(entity.getDevice1());
        if (group1 != null) {
            group1.shutdownGracefully();
        }
        EventLoopGroup group2 = channelMap.remove(entity.getDevice2());
        if (group2 != null) {
            group2.shutdownGracefully();
        }
    }


}
