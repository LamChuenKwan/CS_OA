package com.OA.restTO;

import com.OA.TO.User;

public class loginRestMsg {
	String restMessage;
	String token;
	User   user;
	
	public String getRestMessage() {
		return restMessage;
	}
	public void setRestMessage(String restMessage) {
		this.restMessage = restMessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public void init() {
		this.restMessage = "error";
		this.token ="";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
