package com.tcs.slackintegration.utility;

public class Payload {
	
	private String text;
	private String command;
	private String token;
	private String channel_id;
	private String channel_name;
	private String user_name;
	private String response_url;
	private String team_id;
	private String team_domain;
	private String enterprise_id;
	private String enterprise_name;
	private String trigger_id;
	private String api_app_id;
	private String user_id;
	private String response_type;
	
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	
	@Override
	public String toString() {
		return "Payload [text=" + text + ", command=" + command + ", token=" + token + ", channel_id=" + channel_id
				+ ", channel_name=" + channel_name + ", user_name=" + user_name + ", response_url=" + response_url
				+ ", team_id=" + team_id + ", team_domain=" + team_domain + ", enterprise_id=" + enterprise_id
				+ ", enterprise_name=" + enterprise_name + ", trigger_id=" + trigger_id + ", api_app_id=" + api_app_id
				+ ", user_id=" + user_id + ", response_type=" + response_type + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getResponse_url() {
		return response_url;
	}
	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getTeam_domain() {
		return team_domain;
	}
	public void setTeam_domain(String team_domain) {
		this.team_domain = team_domain;
	}
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	public String getEnterprise_name() {
		return enterprise_name;
	}
	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}
	public String getTrigger_id() {
		return trigger_id;
	}
	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}
	public String getApi_app_id() {
		return api_app_id;
	}
	public void setApi_app_id(String api_app_id) {
		this.api_app_id = api_app_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
