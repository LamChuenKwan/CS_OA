package com.OA.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.OA.TO.User;
import com.OA.dao.UserMapper;
import com.OA.restTO.loginRestMsg;

@RestController
@RequestMapping("/user")
public class loginRest {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value = {"/testGet"}, method = RequestMethod.GET)
	public String testGet(String name,String pass) {
		System.out.print("name:"+name);
		System.out.println("pass:"+pass);
		return name+"|"+pass;
	}
	
	
	@RequestMapping(value = {"/selectAllUser"},method = RequestMethod.GET)
	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	
	@RequestMapping(value = {"/selectUserById"},method = RequestMethod.GET)
	public List<User> selectUserById(String userId){
		return userMapper.selectUserById(userId);
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public loginRestMsg login(@RequestBody User user) {
		loginRestMsg loginRestMsg = new loginRestMsg();
		loginRestMsg.init();
		if(user.getUserName() != null && !user.getUserName().equals("") 
				&& user.getPassword() !=null && !user.getPassword().equals("")){
			User reuslt = userMapper.logingUser(user.getUserName(), user.getPassword());
//			String reuslt = userMapper.logingUser(user);
//			System.out.println("reuslt:"+reuslt+"\n");
			if(reuslt != null) {
				loginRestMsg.setUser( reuslt);
				loginRestMsg.setRestMessage("ok");
			}
		}
		return loginRestMsg;
	}
	
	@RequestMapping(value = {"/getUserByToken"},method = RequestMethod.GET)
	public loginRestMsg getUserByToken(String token) {
		loginRestMsg loginRestMsg = new loginRestMsg();
		loginRestMsg.init();
		if(token != null && !token.equals("")) {
			User user = userMapper.getUserByToken(token);
			if(user !=null) {
				loginRestMsg.setUser(user);
				loginRestMsg.setRestMessage("ok");
			}
		}
		return loginRestMsg;
	}
	
}
