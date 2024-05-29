/**
 * 1. @ClassName MinioProperties
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 10:08
 */
package com.farm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="farm.minio") //读取节点
public class MinioProperties {

    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;

}