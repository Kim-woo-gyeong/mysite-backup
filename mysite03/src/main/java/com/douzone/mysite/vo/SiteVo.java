package com.douzone.mysite.vo;

public class SiteVo {
	private long no;
	private String title;
	private String description;
	private String welcomeMessage;
	private String profileURL;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", description=" + description + ", welcomeMessage="
				+ welcomeMessage + ", profileURL=" + profileURL + "]";
	}
	
	
}
