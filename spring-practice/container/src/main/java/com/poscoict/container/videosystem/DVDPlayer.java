package com.poscoict.container.videosystem;


public class DVDPlayer {
	
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
