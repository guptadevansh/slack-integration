package com.tcs.slackintegration.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agents")
public class Agents {

	@Id
	@Column(name = "user_id")
	private String user_id;

	public Agents() {
		super();
	}
	
	public Agents(String user_id) {
		super();
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Agents [user_id=" + user_id + "]";
	}
	
}
