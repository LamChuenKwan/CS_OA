package com.OA.dao;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.OA.PTO.ProjectPTO;


@Mapper
public interface ProjectMapper {

//	@Insert("Insert into project(ProjectName,Start_Time,Target_Time,P_Descript) Values(#{projectName} , #{startTime} , #{targetTime}, #{descript})")
//	public  void creatProject(ProjectPTO pto);
	
	
//	@Insert("Insert into project(ProjectName,Start_Time,Target_Time,P_Descript) Values(#{0} , #{1} , #{2}, #{3})")
//	public  void creatProject(String projectName,Date startTime,Date targetTime,Blob descript);
	
	@Insert("Insert into project(ProjectName,Start_Time,Target_Time,P_Descript,State) Values(#{projectName} , #{startTime} , #{targetTime}, #{descript}, #{projectState})")
	@Options(useGeneratedKeys = true,keyProperty = "projectId")
	public  int creatProject(ProjectPTO pto);
	
	@Select("SELECT ProjectId AS projectId, ProjectName AS projectName,Start_Time AS startTime,"
			+ "Target_Time AS targetTime,Progress AS progress, "
			+ "State AS projectState  FROM project ")
	public List<ProjectPTO> getProjectList();
	
	
	@Select("SELECT ProjectId AS projectId, ProjectName AS projectName,Start_Time AS startTime,"
			+ " Target_Time AS targetTime,Progress AS progress, "
			+ " CAST( P_Descript  AS CHAR(10000) CHARACTER SET utf8 ) AS descript,State AS projectState  FROM project Where ProjectId = #{projectId}")
	public ProjectPTO getProjectById(Integer projectId);
	
	

	@Insert("Insert into project(ProjectName,Start_Time,Target_Time,P_Descript,State) Values(#{projectName} , #{startTime} , #{targetTime}, #{descript}, #{projectState})")
	@Options(useGeneratedKeys = true,keyProperty = "projectId")
	public  int creatProjectText(ProjectPTO pto);
	
	 String editProjectSQL= "UPDATE project SET projectName = #{projectName},Start_Time =#{startTime},Target_Time = #{targetTime},P_Descript=#{descript},State=#{projectState} where ProjectId = #{projectId} ;";
	
	@Update("UPDATE project SET projectName = #{projectName},Start_Time =#{startTime},Target_Time = #{targetTime},P_Descript=#{descript},State=#{projectState} where ProjectId = #{projectId} ;")
	@Options(useGeneratedKeys = true, keyProperty = "projectId")
	public int  editProjectById(ProjectPTO pto );
}
