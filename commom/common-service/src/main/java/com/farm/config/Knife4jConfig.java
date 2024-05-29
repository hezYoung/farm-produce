/**
 * 1. @ClassName Knife4jConfig
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:33
 */
package com.farm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi adminApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("admin-api")         // 分组名称
                .pathsToMatch("/admin/**")  // 接口请求路径规则
                .build();
    }

    /***
     * @description 自定义接口信息
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("智慧农庄商城API接口文档")
                        .version("1.0")
                        .description("智慧农庄商城API接口文档")
                        .contact(new Contact().name("young"))); // 设定作者
    }

}
