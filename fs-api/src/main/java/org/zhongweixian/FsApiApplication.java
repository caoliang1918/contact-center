package org.zhongweixian;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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


@SpringBootApplication
@EnableEncryptableProperties
@MapperScan("org.cti.cc.mapper")
public class FsApiApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

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
}
