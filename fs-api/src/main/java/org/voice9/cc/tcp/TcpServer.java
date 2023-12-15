package org.voice9.cc.tcp;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.server.tcp.NettyServer;

import java.util.concurrent.*;

/**
 * Create by caoliang on 2020/9/20
 */

@Component
public class TcpServer {
    private Logger logger = LoggerFactory.getLogger(TcpServer.class);

    @Value("${tcp.server.port:7260}")
    private Integer port;


    private ThreadPoolExecutor executor = null;


    @Autowired
    private TcpServerHandler tcpServerHandler;


    private NettyServer nettyServer = null;

    public void start() {
        executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("tcp-client-pool-%d").build());
        nettyServer = new NettyServer(port, tcpServerHandler);
        nettyServer.start();
        tcpServerHandler.check();
    }


    public void stop() {
        if (nettyServer != null) {
            logger.info("tcp server:{} stop", port);
            nettyServer.close();
            tcpServerHandler.stop();
        }
    }


}
