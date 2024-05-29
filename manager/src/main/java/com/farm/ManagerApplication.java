/**
 * 1. @ClassName ManagerApplication
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:41
 */
package com.farm;

import com.farm.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(value = {MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class , args) ;

    }
}

