package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.model.ConnectedPlayer;
import com.awasik.gameserver.model.GameMessage;
import com.awasik.gameserver.model.ServerResponse;
import com.awasik.gameserver.repository.CharacterRepository;
import com.awasik.gameserver.service.CharacterCacheService;
import com.awasik.gameserver.service.ConnectedPlayersService;
import com.awasik.gameserver.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

public class GameWebSocketController extends TextWebSocketHandler {

    private CharacterRepository characterRepository;
    private CharacterCacheService characterCacheService;
    private ConnectedPlayersService connectedPlayersService;
    private Map<String, WebSocketSession> sessions = new HashMap<>();

    private Map<String, GameMessageHandler> messageHandlers = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(GameWebSocketController.class);

    public GameWebSocketController(CharacterRepository characterRepository,
                                   CharacterCacheService characterCacheService,
                                   ConnectedPlayersService connectedPlayersService) {
        this.characterRepository = characterRepository;
        this.characterCacheService = characterCacheService;
        this.connectedPlayersService = connectedPlayersService;
        messageHandlers.put("join", new JoinMessageHandler(characterRepository, characterCacheService, connectedPlayersService
        , sessions));
        messageHandlers.put("move", new MoveMessageHandler(connectedPlayersService));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //sessions.put(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        // Deserialize the incoming message
        ObjectMapper objectMapper = new ObjectMapper();
        GameMessage msg = objectMapper.readValue(message.getPayload(), GameMessage.class);
        Character character = characterCacheService.getCharacterFromCache(msg.getData().getCharacterId());
        GameMessageHandler handler = messageHandlers.get(msg.getMethod());
        if (handler == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid method: " + msg.getMethod());
        }
        else if(!(handler instanceof JoinMessageHandler) && character == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player must first join the game: " + msg.getMethod());
        }
        // Delegate message handling to the handler
        ServerResponse response = handler.handleMessage(msg, session, character);
        String responseJson = objectMapper.writeValueAsString(response);
        TextMessage textMessageResponse = new TextMessage(responseJson);
        if(response.getTarget().equals("ALL")) {
            // Send the message to all connected clients
            for (Map.Entry<String, WebSocketSession> s : sessions.entrySet()) {
                try {
                    s.getValue().sendMessage(textMessageResponse);
                } catch (IOException e) {
                    sessions.remove(s.getKey());
                }
            }
            return;
        }
        // Send the response back to the client
        try {
            session.sendMessage(textMessageResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
