package com.awasik.gameserver.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyMessage {

    private String name;

    public MyMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}