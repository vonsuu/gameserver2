package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.model.ConnectedPlayer;
import com.awasik.gameserver.model.GameMessage;
import com.awasik.gameserver.model.ServerResponse;
import com.awasik.gameserver.service.ConnectedPlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

public class MoveMessageHandler implements GameMessageHandler {

    private ConnectedPlayersService connectedPlayersService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveMessageHandler.class);

    public MoveMessageHandler(ConnectedPlayersService connectedPlayersService) {
        this.connectedPlayersService = connectedPlayersService;
    }

    @Override
    public ServerResponse handleMessage(GameMessage msg, WebSocketSession session, Character character) {

        String direction = msg.getData().getMoveDirection();
        ConnectedPlayer player = connectedPlayersService.getConnectedPlayer(character);
        if(direction.equals("RIGHT")) {
            LOGGER.info("Player moving right: " + character.getName());
            player.setTargetPosition(player.getRealPosition()+1);
            player.setState("MOVING_RIGHT");
        }
        else if(direction.equals("STOP")) {
            LOGGER.info("Player stopeed: " + character.getName());
            player.setState("IDLE");
        }
        return ServerResponse.builder()
                .method("echo")
                .message(character.getId() + ": moving right")
                .target("ALL")
                .build();
    }
}
