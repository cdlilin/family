/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.swz.com.family.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.User;
import org.swz.com.family.service.LoginService;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
public class LoginController {

    private final static Log logger = LogFactory.getLog(LoginController.class);
    
    @Autowired
    private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	   * 判断用户是否登录
	   * @param currUser
	   * @return
	   */
	@ResponseBody
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public Result isLogin(@RequestBody Map<String,Object> reqMap){
		Result result = new Result("0", "");
	    Subject user = SecurityUtils.getSubject(); 
	    System.out.println(reqMap);
	    UsernamePasswordToken token = new UsernamePasswordToken(StringUtil.objToStr(reqMap.get("userName")), StringUtil.objToStr(reqMap.get("password")));
	    if(user.getSession().getAttribute("userName") != null && user.getSession().getAttribute("userName").equals(token.getUsername())){
	    	return result;
	    }
	    token.setRememberMe(true);
	    try {
	      user.login(token);
	      if(user.isAuthenticated()){
	    	  user.getSession().setAttribute("userName", token.getUsername());
	    	  result.setMessage("index");
	      }
	    } catch (UnknownAccountException uae) { 
	    	result.setCode("-1");
	    	result.setMessage("该用户名没有找到，请检查");
	    	logger.info("username wasn't in the system."); 

	    } catch (IncorrectCredentialsException ice) { 
	    	result.setCode("-1");
	    	result.setMessage("密码错误，请重新输入");
	    	logger.info("password didn't match."); 

	    } catch (LockedAccountException lae) { 
	    	result.setCode("-1");
	    	result.setMessage("该用户被锁定了，无法登陆");

	    } catch (AuthenticationException ae) { 
	    	result.setCode("-1");
	    	result.setMessage("请核对用户名和密码以后重新登陆"); 
	    }
	    
	    return result;
	  }  
	
	@ResponseBody
    @RequestMapping(value = "/loginForApp", method=RequestMethod.GET)
    public Result loginForApp(HttpServletRequest request, HttpServletResponse response){
		Result result = new Result("0", "");
	    Subject subject = SecurityUtils.getSubject();
	    String password = StringUtil.objToStr(request.getParameter("password"));
	    String userName = StringUtil.objToStr(request.getParameter("userName"));
	    User user = loginService.login(userName);  
	    
	    if(user != null){
	    	if(user.getPassword().equals(password)){
	    		UsernamePasswordToken token = new UsernamePasswordToken(StringUtil.objToStr(request.getParameter("userName")), StringUtil.objToStr(request.getParameter("password")));
	    	    
	    		if(subject.getSession().getAttribute("userName") != null && subject.getSession().getAttribute("userName").equals(token.getUsername())){
	    	    	return result;
	    	    }
	    	    token.setRememberMe(true);
	    	    try {
	    	    	subject.login(token); 
	    		      if(subject.isAuthenticated()){
	    		    	  ShiroRealm.ShiroUser shiroUser = (ShiroRealm.ShiroUser)subject.getPrincipal();
	    		    	  result.setData(shiroUser);
	    		    	  subject.getSession().setAttribute("userName", token.getUsername());
	    		    	  result.setMessage("index");
	    		      }
    		    } catch (UnknownAccountException uae) { 
    		    	
    		    } catch (IncorrectCredentialsException ice) { 
    		    	result.setCode("-1");
    		    	result.setMessage("密码错误，请重新输入");
    		    	logger.info("password didn't match."); 

    		    } catch (LockedAccountException lae) { 
    		    	result.setCode("-1");
    		    	result.setMessage("该用户被锁定了，无法登陆");

    		    } catch (AuthenticationException ae) { 
    		    	result.setCode("-1");
    		    	result.setMessage("请核对用户名和密码以后重新登陆"); 
    		    } 
	    	}else{
	    		result.setCode("-1");
		    	result.setMessage("密码错误");
		    	logger.info("username wasn't in the system.");  
	    	}
	    	
	    }else{
	    	result.setCode("-1");
	    	result.setMessage("该用户名没有找到，请检查");
	    	logger.info("username wasn't in the system."); 

	    } 
	    
	    return result;
	  } 
	
	@ResponseBody
    @RequestMapping(value = "/loginOutForApp", method=RequestMethod.GET)
    public Result loginOutForApp(HttpServletRequest request, HttpServletResponse response){
		Result result = new Result("0", "");
		try{
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		}catch(Exception e){
			result.setCode("-1");
			result.setMessage("退出失败");
		} 
	    return result;
	  } 

    @RequestMapping(value = "user/switch", method = RequestMethod.GET)
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        Object principal = currentUser.getPrincipal();
        if(principal == null) {
            return "redirect:../login";
        }
        String username = ((ShiroRealm.ShiroUser)principal).getUsername();
        currentUser.logout();
        logger.info("Logout:" + username + " logout!");
        return "redirect:../login";
    }

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	} 
	
}
