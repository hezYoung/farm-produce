/**
 * 1. @ClassName OrderApplication
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/15 16:00
 */
package com.farm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan(basePackages = {"com.farm.mapper"})
@EnableFeignClients(basePackages = {
        "com.farm.feign"
})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

