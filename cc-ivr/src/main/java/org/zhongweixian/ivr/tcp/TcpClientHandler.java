package org.zhongweixian.ivr.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zhongweixian.listener.ConnectionListener;

/**
 * Created by caoliang on 2021/8/7
 */
@Component
public class TcpClientHandler implements ConnectionListener {
    private Logger logger = LoggerFactory.getLogger(TcpClientHandler.class);



    @Override
    public void onClose(Channel channel, int i, String s) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onFail(int i, String s) {

    }

    @Override
    public void onMessage(Channel channel, String payload) throws Exception {

    }

    @Override
    public void onMessage(Channel channel, ByteBuf byteBuf) throws Exception {

    }

    @Override
    public void connect(Channel channel) throws Exception {

    }
}
