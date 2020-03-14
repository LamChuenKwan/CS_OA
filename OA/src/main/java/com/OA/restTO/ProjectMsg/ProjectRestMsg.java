package com.OA.restTO.ProjectMsg;

import java.util.List;

import com.OA.TO.ProjectTO;
import com.OA.TO.User;
import com.OA.TO.projectMembersTO;

public class ProjectRestMsg {
	
	String restMessage;
	List<ProjectTO> projectTOList;
	List<User> userTOList;
	String projectId;
	ProjectTO projectTO;
	projectMembersTO projectMembersTO;

	public List<ProjectTO> getProjectTOList() {
		return projectTOList;
	}


	public void setProjectTOList(List<ProjectTO> projectTOList) {
		this.projectTOList = projectTOList;
	}


	public String getRestMessage() {
		return restMessage;
	}


	public void setRestMessage(String restMessage) {
		this.restMessage = restMessage;
	}


	public List<User> getUserTOList() {
		return userTOList;
	}


	public void setUserTOList(List<User> userTOList) {
		this.userTOList = userTOList;
	}


	public void init() {
		this.restMessage = "error";
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public ProjectTO getProjectTO() {
		return projectTO;
	}


	public void setProjectTO(ProjectTO projectTO) {
		this.projectTO = projectTO;
	}


	public projectMembersTO getProjectMembersTO() {
		return projectMembersTO;
	}


	public void setProjectMembersTO(projectMembersTO projectMembersTO) {
		this.projectMembersTO = projectMembersTO;
	}
	
}
