package org.swz.com.family.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.exception.BusinessException;
import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.UserDao;
import org.swz.com.family.service.LoginService;
import org.swz.com.family.service.ShiroRealm;
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
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			User user = new User(); 
			user.setId(UUID.randomUUID().toString());
			user.setUserName((String)reqMap.get("userName"));
			user.setPassword((String)reqMap.get("password1"));  
			user.setEmail((String)reqMap.get("email"));
			userServer.saveUser(user);  
			result.setData(user);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("-1");
			result.setMessage("注册失败");
		}
		return result;
	} 
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(String a, String b, String k, String  c) {  
		if(userServer.confirmUser(a, c)){
			return "redirect:../login";
		}else{
			return "error/500";
		}
	}  
	
	@ResponseBody
	@RequestMapping(value = "/regUserForApp", method = RequestMethod.POST)
	public Result regUserForApp(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			User user = new User(); 
			user.setId(UUID.randomUUID().toString());
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password1"));  
			user.setEmail(request.getParameter("email"));
			userServer.saveUser(user);  
			result.setData(user);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("-1");
			result.setMessage("注册失败");
		}
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	public Result setPassword(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			//发送短信
			User user = new User(); 
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			
			userServer.setPassword(user);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("-1");
			result.setMessage("注册失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateConfirmCode", method = RequestMethod.POST)
	public Result validateConfirmCode(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			String userName = request.getParameter("userName");
			String confirmCode = request.getParameter("confirmCode");
			
			userServer.validateConfirmCode(userName, confirmCode);
			//发送短信 
			
		}catch(BusinessException e){
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			result.setCode("-1");
			result.setMessage("未知错误请重试");
		}
		return result;
	} 
	
	
	@ResponseBody
	@RequestMapping(value = "/validateConfirmCodeForFindPassword", method = RequestMethod.POST)
	public Result validateConfirmCodeForFindPassword(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			String userName = request.getParameter("userName");
			String confirmCode = request.getParameter("confirmCode");
			
			userServer.validateConfirmCodeForFindPassword(userName, confirmCode);
			//发送短信 
			
		}catch(BusinessException e){
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			result.setCode("-1");
			result.setMessage("未知错误请重试");
		}
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/sendFindPasswordConfirmRequest", method = RequestMethod.GET)
	public Result sendFindPasswordConfirmRequest(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			//发送短信  
			User user = userServer.sendFindPasswordConfirmRequest(request.getParameter("userName"));
			
			result.setData(user);
		}catch(BusinessException e){
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("-1");
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendConfirmRequest", method = RequestMethod.GET)
	public Result sendConfirmRequest(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Result result = new Result("0", "注册成功，请到你的邮箱确认");
		try{
			//发送短信  
			User user = userServer.sendConfirmCode(request.getParameter("userName"));
			result.setData(user);
		}catch(BusinessException e){
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("-1");
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping(value = "/userSpace", method = RequestMethod.GET)
	public String personInfo() {
		return "user/userSpace";
	}  
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile() {
		return "user/profile";
	} 

	@RequestMapping(value = "emailRedirect/{url}", method = RequestMethod.GET)
	public String gotologin(@PathVariable String url, Model model){
		model.addAttribute("url", "http://mail." + (url.substring(url.indexOf("@") + 1)));
		model.addAttribute("message", "注册成功，我们将为你跳转到你的注册邮箱，请您确认");
		return "emailRedirect";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getpermission", method = RequestMethod.GET)
	public Result getpermission(HttpServletRequest request, HttpServletResponse response){
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		result.setData(user.getPermissionList());
		return result;
	}

}
