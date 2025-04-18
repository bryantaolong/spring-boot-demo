package com.bryan.springbootdemo.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * ClassName: MyWebSocketHandler
 * Package: com.bryan.springbootdemo.common.handler
 * Description:
 * Author: Bryan Long
 * Create: 2025/2/23 - 21:00
 * Version: v1.0
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("新连接建立: " + session.getId());
        session.sendMessage(new TextMessage("欢迎连接 WebSocket 服务器！"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("收到消息: " + message.getPayload());
        session.sendMessage(new TextMessage("服务器收到: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("连接关闭: " + session.getId());
    }
}

