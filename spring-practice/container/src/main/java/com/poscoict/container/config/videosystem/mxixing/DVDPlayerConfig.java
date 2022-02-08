package com.poscoict.container.config.videosystem.mxixing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.poscoict.container.videosystem.DVDPlayer;
import com.poscoict.container.videosystem.DigitalVideoDisc;

/**
 * JavaConfig2(DVDPlayerConfig) <--------------javaConfig1(DVDconfig)
 * 								 	import
 * 
 * JavaConfig2 + JavaConfig1
 * 
 * */


@Configuration
@Import({DVDConfig.class})
public class DVDPlayerConfig {
	
	//	생성자 주입
	//	하나 찾아서 bean지정 -> Qualifier
	@Bean
	public DVDPlayer dvdPlayer(@Qualifier("avangersInfinityWar") DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd);
	}
}
