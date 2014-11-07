package org.swz.com.family.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.Apply;
import org.swz.com.family.entity.FamilyIncludApply;
import org.swz.com.family.service.ApplyServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;
@Controller
@RequestMapping(value = "/apply")
public class ApplyController {
	
		@Autowired
		private ApplyServer applyServer;
	
	    @ResponseBody
	    @RequestMapping(value = "/getApplayForUser", method = RequestMethod.GET)
	   	public List<Apply> getApplayForUser() {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("userId", user.getUserId());
	    	List<Apply> applies = applyServer.getApplyByUserId(map);
			return applies;
	   	} 
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/checkApples", method = RequestMethod.POST)
	   	public List<Apply> checkApples(@RequestBody List<Apply> applies) {
//	    	System.out.println(applies);
//	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//	    	List<Apply> applies = applyServer.getApplyByUserId(user.getUserId());
	    	for(Apply apply : applies){
	    		apply.setApplyResult(1);
	    		apply.setIsDone(1);
	    		applyServer.updateApplyStatus(apply);
	    	}; 
			return applies;
	   	}  
	    
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/checkAppleForApp", method = RequestMethod.POST)
	   	public Result checkApples(HttpServletRequest request, HttpServletResponse response) {
//	    	System.out.println(applies);
//	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//	    	List<Apply> applies = applyServer.getApplyByUserId(user.getUserId());
	    	Result result = new Result("0", "");
	    	try{
		    	 Apply app = new Apply();
		    	 app.setApplyId(request.getParameter("applyId"));
		    	 app.setApplyFamily(request.getParameter("applyFamily"));
		    	 app.setApplyPersonId(request.getParameter("applyPersonId"));
		    	 app.setApplyResult(1);
		    	 app.setIsDone(1);
		    	 app.setApplyUserId(request.getParameter("applyUserId"));
		    	 app.setApplyValidate(request.getParameter("applyValidate"));
		    	 applyServer.updateApplyStatus(app);
	    	}catch(Exception e){
	    		result.setCode("-1"); 
	    	}
			return result;
	   	}  
	    
	    
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/deleteApples", method = RequestMethod.POST)
	   	public Result checkApples(@RequestBody Map<String,Object> reqMap) {
//	    	System.out.println(applies);
//	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//	    	List<Apply> applies = applyServer.getApplyByUserId(user.getUserId());
	    	Result result = new Result("0", "");
	    	applyServer.deleteApples(StringUtil.objToStr(reqMap.get("ids")));
	    	 
			return result;
	   	}  
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/deleteAppleForApp", method = RequestMethod.POST)
	   	public Result deleteAppleForApp(HttpServletRequest request, HttpServletResponse response) {
	    	Result result = new Result("0", "");
	    	try{
		    	 Apply app = new Apply();
		    	 app.setApplyId(request.getParameter("applyId"));
		    	 app.setApplyFamily(request.getParameter("applyFamily"));
		    	 app.setApplyPersonId(request.getParameter("applyPersonId"));
		    	 app.setApplyResult(2);
		    	 app.setIsDone(1);
		    	 app.setApplyUserId(request.getParameter("applyUserId"));
		    	 app.setApplyValidate(request.getParameter("applyValidate"));
		    	 applyServer.updateApplyStatus(app);
	    	}catch(Exception e){
	    		result.setCode("-1"); 
	    	}
			return result; 
	   	}  
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/cancelAppleForApp", method = RequestMethod.POST)
	   	public Result cancelAppleForApp(HttpServletRequest request, HttpServletResponse response) {
	    	Result result = new Result("0", "");
	    	try{
		    	 Apply app = new Apply();
		    	 app.setApplyId(request.getParameter("applyId"));
		    	 app.setApplyFamily(request.getParameter("applyFamily"));
		    	 app.setApplyPersonId(request.getParameter("applyPersonId"));
		    	 app.setApplyResult(3);
		    	 app.setIsDone(1);
		    	 app.setApplyUserId(request.getParameter("applyUserId"));
		    	 app.setApplyValidate(request.getParameter("applyValidate"));
		    	 applyServer.updateApplyStatus(app);
	    	}catch(Exception e){
	    		result.setCode("-1"); 
	    	}
			return result; 
	   	}  
	    
	    
	    
	    
	    
	    
	    
	    @ResponseBody
	    @RequestMapping(value = "/sendApply", method = RequestMethod.POST)
	   	public Result sendApply(@RequestBody Map<String,Object> reqMap) {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Result result = new Result("0", "");  
	    	Apply apply = new Apply();
	    	apply.setApplyUserId(user.getUserId());
	    	apply.setApplyFamily(StringUtil.objToStr(reqMap.get("familyId")));
	    	apply.setApplyId(UUID.randomUUID().toString());
	    	apply.setApplyPersonId(StringUtil.objToStr(reqMap.get("personId")));
	    	apply.setApplyValidate(StringUtil.objToStr(reqMap.get("applyValidate")));
	    	applyServer.save(apply);
			return result;
	   	}  
	    
	    @ResponseBody
	    @RequestMapping(value = "/sendApplyForApp", method = RequestMethod.POST)
	   	public Result sendApply(HttpServletRequest request, HttpServletResponse response) {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Result result = new Result("0", "");  
	    	Apply apply = new Apply();
	    	apply.setApplyUserId(user.getUserId());
	    	apply.setApplyFamily(request.getParameter("familyId"));
	    	apply.setApplyId(UUID.randomUUID().toString());
	    	apply.setApplyPersonId(request.getParameter("personId"));
	    	apply.setApplyValidate(request.getParameter("applyValidate"));
	    	applyServer.save(apply);
			return result;
	   	}  
	    
	    @ResponseBody
	    @RequestMapping(value = "/sendAreaFamApplyForApp", method = RequestMethod.POST)
	   	public Result sendAreaFamApplyForApp(HttpServletRequest request, HttpServletResponse response) {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Result result = new Result("0", "");  
//	    	FamilyIncludApply apply = new FamilyIncludApply();
//	    	apply.setApplyUserId(user.getUserId());
//	    	apply.setApplyFamilyId(request.getParameter("familyId"));
//	    	apply.setApplyId(UUID.randomUUID().toString());
//	    	apply.setApplyIncludeFamilyId(request.getParameter("includFamilyId"));
//	    	apply.setApplyValidate(request.getParameter("applyValidate"));
//	    	applyServer.save(apply);
			return result;
	   	}  
	    
	    
	    
	    @ResponseBody
		@RequestMapping(value = "/getApplayForFamilyAdmin", method = RequestMethod.GET)
		public List<Apply> getApplayForFamilyAdmin() { 
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			//为用户查找可能的家谱
			List<Apply> applies = applyServer.getApplyForFamilyAdmin(user.getPersonId());
			return applies;
		}

}
