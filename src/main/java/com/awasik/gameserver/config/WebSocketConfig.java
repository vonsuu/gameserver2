package com.awasik.gameserver.config;

import com.awasik.gameserver.controller.GameWebSocketController;
import com.awasik.gameserver.controller.WebSocketController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/my-websocket-endpoint")
                .setAllowedOrigins("*");
        registry.addHandler(gameWebSocketController(), "/game")
                .setAllowedOrigins("*");
    }


    @Bean
    public WebSocketHandler gameWebSocketController() {
        return new GameWebSocketController();
    }

    @Bean
    public WebSocketController myWebSocketHandler() {
        return new WebSocketController();
    }
}

