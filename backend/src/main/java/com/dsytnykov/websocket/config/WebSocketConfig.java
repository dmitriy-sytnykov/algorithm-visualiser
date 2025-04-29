package com.dsytnykov.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.dsytnykov.websocket.handler.AlgorithmWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final AlgorithmWebSocketHandler algorithmWebSocketHandler;

    public WebSocketConfig(AlgorithmWebSocketHandler algorithmWebSocketHandler) {
        this.algorithmWebSocketHandler = algorithmWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(algorithmWebSocketHandler, "/algorithm-ws")
                .setAllowedOriginPatterns("*");
    }
}
