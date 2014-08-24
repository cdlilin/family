package org.swz.com.family.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.Apply;
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
	    	List<Apply> applies = applyServer.getApplyByUserId(user.getUserId());
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
		@RequestMapping(value = "/getApplayForFamilyAdmin", method = RequestMethod.GET)
		public List<Apply> getApplayForFamilyAdmin() { 
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			//为用户查找可能的家谱
			List<Apply> applies = applyServer.getApplyForFamilyAdmin(user.getPersonId());
			return applies;
		}

}
