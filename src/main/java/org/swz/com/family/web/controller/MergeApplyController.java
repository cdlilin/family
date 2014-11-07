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
import org.swz.com.family.entity.MergeApply;
import org.swz.com.family.service.MergeApplyServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;
@Controller
@RequestMapping(value = "/mergeApply")
public class MergeApplyController {
	
		@Autowired
		private MergeApplyServer applyServer;
	
	    @ResponseBody
	    @RequestMapping(value = "/getApplayForUser", method = RequestMethod.GET)
	   	public List<MergeApply> getApplayForUser() {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("userId", user.getUserId());
	    	List<MergeApply> applies = applyServer.getApplyByUserId(map);
			return applies;
	   	} 
	    
	    /**
	     * @param applies
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/checkApples", method = RequestMethod.POST)
	   	public List<MergeApply> checkApples(@RequestBody List<MergeApply> applies) {
//	    	System.out.println(applies);
//	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//	    	List<Apply> applies = applyServer.getApplyByUserId(user.getUserId());
	    	for(MergeApply apply : applies){
	    		apply.setApplyResult(1);
	    		apply.setApplyStatus(1);
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
	    		 MergeApply app = new MergeApply();
		    	 app.setMergeApplyId(request.getParameter("mergeApplyId"));
		    	 app.setApplyFamilyId(request.getParameter("applyFamilyId"));
		    	 app.setMergeFamilyId(request.getParameter("mergeFamilyId"));
		    	 app.setMergeType(Integer.parseInt(request.getParameter("mergeType")));
		    	 app.setApplyResult(1);
		    	 app.setApplyStatus(1);
		    	 app.setApplyUserId(request.getParameter("applyUserId"));
		    	 app.setMergePersonId(request.getParameter("mergePersonId"));
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
	    		MergeApply app = new MergeApply();
	    		app.setMergeApplyId(request.getParameter("mergeApplyId"));
		        app.setApplyFamilyId(request.getParameter("applyFamilyId"));
		    	 app.setMergeFamilyId(request.getParameter("mergeFamilyId"));
		    	 app.setApplyResult(2);
		    	 app.setApplyStatus(1);
		    	 app.setApplyUserId(request.getParameter("applyUserId"));
		    	 app.setMergePersonId(request.getParameter("mergePersonId"));
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
	    		MergeApply app = new MergeApply();
	    		app.setMergeApplyId(request.getParameter("mergeApplyId"));
		        app.setApplyFamilyId(request.getParameter("applyFamilyId"));
		    	app.setMergeFamilyId(request.getParameter("mergeFamilyId"));
		    	app.setApplyResult(3);
		    	app.setApplyStatus(1);
		    	app.setApplyUserId(request.getParameter("applyUserId"));
		    	app.setMergePersonId(request.getParameter("mergePersonId"));
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
	    	MergeApply app = new MergeApply();
//    		app.setMergeApplyId(request.getParameter("mergeApplyId"));
//	        app.setApplyFamilyId(request.getParameter("applyFamilyId"));
//	    	 app.setMergeFamilyId(request.getParameter("mergeFamilyId"));
//	    	 app.setApplyResult(2);
//	    	 app.setApplyStatus(1);
//	    	 app.setApplyUserId(request.getParameter("applyUserId"));
//	    	 app.setMergePersonId(request.getParameter("mergePersonId"));
	    	applyServer.save(app);
			return result;
	   	}  
	    
	    @ResponseBody
	    @RequestMapping(value = "/sendApplyForApp", method = RequestMethod.POST)
	   	public Result sendApply(HttpServletRequest request, HttpServletResponse response) {
	    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
	    	Result result = new Result("0", "");  
	    	MergeApply app = new MergeApply();
    		app.setMergeApplyId(UUID.randomUUID().toString());
	        app.setApplyFamilyId(user.getFamilyId());
	    	app.setMergeFamilyId(request.getParameter("mergeFamilyId"));
	    	app.setMergeType(Integer.parseInt(request.getParameter("mergeType")));
	    	app.setApplyResult(0);
	    	app.setApplyStatus(0);
	    	app.setApplyUserId(user.getUserId());
	    	app.setMergePersonId(request.getParameter("mergePersonId"));
	    	applyServer.save(app);
			return result;
	   	}   
	    
	    
	    
	    @ResponseBody
		@RequestMapping(value = "/getApplayForFamilyAdmin", method = RequestMethod.GET)
		public List<MergeApply> getApplayForFamilyAdmin() { 
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			//为用户查找可能的家谱
			List<MergeApply> applies = applyServer.getApplyForFamilyAdmin(user.getPersonId());
			return applies;
		}

}
