package org.swz.com.family.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.entity.User;
import org.swz.com.family.service.LoginService;
import org.swz.com.family.service.UserService;
import org.swz.com.family.web.dto.Result;

/**
 * Created by star on 5/15/14.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UserService userServer;

    @RequestMapping(value = "{username}/account", method = RequestMethod.GET)
    public List<Map<String, Object>> getAccounts(@PathVariable String username) {
        logger.debug("-X GET /user/" + username + "/account");
        return loginService.getAccountByLoginName(username);
    } 
    
	@ResponseBody
    @RequestMapping(value = "/getAccountForPage", method = RequestMethod.GET)
    public List<User> getAccountForPage() {
        logger.debug("-X GET /user/"  + "/getAccountForPage");
        return loginService.getAccountForPage(null);
    } 
    
	@ResponseBody
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public Result regUser(@RequestBody Map<String,Object> reqMap) { 
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUserName((String)reqMap.get("userName"));
		user.setPassword((String)reqMap.get("password1"));
		userServer.saveUser(user);
		
		 
		
		return new Result("0", "");
	} 
	
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public String personInfo() {
		return "user/personInfo";
	} 
	
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile() {
		return "user/profile";
	} 


}
