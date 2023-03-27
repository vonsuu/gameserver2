package com.awasik.gameserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameObject {

    private Position arenaPosition;
    private Position areaPosition;

}
