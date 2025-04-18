package com.bryan.springbootdemo.test.wetsocket;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ChatEndpoint
 * Package: com.bryan.springbootdemo.test.wetsocket
 * Description:
 * Author: Bryan Long
 * Create: 2025/2/24-18:03
 * Version: v1.0
 */
@Slf4j
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("onOpen");

        // 1.将 Session 进行保存
        this.httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        String username = httpSession.getAttribute("username").toString();
        onlineUsers.put(username, session);

        // 2.广播消息
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("onMessage");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("onClose");
    }

    private void sendMessage(Session session, String message) throws IOException {
        log.info("sendMessage: {}", message);

        Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
        for (Map.Entry<String, Session> entry : entries) {
            Session userSession = entry.getValue();
            if (userSession.isOpen()) {
//                同步
//                userSession.getBasicRemote().sendText(message);

//                异步
                userSession.getAsyncRemote().sendText(message);
            }
        }

    }

    private Set<String> onlineUsers() {
        return onlineUsers.keySet();
    }

}
