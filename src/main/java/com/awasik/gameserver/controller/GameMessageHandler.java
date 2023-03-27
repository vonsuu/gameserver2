package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.model.GameMessage;
import com.awasik.gameserver.model.ServerResponse;
import org.springframework.web.socket.WebSocketSession;

public interface GameMessageHandler {

    ServerResponse handleMessage(GameMessage msg, WebSocketSession session, Character character);

}