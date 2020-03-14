package com.OA.TO;

import java.util.List;

public class projectMembersTO {

  private int projectTeamId;
  private String projectId;
  private List<String> userList;
  private String userId;
public int getProjectTeamId() {
	return projectTeamId;
}
public void setProjectTeamId(int projectTeamId) {
	this.projectTeamId = projectTeamId;
}
public String getProjectId() {
	return projectId;
}
public void setProjectId(String projectId) {
	this.projectId = projectId;
}
public List<String> getUserList() {
	return userList;
}
public void setUserList(List<String> userList) {
	this.userList = userList;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
  
  

  
}
