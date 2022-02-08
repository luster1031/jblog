package com.poscoict.container.viedosystem;

import org.springframework.beans.factory.annotation.Autowired;

public class DVDPlayer {
	@Autowired
	private DigitalVideoDisc dvd;
	
	
	public DigitalVideoDisc getDvd() {
		return dvd;
	}
	public void setDvd(DigitalVideoDisc dvd) {
		this.dvd = dvd;
	}
	public DVDPlayer(DigitalVideoDisc avengers) {
		this.dvd = avengers;
	}
	public DVDPlayer() {
		
	}
	public void play() {
		dvd.play();
	}

}
