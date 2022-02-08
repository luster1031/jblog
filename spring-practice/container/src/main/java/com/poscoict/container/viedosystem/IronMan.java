package com.poscoict.container.viedosystem;

public class IronMan implements DigitalVideoDisc {
	private String studio = "MARVEL";
	private String title = "Avengers";

	@Override
	public void play() {
		System.out.println("Playing Movie " + studio + "'s " + title);
	}
	
}
