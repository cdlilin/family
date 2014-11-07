package org.swz.com.family.web.controller;

import java.util.Date;
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
import org.swz.com.family.entity.FamilyTalkAgree;
import org.swz.com.family.entity.Talk;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.service.TalkServer;
import org.swz.com.family.web.dto.Result;

@Controller
@RequestMapping(value = "/talk")
public class TalkController {
	
	@Autowired
	private TalkServer talkServer;
	
	
	@ResponseBody
    @RequestMapping(value = "/getTalks", method = RequestMethod.POST)
    public List<Talk> getTalks(@RequestBody Map<String,Object> reqMap) {
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		
		int startIndex = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("startIndex")));
        int endIndex = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("endIndex")));
		return talkServer.getTalks(startIndex, endIndex, user.getPersonId());
    } 
	
	
	@ResponseBody
    @RequestMapping(value = "/getTalksForApp", method = RequestMethod.POST)
    public Result getTalksForApp(HttpServletRequest request, HttpServletResponse response) {
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal(); 
       	Result result = new Result("0", "");
   	    int pageNum = StringUtil.strToInteger(request.getParameter("pageNum"));
        int pageSize = StringUtil.strToInteger(request.getParameter("pageSize"));
        int talkFocus = StringUtil.strToInteger(request.getParameter("talkFocus"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("talkFocus", talkFocus);
        map.put("userId", user.getUserId());
        if(request.getParameter("searchContent") != null){
        	 map.put("searchContent", request.getParameter("searchContent"));
        }
  		//为用户查找可能的家谱
        Page page = talkServer.getFamilyContribution(map);
//        familyPersonResumeServer.getFamilyContribution(user.getPersonId(), pageNum, pageSize);
  		result.setData(page);
  		return result;
    } 
	
	
	@ResponseBody
    @RequestMapping(value = "/sendTalkForApp", method = RequestMethod.POST)
    public Result sendTalkForApp(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		Talk talk = new Talk();
		int talkType = StringUtil.strToInteger(request.getParameter("talkType"));
		talk.setTalkContent(request.getParameter("talkContent"));
		talk.setUserId(user.getUserId());
		talk.setTalkId(UUID.randomUUID().toString()); 
		talk.setTalkType(talkType);
		talk.setTalkFocus(StringUtil.strToInteger(request.getParameter("talkFocus")));
		talk.setCreateTime(new Date());  
		talk.setNick(user.getNick());
		talk.setHeadUrl(user.getHeadUrl());
		if(talkType == 1){  
			talk.setRepliedTalkId(request.getParameter("repliedTalkId"));
		} 
		talkServer.saveTalk(talk); 
		result.setData(talk);
		return result;
    } 
	
	@ResponseBody
    @RequestMapping(value = "/addTalkAgreeCount", method = RequestMethod.POST)
    public Result addTalkAgreeCount(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		FamilyTalkAgree agree = new FamilyTalkAgree();
		agree.setAgreeId(UUID.randomUUID().toString());
		agree.setAgreeUserId(user.getUserId());
		agree.setTalkId(request.getParameter("talkId"));
		 
		talkServer.addAgree(agree);
		agree.setAgreeUserNick(user.getNick());
		result.setData(agree);
		return result;
    } 
    
	@ResponseBody
	@RequestMapping(value = "/sendTalk", method = RequestMethod.POST)
	public Result regUser(@RequestBody Map<String,Object> reqMap) { 
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		Talk talk = new Talk();
		int talkType = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("talkType")));
		talk.setTalkContent(StringUtil.objToStr(reqMap.get("talkContent")));
		talk.setUserId(user.getUserId());
		talk.setTalkId(UUID.randomUUID().toString()); 
		talk.setTalkType(talkType);
		talk.setCreateTime(new Date());   
		if(talkType == 1){  
			talk.setRepliedTalkId(StringUtil.objToStr(reqMap.get("repliedTalkId")));
		} 
		talkServer.saveTalk(talk); 
		return new Result("0", "");
	} 

}
