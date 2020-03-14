package com.OA.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.OA.TO.User;
import com.OA.dao.UserMapper;

@RestController
@RequestMapping("/OA_registered")
public class registered {

	@Autowired 
	UserMapper userMapper;
	
	@RequestMapping(value = {"/registeredUser"}, method = RequestMethod.POST )
	public String registeredUser(@RequestBody User user) {
		String restToken ="error";
		if(user!=null) {
			String id= user.getUserId();
			String token = user.getUserId()+"|"+user.getUserName()+"|"+user.getPermission();
			user.setToken(token);
			userMapper.addUser(user);
			restToken= userMapper.getTokenByUserID(id);
			if(restToken.equals("") || restToken == null) {
				restToken ="error";
			}
		}else {
			return "error";
		}
		return restToken;
	}
	
	@RequestMapping(value = {"/checkUserNameDouble"}, method = RequestMethod.GET)
	public String  checkUserNameDouble(String userName) {
		String reuslt ="error";
		if(userName != null && !userName.equals("")) {
			String userId = userMapper.checkUserName(userName);
			if( userId == null ||userId.equals("")) {
				reuslt = "ok";
			}
		}
		return reuslt;
	}
	
	
}
