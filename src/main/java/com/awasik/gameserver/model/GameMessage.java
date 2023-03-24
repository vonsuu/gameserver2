package com.awasik.gameserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameMessage {
    private String method;
    private String subject;
}

