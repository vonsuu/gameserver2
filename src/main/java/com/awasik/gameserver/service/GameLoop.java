package com.awasik.gameserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameLoop implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);
    private long lastUpdateTime = System.currentTimeMillis();
    private final GameService gameService;
    public GameLoop(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastUpdateTime;
        lastUpdateTime = currentTime;
        update(deltaTime);
    }
    private void update(long deltaTime) {
        gameService.update(deltaTime);
    }
}
