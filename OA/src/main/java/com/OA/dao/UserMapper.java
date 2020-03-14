package com.OA.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.OA.TO.User;
import com.OA.TO.projectMembersTO;

@Mapper
public interface UserMapper {
	
	@Select("select Token from user Where UserID = #{userId}")
	public String getTokenByUserID(String userId);

	@Select("SELECT * FROM USER")
	public List<User> selectAllUser();
	
	@Select("SELECT * FROM USER WHERE UserID = #{userId}")
	public List<User> selectUserById(String userId);
	
	
	@Update("Update user set UserName = #{userName} ,Password = #{password},UserSex = #{userSex}")
	public void updateUserById(User user);
	
	@Delete("Delete from user where UserID = #{userId}")
	public void deleteUserById(int userId);
	
	
	
	@Select("select UserID from user Where UserName = #{userName}")
	public String checkUserName(String userName);
	
	//Start Login -------------------------
	@Select("select UserID,UserName,UserDepartment,UserPosition,Permission,Token from user Where UserName = #{0} && Password = #{1}")
	public User logingUser(String userName, String password);
	
	//	@Select("select Token from user Where UserName = #{userName} && Password = #{password}")
	//	public String logingUser(User user);
	
	//end 
	
	
	//rgistered Start
	@Insert("Insert into user(UserID,UserName,Password,UserSex,UserDepartment,UserPosition,Permission,Token) "
			+ "values(#{userId},#{userName},#{password},#{userSex},#{userdepartment},#{userposition},#{permission},#{token})")
	public void addUser(User user);
	//end
	
	//getUserBytoken
	@Select("select UserID,UserName,UserDepartment,UserPosition,Permission,Token from user where Token = #{token}")
	public User getUserByToken(String  token);

	@Select("SELECT UserID AS userId,UserName AS userName,UserSex AS userSex, "
			+ "UserDepartment AS userdepartment,UserPosition AS userposition FROM USER;")
	public List<User> getAllUserList();
	
	@Select("SELECT UserID AS userId,UserName AS userName,UserSex AS userSex, "
			+ "UserDepartment AS userdepartment,UserPosition AS userposition FROM USER WHERE UserDepartment = #{department};")
	public List<User> getUserListByDepartment(String department);
	
	String  getUnMPUserListByDepartmentSQL ="SELECT u.UserID AS userId, u.UserName AS userName, u.UserSex AS userSex, u.UserDepartment AS userdepartment, "
			+ " u.UserPosition AS userposition FROM USER u LEFT JOIN  (SELECT UserId AS uid FROM projectmembers  WHERE ProjectId = #{0}) p"
			+ " ON u.userId = p.uid"
			+ " WHERE p.uid IS NULL AND u.userdepartment = #{1}";
	
	@Select(getUnMPUserListByDepartmentSQL)
	public List<User> getKXUserListByDepartment(Integer projectId, String department);
	
	String getYXUserListByProjectId ="SELECT u.UserID AS userId, u.UserName AS userName, u.UserSex AS userSex, u.UserDepartment AS userdepartment, u.UserPosition AS userposition "
			+ "FROM USER u LEFT JOIN projectmembers p ON u.UserID = p.UserId WHERE p.projectId = #{0};";
	
	@Select(getYXUserListByProjectId)
	public List<User> getYXUserListByProjectId(Integer projectId);
	
	@Delete("DELETE FROM projectmembers WHERE ProjectId = #{0} AND UserId = #{1}")
	public void deleteTeamByUserId(Integer projectId,String userId);
	
//	@Insert("INSERT INTO projectmembers(ProjectId,UserId) VALUES( #{0}, #{1});")
//	public int insertTeamByUserId(Integer projectId, String userId);
	
	@Insert("INSERT INTO projectmembers(ProjectId,UserId) VALUES( #{projectId}, #{userId});")
	@Options(useGeneratedKeys = true , keyProperty = "projectTeamId")
	public int insertTeamByUserId(projectMembersTO to);
}
