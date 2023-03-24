package com.awasik.gameserver.model;

import lombok.Data;

@Data
public class ServerResponse {
    private String method;
    private String message;

    public ServerResponse(String method, String message) {
        this.method = method;
        this.message = message;
    }
}

