package com.tcs.slackintegration.utility;

public class Message {

	private String type;
	private String user;
	private String text;
	private String ts;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	@Override
	public String toString() {
		return "History [type=" + type + ", user=" + user + ", text=" + text + ", ts=" + ts + "]";
	}
}

