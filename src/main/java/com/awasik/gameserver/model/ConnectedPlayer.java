package com.awasik.gameserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;


@AllArgsConstructor
@Data
public class ConnectedPlayer {

    private UUID id;
    private Character character;
    private WebSocketSession session;
    private float realPosition;
    private float targetPosition;

    private String state = "IDLE";
}
