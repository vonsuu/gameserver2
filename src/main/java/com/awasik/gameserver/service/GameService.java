package com.awasik.gameserver.service;

import com.awasik.gameserver.model.ConnectedPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
    @Autowired
    private ConnectedPlayersService connectedPlayersService;
    public void update(long deltaTime) {
        for(ConnectedPlayer player: connectedPlayersService.getConnectedPlayers().values()) {
            LOGGER.debug(player.getId() + " " + String.valueOf(player.getRealPosition()));
            if(player.getState().equals("MOVING_RIGHT")) {
                player.setRealPosition(player.getRealPosition() + 1 * deltaTime);
            }
            else if(player.getState().equals("IDLE")) {
                if(player.getRealPosition() < player.getTargetPosition()) {
                    player.setRealPosition(player.getRealPosition() + 1 * deltaTime);
                }
            }
        }
    }
}
