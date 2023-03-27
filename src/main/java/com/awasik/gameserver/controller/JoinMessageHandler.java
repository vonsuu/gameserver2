package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.model.ConnectedPlayer;
import com.awasik.gameserver.model.GameMessage;
import com.awasik.gameserver.model.ServerResponse;
import com.awasik.gameserver.repository.CharacterRepository;
import com.awasik.gameserver.service.CharacterCacheService;
import com.awasik.gameserver.service.ConnectedPlayersService;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

public class JoinMessageHandler implements GameMessageHandler {

    private CharacterRepository characterRepository;
    private CharacterCacheService characterCacheService;
    private ConnectedPlayersService connectedPlayersService;
    private Map<String, WebSocketSession> sessions = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(JoinMessageHandler.class);

    public JoinMessageHandler(CharacterRepository characterRepository,
                              CharacterCacheService characterCacheService,
                              ConnectedPlayersService connectedPlayersService,
                              Map<String, WebSocketSession> sessions) {
        this.characterRepository = characterRepository;
        this.characterCacheService = characterCacheService;
        this.connectedPlayersService = connectedPlayersService;
        this.sessions = sessions;
    }

    @Override
    public ServerResponse handleMessage(GameMessage msg, WebSocketSession session, Character character) {

        if (character == null) {
            // Fetch the character object from the repository
            character = characterRepository.findById(UUID.fromString(msg.getData().getCharacterId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));

            // Add the character object to the cache
            LOGGER.info("Player joined the game: " + character.getName() + ", " + character.getId());
            characterCacheService.putCharacterInCache(character);
            sessions.put(character.getId().toString(), session);
            connectedPlayersService.addConnectedPlayer(new ConnectedPlayer(character.getId(), character, session, 0, 0, "IDLE"));
        } else {
            sessions.put(character.getId().toString(), session);
            // Character is already in the game
            return ServerResponse.builder()
                    .method("echo")
                    .message(character.getName() + " is already in the game")
                    .target("SENDER")
                    .build();
        }

        return ServerResponse.builder()
                .method("echo")
                .message(character.getName() + " joined the game")
                .target("ALL")
                .build();
    }
}
