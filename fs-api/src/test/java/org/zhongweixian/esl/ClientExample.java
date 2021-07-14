package org.zhongweixian.esl;

import org.zhongweixian.esl.inbound.Client;
import org.zhongweixian.esl.inbound.IEslEventListener;
import org.zhongweixian.esl.internal.Context;
import org.zhongweixian.esl.internal.IModEslApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhongweixian.esl.transport.event.EslEvent;

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
                public void onClose() {

                }
            });

            client.connect(new InetSocketAddress("localhost", 8021), password, 10);
            client.setEventSubscriptions(IModEslApi.EventFormat.PLAIN, "all");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
