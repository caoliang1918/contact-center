package org.voice9.cc.fs.esl.outbound;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.voice9.cc.fs.esl.transport.message.EslFrameDecoder;

import java.util.concurrent.*;

public class OutboundChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final IClientHandlerFactory clientHandlerFactory;

    private static ThreadFactory onEslThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("outbound-onEsl-pool-%d").build();

    private static ExecutorService onEslExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100000), onEslThreadFactory);

    private static ThreadFactory onConnectThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("outbound-onConnect-pool-%d").build();

    private static ExecutorService onConnectExecutor = new ThreadPoolExecutor(32, 512,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2048), onConnectThreadFactory);


    public OutboundChannelInitializer(IClientHandlerFactory clientHandlerFactory) {
        this.clientHandlerFactory = clientHandlerFactory;
    }

    public OutboundChannelInitializer(IClientHandlerFactory clientHandlerFactory, ExecutorService connExecutor, ExecutorService eslExecutor) {
        this.clientHandlerFactory = clientHandlerFactory;
        onEslExecutor = eslExecutor;
        onConnectExecutor = connExecutor;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // Add the text line codec combination first
        pipeline.addLast("encoder", new StringEncoder());
        // Note that outbound mode requires the decoder to treat many 'headers' as body lines
        pipeline.addLast("decoder", new EslFrameDecoder(8092, true));

        // now the outbound client logic
        pipeline.addLast("clientHandler",
                new OutboundClientHandler(clientHandlerFactory.createClientHandler(), onEslExecutor, onConnectExecutor));

    }
}
