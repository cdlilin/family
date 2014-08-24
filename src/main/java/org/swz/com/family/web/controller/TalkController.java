package org.swz.com.family.web.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.swz.com.family.entity.Talk;
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
		int startIndex = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("startIndex")));
        int endIndex = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("endIndex")));
		return talkServer.getTalks(startIndex, endIndex);
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
