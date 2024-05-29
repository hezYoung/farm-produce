/**
 * 1. @ClassName InitConfig
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/28 16:22
 */
package com.farm.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.farm.feign.order.fallback")
public class InitConfig {
}