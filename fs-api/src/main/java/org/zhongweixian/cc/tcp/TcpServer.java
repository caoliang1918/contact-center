package org.zhongweixian.cc.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.server.tcp.NettyServer;

/**
 * Create by caoliang on 2020/9/20
 */

@Component
public class TcpServer {
    private Logger logger = LoggerFactory.getLogger(TcpServer.class);

    @Value("${tcp.server.port:2525}")
    private Integer port;

    @Autowired
    private TcpServerHandler tcpServerHandler;


    private NettyServer nettyServer = null;

    public void start() {
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
