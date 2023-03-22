package com.awasik.gameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.awasik.gameserver")
public class GameserverApplication {

	public static void main(	String[] args) {
		SpringApplication.run(GameserverApplication.class, args);
	}

}

