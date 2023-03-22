package com.awasik.gameserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Deserialize the incoming message
        ObjectMapper objectMapper = new ObjectMapper();
        MyMessage myMessage = objectMapper.readValue(message.getPayload(), MyMessage.class);

        // Process the message and create a response
        String responseText = "Hello123, " + myMessage.getName() + "!";
        TextMessage response = new TextMessage(responseText);

        // Send the response back to the client
        session.sendMessage(response);
    }
}
