package com.bryan.springbootdemo.config;

import com.bryan.springbootdemo.handler.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * ClassName: WebSocketConfig
 * Package: com.bryan.springbootdemo.config
 * Description:
 * Author: Bryan Long
 * Create: 2025/2/23 - 20:58
 * Version: v1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/ws")
                .setAllowedOrigins("*"); // 允许所有来源，生产环境需指定域名
    }

    /**
     * ServerEndpointExporter 是 Spring Boot 与标准 Java WebSocket (@ServerEndpoint) 兼容的组件。它的作用是：
     * 自动注册 @ServerEndpoint 注解标记的 WebSocket 端点（适用于 javax.websocket 规范）。
     * 如果使用 Spring WebSocket（基于 WebSocketHandler），这个 Bean 不是必须的。
     * 但是如果你的 WebSocket 端点是通过 @ServerEndpoint 方式定义的（例如 @ServerEndpoint("/ws")），那就必须添加这个 Bean。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    
}
