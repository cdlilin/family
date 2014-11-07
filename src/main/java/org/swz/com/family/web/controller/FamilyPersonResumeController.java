package org.swz.com.family.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.FamilyPersonResume;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.service.FamilyPersonResumeServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;

@Controller
@RequestMapping(value = "/resume")
public class FamilyPersonResumeController {
	
	@Autowired
	private FamilyPersonResumeServer familyPersonResumeServer;
	
	@ResponseBody
	@RequestMapping(value = "/getResumesByPersonId", method = RequestMethod.POST)
	public Result getResumesByPersonId(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		
		List<FamilyPersonResume> familyPersonResume = familyPersonResumeServer.selectFamilyPersonResumeByPersonId(request.getParameter("personId"));
		result.setData(familyPersonResume);
		return result;
	}
	
    @ResponseBody
   	@RequestMapping(value = "/getFamilyContribution", method = RequestMethod.POST)
   	public Result getFamilyContribution(HttpServletRequest request, HttpServletResponse response) { 
       	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
       	Result result = new Result("0", "");
   	    int pageNum = StringUtil.strToInteger(request.getParameter("pageNum"));
        int pageSize = StringUtil.strToInteger(request.getParameter("pageSize"));
  		//为用户查找可能的家谱
        Page page = familyPersonResumeServer.getFamilyContribution(user.getPersonId(), pageNum, pageSize);
  		result.setData(page);
  		return result;
     } 

}
