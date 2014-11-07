package org.swz.com.family.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.FamilyCulture;
import org.swz.com.family.entity.FamilyForest;
import org.swz.com.family.entity.FamilyRule;
import org.swz.com.family.entity.JitTreeNode;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
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
		return "family/familyMap";
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
		return "family/familyManage";
	} 
	
	@RequestMapping(value = "/familyApply", method = RequestMethod.GET)
	public String familyApply() {
		return "family/familyApply";
	} 
	
	@RequestMapping(value = "/familyCheck", method = RequestMethod.GET)
	public String familyCheck() {
		return "family/familyCheck";
	}   
	
	@ResponseBody
    @RequestMapping(value = "/getFamilyRealtionShipForCurrentUser", method = RequestMethod.GET)
   	public Result getFamilyRealtionShipForCurrentUser() {
    	Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		JitTreeNode person = personServer.searchForJitTree(user.getPersonId());
		result.setData(person);
		return result;
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
   	@RequestMapping(value = "/hitFamilyByRelationShip", method = RequestMethod.POST)
   	public List<Family> hitFamilyByRelationShip(HttpServletRequest request, HttpServletResponse response) { 
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("fullName", StringUtil.objToStr(request.getParameter("fullName")));
   		map.put("fatherName", StringUtil.objToStr(request.getParameter("fatherName")));
   		map.put("matherName", StringUtil.objToStr(request.getParameter("matherName"))); 
   		//为用户查找可能的家谱
   		List<Family> families = familyServer.hitFamilyByRelationShip(map);
   		return families;
   	}
    
    @ResponseBody
   	@RequestMapping(value = "/searchFamilyCountForRegUser", method = RequestMethod.POST)
   	public Result searchFamilyCountForRegUser(HttpServletRequest request, HttpServletResponse response) { 
    	Result result = new Result("0", "");
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("fullName", StringUtil.objToStr(request.getParameter("fullName")));

   		//为用户查找可能的家谱
   		int count = familyServer.searchFamilyCountForRegUser(map);
   		result.setData(count);
   		return result;
   	}  
    
    
    
    
    @ResponseBody
    @RequestMapping(value = "{familyId}/getFamilyTreeForGrid", method = RequestMethod.GET)
   	public List<Person> getFamilyTreeForGrid(@PathVariable String familyId) {
		List<Person> persons = personServer.searchPersonForGridTree(familyId);
		return persons;
   	}
    
    @ResponseBody
    @RequestMapping(value = "/getFamiliesForApp", method = RequestMethod.GET)
   	public List<Person> getFamiliesForApp() {
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Person> persons = personServer.searchFamiliesByCurrentUser(user.getPersonId());
		return persons;
   	}
    
    @ResponseBody
    @RequestMapping(value = "/searchFamilyForManage", method = RequestMethod.GET)
   	public Result searchFamilyForManage() {
    	Result result = new Result("0", ""); 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Family> families = familyServer.searchFamilyForManage(user.getAreaIds(), null);
		result.setData(families);
		return result;
   	}
    
    @ResponseBody
    @RequestMapping(value = "/searchFamilyForMerge", method = RequestMethod.GET)
   	public Result searchFamilyForMerge() {
    	Result result = new Result("0", ""); 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Family> families = familyServer.searchFamilyForMerge(user.getAreaIds(), null);
		result.setData(families);
		return result;
   	} 
    
    
    @ResponseBody
    @RequestMapping(value = "/searchCurrentAreaFamily", method = RequestMethod.GET)
   	public Result searchCurrentAreaFamily() {
    	Result result = new Result("0", ""); 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	List<String> areaIds = new ArrayList<String>();
    	areaIds.add(String.valueOf(user.getAddressId()));
		List<Family> families = familyServer.searchFamilyForManage(areaIds, user.getFirstName());
		result.setData(families);
		return result;
   	} 
    
    
    @ResponseBody
    @RequestMapping(value = "/getHistoricalFiguresForApp", method = RequestMethod.POST)
   	public List<Person> getPersonForApp(HttpServletRequest request) {
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Person> persons = personServer.getHistoricalFiguresByCurrentUser(user.getPersonId(),Integer.parseInt(request.getParameter("startIndex")), Integer.parseInt(request.getParameter("endIndex")));
		return persons;
   	}
    
    @ResponseBody
    @RequestMapping(value = "/getFamilyRealtionShipByFamilyId", method = RequestMethod.GET)
   	public Result getFamilyRealtionShipByFamilyId(HttpServletRequest request, HttpServletResponse response) {
    	Result result = new Result("0", "");
		JitTreeNode person = personServer.getFamilyRealtionShipByFamilyId(request.getParameter("familyId"));
		result.setData(person);
		return result;
   	} 
    @ResponseBody
    @RequestMapping(value = "/getFamilyTreeForCurrentUser", method = RequestMethod.GET)
   	public List<Person> getFamilyTreeForCurrentUser() {
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		List<Person> persons = personServer.searchPersonForCurentUser(user.getPersonId());
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
		
		FamilyForest familyForest = new FamilyForest();
		familyForest.setFamilyId(family.getFamilyId());
		familyForest.setContactPersonId("");
		familyForest.setParentId(family.getFamilyId());
		familyForest.setIsChecked(1);
		familyForest.setRootId(family.getFamilyId());
		 
    	familyServer.saveFamily(family, rs, familyForest);
    	
		result.setData(family);
		return result;
   	} 
    
    
    @ResponseBody
    @RequestMapping(value = "/saveFamilyForApp", method = RequestMethod.POST)
   	public Result saveFamilyForApp(HttpServletRequest request, HttpServletResponse response) {
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Result result = new Result("0", ""); 
    	Family family = new Family();
    	family.setFamilyId(UUID.randomUUID().toString());
    	family.setCreateUserId(user.getUserId());
    	family.setFamilyName(StringUtil.objToStr(request.getParameter("familyName")));
    	family.setUserType(2);
    	family.setSurname(request.getParameter("firstName"));
    	family.setAreaId(user.getAddressId());
    	family.setIncludeFlag(1); 
    	
    	Person person = new Person();   
		person.setPersonId(UUID.randomUUID().toString());
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		person.setFullName(request.getParameter("fullName"));
		person.setSex(Integer.parseInt(request.getParameter("sex") == null ? "1" : request.getParameter("sex")));
		person.setAddressId(family.getAreaId()); 
		
    	RelationShip rs = new RelationShip();
		rs.setFamilyId(family.getFamilyId());
		rs.setPersonId(person.getPersonId());
//		rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
		rs.setIsFamilyAdmin(1);
		rs.setParentId("0");
		rs.setRelationShipType(2); 
		
		FamilyForest familyForest = new FamilyForest();
		familyForest.setFamilyId(family.getFamilyId());
		familyForest.setContactPersonId("");
		familyForest.setParentId(family.getFamilyId());
		familyForest.setIsChecked(1);
		familyForest.setRootId(family.getFamilyId());
		 
    	familyServer.saveFamily(family, rs, person);
    	
		result.setData(family);
		return result;
   	} 
    
    
    @ResponseBody
	@RequestMapping(value = "/getFamilyByPersonInfo", method = RequestMethod.POST)
	public List<Family> getFamilyByPersonInfo(HttpServletRequest request, HttpServletResponse response) { 
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("fullName", StringUtil.objToStr(request.getParameter("fullName")));
   		map.put("fatherName", StringUtil.objToStr(request.getParameter("fatherName")));
   		map.put("matherName", StringUtil.objToStr(request.getParameter("matherName"))); 
   		//为用户查找可能的家谱
   		List<Family> families = familyServer.hitFamilyByRelationShip(map);
   		return families;
   	}
    
    @ResponseBody
	@RequestMapping(value = "/searchHitPersonByPersonId", method = RequestMethod.POST)
	public List<Family> searchHitPersonByPersonId(HttpServletRequest request, HttpServletResponse response) { 
   		Map<String, Object> map = new HashMap<String, Object>();
   		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
   		map.put("contactFamilyId", StringUtil.objToStr(request.getParameter("familyId")));
   		map.put("currentFamilyId", user.getFamilyId());
   		//为用户查找可能的家谱
   		List<Family> families = familyServer.searchHitPersonByPersonId(map);
   		return families;
   	} 
    
    
    
    @ResponseBody
	@RequestMapping(value = "/getFamilyRule", method = RequestMethod.GET)
	public Result getFamilyRule(HttpServletRequest request, HttpServletResponse response) { 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Result result = new Result("0", "");
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("familyId", user.getFamilyId());
   		//为用户查找可能的家谱
   		List<FamilyRule> families = familyServer.getFamilyRule(map);
   		result.setData(families);
   		return result;
   	}
    
    @ResponseBody
   	@RequestMapping(value = "/getFamilyCulture", method = RequestMethod.GET)
   	public Result getFamilyCulture(HttpServletRequest request, HttpServletResponse response) { 
       	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
       	Result result = new Result("0", "");
      		Map<String, Object> map = new HashMap<String, Object>();
      		map.put("familyId", user.getFamilyId());
      		//为用户查找可能的家谱
      		List<FamilyCulture> families = familyServer.getFamilyCulture(map);
      		result.setData(families);
      		return result;
      	} 
    
    @ResponseBody
   	@RequestMapping(value = "/saveFamilyRule", method = RequestMethod.POST)
   	public Result saveFamilyRule(HttpServletRequest request, HttpServletResponse response) { 
   		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
   		Result result = new Result("0", "");
   		FamilyRule familyRule = new FamilyRule();
   		familyRule.setRuleId(UUID.randomUUID().toString());
   		familyRule.setRuleTitle(request.getParameter("ruleTitle"));
   		familyRule.setRuleContent(request.getParameter("ruleContent"));
   		familyRule.setCreateUserId(user.getUserId());
   		familyRule.setFamilyId(request.getParameter("familyId"));
   		familyRule.setCreateTime(new Date());
  		//为用户查找可能的家谱
  		familyServer.saveFamilyRule(familyRule);
  		result.setData(familyRule);
  		return result;
  	} 
    
    @ResponseBody
   	@RequestMapping(value = "/saveFamilyCulture", method = RequestMethod.POST)
   	public Result saveFamilyCulture(HttpServletRequest request, HttpServletResponse response) { 
   		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
   		Result result = new Result("0", "");
   		FamilyCulture familyCulture = new FamilyCulture();
   		familyCulture.setCultureId(UUID.randomUUID().toString());
   		familyCulture.setCultureTitle(request.getParameter("cultureTitle"));
   		familyCulture.setCultureContent(request.getParameter("cultureContent"));
   		familyCulture.setCreateUserId(user.getUserId());
   		familyCulture.setFamilyId(request.getParameter("familyId"));
   		familyCulture.setCreateTime(new Date());
  		//为用户查找可能的家谱
  		familyServer.saveFamilyCulture(familyCulture);
  		result.setData(familyCulture);
  		return result;
  	} 
    
    
    @ResponseBody
	@RequestMapping(value = "/applyFamilyMerge", method = RequestMethod.POST)
	public Result applyFamilyMerge(HttpServletRequest request, HttpServletResponse response) { 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Result result = new Result("0", "");
    	FamilyForest familyForest = familyServer.searchFamilyForest(user.getFamilyId());
    	FamilyForest rootFamilyForest = familyServer.searchFamilyForest(request.getParameter("contactFamilyId"));
    	if(familyForest == null){ 
    		familyForest = new FamilyForest();
    		familyForest.setFamilyId(user.getFamilyId());
    		familyForest.setContactPersonId(request.getParameter("contactPersonId"));
    		familyForest.setParentId(request.getParameter("contactFamilyId"));
    		familyForest.setIsChecked(0);
    		if(rootFamilyForest == null){
    			rootFamilyForest = new FamilyForest();
    			rootFamilyForest.setFamilyId(request.getParameter("contactFamilyId"));
    			rootFamilyForest.setContactPersonId("");
    			rootFamilyForest.setParentId(request.getParameter("contactFamilyId"));
        		rootFamilyForest.setIsChecked(1);
        		rootFamilyForest.setRootId(request.getParameter("contactFamilyId"));
    			familyServer.saveFamilyForest(rootFamilyForest);
    		}
    		familyForest.setRootId(rootFamilyForest.getRootId());
    		familyServer.saveFamilyForest(familyForest);
    		
    	}else if(familyForest.getParentId().equals(user.getFamilyId())){
    		familyForest.setParentId(request.getParameter("contactFamilyId"));
    		familyForest.setContactPersonId(request.getParameter("contactPersonId"));
    		familyServer.contactFamily(familyForest);
    	}else{ 
    		result.setCode("-1");
    		result.setMessage("已经挂载到其他家谱了，无法重新挂载，");
    	} 
 
   		return result;
   	} 
    
    
    @ResponseBody
	@RequestMapping(value = "/applyFamilyContact", method = RequestMethod.POST)
	public Result applyFamilyContact(HttpServletRequest request, HttpServletResponse response) { 
    	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Result result = new Result("0", "");
    	FamilyForest familyForest = familyServer.searchFamilyForest(user.getFamilyId());
    	FamilyForest rootFamilyForest = familyServer.searchFamilyForest(request.getParameter("contactFamilyId"));
    	if(familyForest == null){ 
    		familyForest = new FamilyForest();
    		familyForest.setFamilyId(user.getFamilyId());
    		familyForest.setContactPersonId(request.getParameter("contactPersonId"));
    		familyForest.setParentId(request.getParameter("contactFamilyId"));
    		familyForest.setIsChecked(0);
    		if(rootFamilyForest == null){
    			rootFamilyForest = new FamilyForest();
    			rootFamilyForest.setFamilyId(request.getParameter("contactFamilyId"));
    			rootFamilyForest.setContactPersonId("");
    			rootFamilyForest.setParentId(request.getParameter("contactFamilyId"));
        		rootFamilyForest.setIsChecked(1);
        		rootFamilyForest.setRootId(request.getParameter("contactFamilyId"));
    			familyServer.saveFamilyForest(rootFamilyForest);
    		}
    		familyForest.setRootId(rootFamilyForest.getRootId());
    		familyServer.saveFamilyForest(familyForest);
    		
    	}else if(familyForest.getParentId().equals(user.getFamilyId())){
    		familyForest.setParentId(request.getParameter("contactFamilyId"));
    		familyForest.setContactPersonId(request.getParameter("contactPersonId"));
    		familyServer.contactFamily(familyForest);
    	}else{ 
    		result.setCode("-1");
    		result.setMessage("已经挂载到其他家谱了，无法重新挂载，");
    	} 
 
   		return result;
   	} 
     
 
 
}
