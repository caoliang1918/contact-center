package org.zhongweixian.ivr;

import org.cti.cc.entity.Station;
import org.cti.cc.mapper.StationMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.zhongweixian.ivr.tcp.TcpClientManager;

@SpringBootApplication
@MapperScan("org.cti.cc.mapper")
public class CcIvrApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {
    private Logger logger = LoggerFactory.getLogger(CcIvrApplication.class);

    @Autowired
    private StationMapper stationMapper;

    @Value("${spring.application.id}")
    private Integer applicationId;

    @Autowired
    private TcpClientManager tcpClientManager;


    @Override
    public void run(String... args) throws Exception {
        tcpClientManager.start();
    }

    @Bean
    public Station station() {
        if (applicationId == null || applicationId == 0) {
            logger.error("spring.application.id is null");
            System.exit(0);
        }
        Station station = stationMapper.selectByAppId(applicationId);
        if (station == null) {
            logger.error("station {} is not exist", applicationId);
            System.exit(0);
        }
        return station;
    }


    public static void main(String[] args) {
        SpringApplication.run(CcIvrApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}
