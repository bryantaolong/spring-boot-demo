package com.bryan.springbootdemo.test.wetsocket;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: GetHttpSessionConfig
 * Package: com.bryan.springbootdemo.test.wetsocket
 * Description:
 * Author: Bryan Long
 * Create: 2025/2/24 - 18:13
 * Version: v1.0
 */
@Configuration
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(
            ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }

}
