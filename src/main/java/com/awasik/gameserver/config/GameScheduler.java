package com.awasik.gameserver.config;

import com.awasik.gameserver.service.GameLoop;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class GameScheduler {

    private final GameLoop gameLoop;

    public GameScheduler(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    @Scheduled(fixedRate = 16) // 60 frames per second
    public void update() {
        gameLoop.run();
    }
}
