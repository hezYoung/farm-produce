/**
 * 1. @ClassName PayMentApplication
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:03
 */
package com.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
        "com.farm.feign"
})
@SpringBootApplication
public class PayMentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayMentApplication.class, args);
    }
}

