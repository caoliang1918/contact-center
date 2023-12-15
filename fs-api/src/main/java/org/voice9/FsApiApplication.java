package org.voice9;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.minio.MinioClient;
import io.netty.util.HashedWheelTimer;
import com.voice9.core.util.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.command.GroupHandler;
import org.voice9.cc.fs.FsListen;
import org.voice9.cc.tcp.TcpServer;
import org.voice9.cc.websocket.WebSocketManager;

import java.util.concurrent.TimeUnit;


@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan("com.voice9.core.mapper")
@SpringBootApplication
public class FsApiApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {
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
    private CacheService cacheService;


    @Value("${subscribe.agent.state:false}")
    private Boolean agentState;


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    /**
     * 外部调用服务
     *
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    @Bean
    public RestTemplate restTemplate(@Value("${cdr.notify.connectTimeout:100}") Integer connectTimeout, @Value("${cdr.notify.readTimeout:300}") Integer readTimeout) {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }

    /**
     * 内部调用服务
     *
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate httpClient(@Value("${cc.inner.connectTimeout:100}") Integer connectTimeout, @Value("${cc.inner.readTimeout:3000}") Integer readTimeout) {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }


    @Bean
    public MinioClient minioClient(@Value("${voice9.minio.endpoint:}") String endpoint, @Value("${voice9.minio.access.key:}") String accessKey, @Value("${voice9.minio.secret.key:}") String secretKey) {
        try {
            return new MinioClient.Builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }

    @Bean
    public HashedWheelTimer hashedWheelTimer() {
        return new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 10240);
    }


    public static void main(String[] args) {
        SpringApplication.run(FsApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cacheService.initCompany();
        webSocketManager.start();
        fsListen.start();
        groupHandler.start();

        //开启tcp订阅坐席状态
        if (agentState) {
            tcpServer.start();
        }
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        cacheService.stop();
        webSocketManager.stop();
        fsListen.stop();
        groupHandler.stop();
        tcpServer.stop();
    }
}
