package com.awasik.gameserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameLoop implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);

    private final GameService gameService;

    public GameLoop(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run() {
        LOGGER.info("Loop is running");
    }
}
