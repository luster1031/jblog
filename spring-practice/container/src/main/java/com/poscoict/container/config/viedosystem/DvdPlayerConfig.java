package com.poscoict.container.config.viedosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poscoict.container.viedosystem.DVDPlayer;
import com.poscoict.container.viedosystem.DigitalVideoDisc;

@Configuration
public class DvdPlayerConfig {
	
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	
	@Bean
	public DVDPlayer dvdplayer() {
		return new DVDPlayer();
	}
	
	//주입 (Injection)하기 1
	
}
