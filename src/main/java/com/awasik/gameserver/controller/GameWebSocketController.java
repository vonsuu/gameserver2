package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.GameMessage;
import com.awasik.gameserver.model.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class GameWebSocketController extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Deserialize the incoming message
        ObjectMapper objectMapper = new ObjectMapper();
        GameMessage msg = objectMapper.readValue(message.getPayload(), GameMessage.class);

        String responseJson = objectMapper.writeValueAsString(new ServerResponse("echo", "Hello, " + msg.getSubject() + "!"));

        // Process the message and create a response
        TextMessage response = new TextMessage(responseJson);

        // Send the response back to the client
        session.sendMessage(response);
    }
}
