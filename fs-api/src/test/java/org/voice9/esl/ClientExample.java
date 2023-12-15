package org.voice9.esl;

import io.netty.channel.Channel;
import org.voice9.cc.fs.esl.internal.Context;
import org.voice9.cc.fs.esl.internal.IModEslApi;
import org.voice9.cc.fs.esl.transport.event.EslEvent;
import org.voice9.cc.fs.esl.inbound.Client;
import org.voice9.cc.fs.esl.inbound.IEslEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientExample {
    private static final Logger logger = LoggerFactory.getLogger(ClientExample.class);

    public static void main(String[] args) {
        try {


            String password = "";
            Client client = new Client();

            client.addEventListener(new IEslEventListener() {
                @Override
                public void onEslEvent(Context ctx, EslEvent event) {
                    logger.info("Received event: {}", event.getEventName());
                }

                @Override
                public void onClose(Channel channel) {

                }
            });

            client.connect(new InetSocketAddress("localhost", 8021), password, 10);
            client.setEventSubscriptions(IModEslApi.EventFormat.PLAIN, "all");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
