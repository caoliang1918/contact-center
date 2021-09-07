package org.zhongweixian.ivr;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
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
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.zhongweixian.ivr.tcp.TcpClientManager;

@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan("org.cti.cc.mapper")
@SpringBootApplication
public class CcIvrApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private Logger logger = LoggerFactory.getLogger(CcIvrApplication.class);

    @Autowired
    private StationMapper stationMapper;

    @Value("${spring.application.id}")
    private Integer applicationId;

    @Autowired
    private TcpClientManager tcpClientManager;


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
    public void run(String... args) throws Exception {
        tcpClientManager.start();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        tcpClientManager.stop();
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        Station station = station();
        factory.setPort(station.getApplicationPort());
    }
}
