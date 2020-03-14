package com.OA.TO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	
private String userId;
private String userName;
private String password;
private String userSex;
private String userdepartment;
private String userposition;
private String permission;
private String doublecheckPassword;
private String token;

public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUserSex() {
	return userSex;
}
public void setUserSex(String userSex) {
	this.userSex = userSex;
}
public String getUserdepartment() {
	return userdepartment;
}
public void setUserdepartment(String userdepartment) {
	this.userdepartment = userdepartment;
}
public String getUserposition() {
	return userposition;
}
public void setUserposition(String userposition) {
	this.userposition = userposition;
}
public String getPermission() {
	return permission;
}
public void setPermission(String permission) {
	this.permission = permission;
}
public String getDoublecheckPassword() {
	return doublecheckPassword;
}
public void setDoublecheckPassword(String doublecheckPassword) {
	this.doublecheckPassword = doublecheckPassword;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}



}
