package com.poscoict.mysite.vo;

public class SiteVo {
	private String title;
	private String welcomeMessage;
	private String profile;
	private String description;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [title=" + title + ", welcomeMessage=" + welcomeMessage + ", profile=" + profile
				+ ", description=" + description + "]";
	}
	
	
	
}
