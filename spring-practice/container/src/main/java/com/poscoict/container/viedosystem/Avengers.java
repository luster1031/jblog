package com.poscoict.container.viedosystem;

public class Avengers implements DigitalVideoDisc {
	private String studio = "MARVEL";
	private String title = "Avengers";

	@Override
	public void play() {
		System.out.println("Playing Movie "+ studio+ "MARVEL's " + title);
	}
}
