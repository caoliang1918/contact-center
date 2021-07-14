package org.zhongweixian.cc;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableEncryptableProperties
@MapperScan("org.cti.cc.mapper")
public class CcOpenapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcOpenapiApplication.class, args);
    }

}
