package com.voice9.api;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.voice9.api.configration.QuartzConfig;
import io.minio.MinioClient;
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


@EnableDiscoveryClient
@EnableEncryptableProperties
@MapperScan("com.voice9.core.mapper")
@SpringBootApplication
public class CcApiApplication implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {
    private Logger logger = LoggerFactory.getLogger(CcApiApplication.class);

    @Autowired
    private QuartzConfig quartzConfig;

    public static void main(String[] args) {
        SpringApplication.run(CcApiApplication.class, args);
    }


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
    public RestTemplate restTemplate(@Value("${voice9.notify.connectTimeout:100}") Integer connectTimeout, @Value("${voice9.notify.readTimeout:300}") Integer readTimeout) {
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
    public RestTemplate httpClient(@Value("${voice9.inner.connectTimeout:100}") Integer connectTimeout, @Value("${voice9.inner.readTimeout:3000}") Integer readTimeout) {
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

    @Override
    public void run(String... args) throws Exception {
        quartzConfig.initJob(false);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

    }
}

