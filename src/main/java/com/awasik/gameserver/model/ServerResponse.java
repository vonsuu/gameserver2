package com.awasik.gameserver.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerResponse {
    private String method;
    private String message;

    private String target = "SENDER";
}

