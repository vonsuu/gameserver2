package com.awasik.gameserver.service;

import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.model.ConnectedPlayer;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class ConnectedPlayersService {
    private Map<UUID, ConnectedPlayer> connectedPlayers;

    public ConnectedPlayersService() {
        this.connectedPlayers = new HashMap<>();
    }

    public void addConnectedPlayer(ConnectedPlayer player) {
        connectedPlayers.put(player.getCharacter().getId(), player);
    }
    public ConnectedPlayer getConnectedPlayer(Character character) {
        return this.connectedPlayers.get(character.getId());
    }
}
