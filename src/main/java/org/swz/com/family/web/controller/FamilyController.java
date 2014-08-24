package org.swz.com.family.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.PathParam;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.DateUtils;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.FamilyDao;
import org.swz.com.family.service.FamilyServer;
import org.swz.com.family.service.PersonServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;

/**
 * Created by star on 5/15/14.
 */
@Controller 
public class FamilyController {

    private final static Log logger = LogFactory.getLog(FamilyController.class);

    @Autowired
    private FamilyServer familyServer; 
    
    @Autowired
    private PersonServer personServer; 

    @RequestMapping(value = "/familytree", method = RequestMethod.GET)
	public String familyTree() {
		return "family/familytree";
	} 
    
    @RequestMapping(value = "/familyPerson", method = RequestMethod.GET)
	public String familyPerson() {
		return "family/familyPerson";
	} 
    
    @RequestMapping(value = "/familyCommunity", method = RequestMethod.GET)
	public String familyCommunity() {
		return "family/familyCommunity";
	} 
    
    @RequestMapping(value = "/familyImg", method = RequestMethod.GET)
	public String familyImg() {
		return "family/familyImg";
	}  
	
	@RequestMapping(value = "/familyManage", method = RequestMethod.GET)
	public String familyManage() {
		return "family/familyMain";
	} 
	
	
    
    @ResponseBody
    @RequestMapping(value = "/getFamilyForCurrentUser", method = RequestMethod.GET)
   	public Result getFamilyForCurrentUser() {
    	Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Family> families = familyServer.searchFamilyForCurrentUser(user.getPersonId());
		result.setData(families);
		return result;
   	} 
    
    
    @ResponseBody
    @RequestMapping(value = "{familyId}/getFamilyTree", method = RequestMethod.GET)
   	public Result getFamilyTree(@PathVariable String familyId) {
    	Result result = new Result("0", ""); 
		Person person = personServer.searchPersonForTree(familyId);
		result.setData(person);
		return result;
   	} 
    
    @ResponseBody
    @RequestMapping(value = "{familyId}/getFamilyTreeForGrid", method = RequestMethod.GET)
   	public List<Person> getFamilyTreeForGrid(@PathVariable String familyId) {
		List<Person> persons = personServer.searchPersonForGridTree(familyId);
		return persons;
   	} 
     
    
    @ResponseBody
    @RequestMapping(value = "/saveFamily", method = RequestMethod.POST)
   	public Result saveFamily(@RequestBody Map<String,Object> reqMap) {
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Result result = new Result("0", ""); 
    	Family family = new Family();
    	family.setFamilyId(UUID.randomUUID().toString());
    	family.setCreateUserId(user.getUserId());
    	family.setFamilyName(StringUtil.objToStr(reqMap.get("familyName")));
    	family.setUserType(1);
    	
    	
    	RelationShip rs = new RelationShip();
		rs.setFamilyId(family.getFamilyId());
		rs.setPersonId(user.getPersonId());
//		rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
		rs.setIsFamilyAdmin(1);
		rs.setParentId("0");
		rs.setRelationShipType(2);
		
    	familyServer.saveFamily(family, rs);
    	
		result.setData(family);
		return result;
   	}  
    
    
    @ResponseBody
	@RequestMapping(value = "/getFamilyByPersonInfo", method = RequestMethod.POST)
	public Result getFamilyByPersonInfo(@RequestBody Map<String,Object> reqMap) { 
		Result result = new Result("0", "");
		Person person = new Person();   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName", StringUtil.objToStr(reqMap.get("fullName")));
		map.put("sex", StringUtil.objToStr(reqMap.get("sex")));
		map.put("areaId", StringUtil.objToStr(reqMap.get("areaId")));
		map.put("phone", StringUtil.objToStr(reqMap.get("phone")));
		map.put("email", StringUtil.objToStr(reqMap.get("email")));
		map.put("cid", StringUtil.objToStr(reqMap.get("cid")));
		map.put("birthDay", StringUtil.objToStr(reqMap.get("birthDay"))); 
		//为用户查找可能的家谱
		List<Family> families = familyServer.searchFamilyForRegUser(map);
		result.setData(families);
		return result;
	}
 
 
}
