package com.OA.Rest.project;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OA.PTO.ProjectPTO;
import com.OA.TO.ProjectTO;
import com.OA.TO.User;
import com.OA.TO.projectMembersTO;
import com.OA.dao.ProjectMapper;
import com.OA.dao.UserMapper;
import com.OA.restTO.ProjectMsg.ProjectRestMsg;

@RestController
@RequestMapping("/project")
public class projectRest {
	
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value = {"/creatProject"},method = RequestMethod.POST)
	public ProjectRestMsg creatProject(@RequestBody ProjectTO to)  {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		if(to != null) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//				Blob blob = new SerialBlob(to.getDescript().getBytes("GBK"));
//				projectMapper.creatProject(to.getProjectName(), format.parse(to.getStartTime()), format.parse(to.getStartTime()), blob);
//				projectMapper.creatProject(to.getProjectName(), format.parse(to.getStartTime()), format.parse(to.getStartTime()), to.getDescript(),to.getProjectState());
				ProjectPTO pto=new ProjectPTO();
				pto.setProjectName(to.getProjectName());
				pto.setStartTime(format.parse(to.getStartTime()));
				pto.setTargetTime(format.parse(to.getTargetTime()));
				pto.setDescript(to.getDescript());
				pto.setProjectState(to.getProjectState());
				projectMapper.creatProjectText(pto);
				
				if(pto.getProjectId() > 0) {
					msg.setProjectId(String.valueOf(pto.getProjectId()));
					msg.setRestMessage("ok");
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
				msg.setRestMessage("e.getMessage()");
			}
		}
		return msg;
	}
	
	@RequestMapping(value = {"/getPrjectList"},method = RequestMethod.GET)
	public ProjectRestMsg getProjectList() {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		
		try {
			List<ProjectPTO> listPTO = new ArrayList<ProjectPTO>();
			List<ProjectTO> list = new ArrayList<ProjectTO>();
			listPTO = projectMapper.getProjectList();
			
			if(listPTO.size() >0) {
				SimpleDateFormat spFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				for (int i = 0; i < listPTO.size(); i++) {
					ProjectTO to = new ProjectTO();
					to.setProjectId( String.valueOf( listPTO.get(i).getProjectId() ) );
					to.setProjectName(listPTO.get(i).getProjectName());
					to.setStartTime(spFormat.format(listPTO.get(i).getStartTime()));
					to.setTargetTime(spFormat.format(listPTO.get(i).getTargetTime()));
//					String str = new String(listPTO.get(i).getDescript().getBytes(1,(int) listPTO.get(i).getDescript().length()),"UTF-8");
//					System.out.print(listPTO.get(i).getDescript()+"-------\n");
					to.setProgress(progressToStr(listPTO.get(i).getProgress()));
					to.setProjectState(listPTO.get(i).getProjectState());
					list.add(to);
				}
				
				if(list.size() > 0) {
					msg.setProjectTOList(list);
					msg.setRestMessage("ok");
				}
			}else {
				msg.setRestMessage("projectList is null");
			}
			
		} catch (Exception e) {
			msg.setRestMessage("error");
		}
		
		return msg;
	}
	
	private String  progressToStr(float progress) {
		String str = String.valueOf(Math.round((progress * 100))) + "%";
		
		return str;
	}
	
	@RequestMapping(value = { "/getAllUserList"}, method = RequestMethod.GET)
	public ProjectRestMsg getAllUserList() {
		ProjectRestMsg rest = new ProjectRestMsg();
		rest.init();
		List<User> userToList = new ArrayList<User>();
		try {
			userToList = userMapper.getAllUserList();
			if( userToList.size() > 0) {
				rest.setUserTOList(userToList);
				rest.setRestMessage("ok");
			}
		} catch (Exception e) {
			rest.setRestMessage(e.getMessage());
		}
		return rest;
	}
	
	@RequestMapping(value = {"/getUserListByDepartment"}, method = RequestMethod.GET)
	public ProjectRestMsg getUserListByDepartment(String department) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		
		try {
			if(department !=null && !department.equals("")) {
				List<User> userList = new ArrayList<User>();
				userList = userMapper.getUserListByDepartment(department);
				
				if(userList.size() > 0) {
					msg.setUserTOList(userList);
					msg.setRestMessage("ok");
				}
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		
		return msg;
	}
	
	@RequestMapping(value = {"/getKXUserListByDepartment"},method = RequestMethod.GET)
	public ProjectRestMsg getKXUserListByDepartment(String projectId,String department) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();

		try {
			
			if(projectId !=null && !projectId.equals("") && department != null && !department.equals("")) {
				List<User> userList = new ArrayList<User>();
				userList = userMapper.getKXUserListByDepartment(Integer.parseInt(projectId), department);
				
				if(userList.size() >0) {
					msg.setUserTOList(userList);
					msg.setRestMessage("ok");
				}
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = {"/getYXUserListByProjectId"} , method = RequestMethod.GET)
	public ProjectRestMsg getYXUserListByProjectId(String projectId){
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		
		try {
			
			if(projectId != null  && !projectId.equals("")) {
				List<User> userList = new ArrayList<User>();
				userList = userMapper.getYXUserListByProjectId(Integer.parseInt(projectId));
				
				if(userList.size() >0) {
					msg.setUserTOList(userList);
					msg.setRestMessage("ok");
				}
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	
	//-------------------------------------------test----------------------------------------------------
	@RequestMapping(value = {"/testCreateProject"} , method = RequestMethod.POST)
	public ProjectRestMsg createProjectText(@RequestBody ProjectTO to) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		
		try {
			
			if(to != null) {
				SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
//				int  projectId = 0;
//				int projectId2 = projectMapper.creatProjectText(projectTO.getProjectName(), simp.parse(projectTO.getStartTime()), simp.parse(projectTO.getTargetTime()), projectTO.getDescript(), projectTO.getProjectState(),projectId);
////				 projectMapper.creatProjectText(projectTO.getProjectName(), simp.parse(projectTO.getStartTime()), simp.parse(projectTO.getTargetTime()), projectTO.getDescript(), projectTO.getProjectState());
//				System.out.print(projectId+"-------prjectId1--------------\n");
//				System.out.print(projectId2+"-------prjectId2--------------\n");
				ProjectPTO pto=new ProjectPTO();
				pto.setProjectName(to.getProjectName());
				pto.setStartTime(simp.parse(to.getStartTime()));
				pto.setTargetTime(simp.parse(to.getTargetTime()));
				pto.setDescript(to.getDescript());
				pto.setProjectState(to.getProjectState());
				 int id =projectMapper.creatProjectText(pto);
				System.out.print(pto.getProjectId()+"-------pto--------------\n");
				System.out.print(id+"-------id--------------\n");
//				if(projectId > 0) {
//					msg.setProjectId(String.valueOf(projectId));
					msg.setRestMessage("ok");
//				}
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = {"/getProjectById"},method = RequestMethod.GET)
	public ProjectRestMsg getProjectById(String projectId) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		
		try {
			if(projectId != null && !projectId.equals("")) {
				
				ProjectTO to = new ProjectTO();
				ProjectPTO pto=	projectMapper.getProjectById(Integer.parseInt(projectId));
				SimpleDateFormat dataformat=new SimpleDateFormat("yyyy-MM-dd");

				if(pto != null) {
					to.setProjectId( String.valueOf(pto.getProjectId()));
					to.setProjectName(pto.getProjectName());
					to.setStartTime(dataformat.format(pto.getStartTime()));
					to.setTargetTime(dataformat.format(pto.getTargetTime()));
					to.setDescript(pto.getDescript());
					to.setProgress(progressToStr(pto.getProgress()));
					to.setProjectState(pto.getProjectState());
					msg.setProjectTO(to);
					msg.setRestMessage("ok");
				}
				
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = {"/deleteTeamByUserId"}, method = RequestMethod.GET)
	public ProjectRestMsg deleteTeamByUserId(String projectId,String userId) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		try {
			if(null != projectId && !projectId.equals("") && null != projectId && !userId.equals("")) {
				userMapper.deleteTeamByUserId(Integer.valueOf(projectId), userId);
				msg.setRestMessage("ok");
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = {"/insertTeamByUserId"}, method = RequestMethod.POST)
	public ProjectRestMsg insertTeamByUserId(@RequestBody projectMembersTO to) {
		ProjectRestMsg msg = new ProjectRestMsg();
		msg.init();
		try {
			
			if( !to.getProjectId().equals("") && to.getUserList().size() > 0) {

				for(int i=0; i<to.getUserList().size() ; i++) {

					projectMembersTO pto = new projectMembersTO();
					pto.setProjectId(to.getProjectId());
					pto.setUserId(to.getUserList().get(i));
					userMapper.insertTeamByUserId(pto);
					if(pto.getProjectTeamId() < 0) {
						msg.setRestMessage("error");
						return msg;
					}
				}
				msg.setRestMessage("ok");
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = {"/editProjectById"} , method = RequestMethod.POST)
	public ProjectRestMsg editProjectById(@RequestBody ProjectTO to) {
		ProjectRestMsg  msg = new ProjectRestMsg();
		msg.init();
		try {
			
			if(to != null) {
				SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
				ProjectPTO pto = new ProjectPTO();
				pto.setProjectId(Integer.valueOf(to.getProjectId()));
				pto.setProjectName(to.getProjectName());
				pto.setStartTime( simple.parse(to.getStartTime()));
				pto.setTargetTime(simple.parse(to.getTargetTime()));
				pto.setDescript(to.getDescript());
				pto.setProjectState(to.getProjectState());
				projectMapper.editProjectById(pto);
				
				if(pto.getProjectId() > 0) {
					msg.setRestMessage("ok");
					msg.setProjectId(String.valueOf(pto.getProjectId()));
				}
				
			}
		} catch (Exception e) {
			msg.setRestMessage(e.getMessage());
		}
		
		return msg;
	}
	
	@RequestMapping("/uploadProjectFile")

	public void uploadProjectFile() {
		
	}
}
