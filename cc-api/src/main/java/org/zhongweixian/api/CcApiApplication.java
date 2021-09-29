package org.zhongweixian.api;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
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
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zhongweixian.api.configration.QuartzConfig;

import java.util.List;


@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan("org.cti.cc.mapper")
@SpringBootApplication
public class CcApiApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private Logger logger = LoggerFactory.getLogger(CcApiApplication.class);

    @Autowired
    private QuartzConfig quartzConfig;

    @Autowired
    private StationMapper stationMapper;

    @Value("${spring.application.id}")
    private Integer applicationId;


    public static void main(String[] args) {
        SpringApplication.run(CcApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        quartzConfig.initJob();

        try {
            String name = "cc-api";
            NamingService namingService = NamingFactory.createNamingService("115.159.101.178:8848");
            List<Instance> instances = namingService.getAllInstances(name);
            logger.info("init instances {}", instances);
            namingService.subscribe(name, new EventListener() {
                @Override
                public void onEvent(Event event) {
                    logger.info(" ============== {} , {}", ((NamingEvent) event).getServiceName(), ((NamingEvent) event).getClusters());
                    logger.info("============== = {}", ((NamingEvent) event).getInstances());
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
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

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        Station station = station();
        factory.setPort(station.getApplicationPort());
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        quartzConfig.stop();
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(100);
        simpleClientHttpRequestFactory.setReadTimeout(500);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }
}

