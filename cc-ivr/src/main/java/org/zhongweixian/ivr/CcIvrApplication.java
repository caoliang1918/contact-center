package org.zhongweixian.ivr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.zhongweixian.ivr.tcp.TcpClientManager;

@SpringBootApplication
@MapperScan("org.cti.cc.mapper")
public class CcIvrApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {


    @Autowired
    private TcpClientManager tcpClientManager;


    @Override
    public void run(String... args) throws Exception {
        tcpClientManager.start();
    }



    public static void main(String[] args) {
        SpringApplication.run(CcIvrApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}
