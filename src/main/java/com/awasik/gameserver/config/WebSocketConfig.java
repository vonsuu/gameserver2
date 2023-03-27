package com.awasik.gameserver.config;

import com.awasik.gameserver.controller.GameWebSocketController;
import com.awasik.gameserver.repository.CharacterRepository;
import com.awasik.gameserver.service.CharacterCacheService;
import com.awasik.gameserver.service.ConnectedPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterCacheService characterCacheService;

    @Autowired
    private ConnectedPlayersService connectedPlayersService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gameWebSocketController(), "/game")
                .setAllowedOrigins("*");
    }


    @Bean
    public WebSocketHandler gameWebSocketController() {
        return new GameWebSocketController(characterRepository, characterCacheService, connectedPlayersService);
    }

}

