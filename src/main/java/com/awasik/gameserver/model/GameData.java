package com.awasik.gameserver.model;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameData {
    private String characterId;
    private String moveDirection;


}
