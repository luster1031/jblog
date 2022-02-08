package com.poscoict.container.config.viedosystem;

import com.poscoict.container.viedosystem.DigitalVideoDisc;

public class Avengers implements DigitalVideoDisc {
	private String studio="MARVEL";
	private String title = "Avengers";
	@Override
	public void play() {
		System.out.println("Playing Movie "+ studio + "'s " + title);
	}
}
