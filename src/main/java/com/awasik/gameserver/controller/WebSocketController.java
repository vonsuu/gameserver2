package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.MyMessage;
import com.awasik.gameserver.model.MyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class WebSocketController extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Deserialize the incoming message
        ObjectMapper objectMapper = new ObjectMapper();
        MyMessage myMessage = objectMapper.readValue(message.getPayload(), MyMessage.class);

        String responseJson = objectMapper.writeValueAsString(new MyResponse("echo", "Hello, " + myMessage.getName() + "!"));

        // Process the message and create a response
        TextMessage response = new TextMessage(responseJson);

        // Send the response back to the client
        session.sendMessage(response);
    }
}
