package com.bryan.springbootdemo.config.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: JwtProperties
 * Package: com.bryan.template.config.properties
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:51
 * Version: v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * admin
     */
    private String adminRoleName;
    private String adminSecretKey;
    private long adminTtl;

    /**
     * user
     */
    private String userRoleName;
    private String userSecretKey;
    private long userTtl;
}
