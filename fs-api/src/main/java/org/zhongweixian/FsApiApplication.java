package org.zhongweixian;


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
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.tcp.TcpServer;
import org.zhongweixian.cc.util.SnowflakeIdWorker;
import org.zhongweixian.cc.websocket.WebSocketManager;
import org.zhongweixian.cc.websocket.handler.WsMonitorHandler;

import java.util.List;


@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan("org.cti.cc.mapper")
@SpringBootApplication
public class FsApiApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private Logger logger = LoggerFactory.getLogger(FsApiApplication.class);

    @Autowired
    private WebSocketManager webSocketManager;

    @Autowired
    private TcpServer tcpServer;

    @Autowired
    private FsListen fsListen;

    @Autowired
    private GroupHandler groupHandler;

    @Autowired
    private WsMonitorHandler wsMonitorHandler;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private StationMapper stationMapper;

    @Value("${spring.application.id}")
    private Integer applicationId;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    //    @Bean
   /* public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }*/

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
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
        try {
            String name = "fs-api";
            NamingService namingService = NamingFactory.createNamingService("115.159.101.178:8848");
            List<Instance> instances = namingService.getAllInstances(name);
            logger.info("==========={}", instances);
            namingService.subscribe(name, new EventListener() {
                @Override
                public void onEvent(Event event) {
                    logger.info(" ============== {}", ((NamingEvent) event).getServiceName());
                    logger.info("============== = {}", ((NamingEvent) event).getInstances());
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return station;
    }


    public static void main(String[] args) {
        SpringApplication.run(FsApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cacheService.initCompany();
        webSocketManager.start();
        tcpServer.start();
        fsListen.start();
        groupHandler.start();
        wsMonitorHandler.start();
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        webSocketManager.stop();
        tcpServer.stop();
        fsListen.stop();
        groupHandler.stop();
        wsMonitorHandler.stop();
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        Station station = station();
        factory.setPort(station.getApplicationPort());
    }
}
