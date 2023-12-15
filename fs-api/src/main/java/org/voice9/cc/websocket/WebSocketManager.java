package org.voice9.cc.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.server.websocket.WebSocketServer;

/**
 * Create by caoliang on 2020/9/20
 */
@Component
public class WebSocketManager {
    private Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

    @Value("${ws.server.port:7250}")
    private Integer port;

    @Value("${ws.server.path:ws}")
    private String path;

    @Autowired
    private WebSocketHandler webSocketHandler;

    private WebSocketServer webSocketServer;

    public void start() {
        webSocketServer = new WebSocketServer(port, 60, path, 2, 4, webSocketHandler);
        webSocketServer.start();
        webSocketHandler.check();
    }

    public void stop() {
        if (webSocketServer == null) {
            return;
        }
        logger.info("websocket server:{} stop", port);
        webSocketHandler.stop();
        webSocketServer.close();
    }
}
