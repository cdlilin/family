package org.swz.com.family.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.swz.com.family.common.util.ImgCutUtil;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.PersonDao;
import org.swz.com.family.service.FamilyServer;
import org.swz.com.family.service.PersonServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;

/**
 * Created by star on 5/15/14.
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    private final static Log logger = LogFactory.getLog(PersonController.class);
 
    
    @Autowired
    private PersonServer personServer; 
    
    @Autowired
    private FamilyServer familyServer; 
    
	@ResponseBody
	@RequestMapping(value = "/modifyPerson", method = RequestMethod.POST)
	public Result modifyPerson(@RequestBody Map<String,Object> reqMap) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			Person person = new Person();   
			person.setPersonId(MapUtils.getString(reqMap, "personId"));
			person.setFirstName(MapUtils.getString(reqMap, "firstName"));
			person.setBirthday(MapUtils.getString(reqMap, "birthDay"));
			person.setLastName(MapUtils.getString(reqMap, "lastName"));
			person.setFullName(MapUtils.getString(reqMap, "fullName"));
			person.setNick(MapUtils.getString(reqMap, "nick"));
			person.setSex(StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("sex"))));
			person.setAddressId(MapUtils.getInteger(reqMap, "areaId")); 
			person.setPhone(MapUtils.getString(reqMap, "phone"));
			person.setEmail(MapUtils.getString(reqMap, "email"));
			person.setCid(MapUtils.getString(reqMap, "cid"));
			person.setHeadUrl(MapUtils.getString(reqMap, "headUrl"));
			person.setProfile(StringUtil.objToStr(reqMap.get("profile")));
			person.setModifyUserId(user.getUserId());
			personServer.modifyPerson(person); 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改人物出错", e);
			result.setCode("-1");
			result.setMessage("修改人物信息失败");
		}  
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public Result addPerson(@RequestBody Map<String,Object> reqMap) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			Person person = new Person();   
			person.setPersonId(UUID.randomUUID().toString());
			person.setFirstName(MapUtils.getString(reqMap, "firstName"));
			person.setBirthday(MapUtils.getString(reqMap, "birthDay"));
			person.setLastName(MapUtils.getString(reqMap, "lastName"));
			person.setFullName(MapUtils.getString(reqMap, "fullName"));
			person.setNick(MapUtils.getString(reqMap, "nick"));
			person.setSex(MapUtils.getInteger(reqMap, "sex"));
			person.setAddressId(MapUtils.getInteger(reqMap, "areaId")); 
			person.setPhone(MapUtils.getString(reqMap, "phone"));
			person.setEmail(MapUtils.getString(reqMap, "email"));
			person.setCid(MapUtils.getString(reqMap, "cid"));
			person.setHeadUrl(MapUtils.getString(reqMap, "headUrl"));
			person.setParentId("0");
			person.setCreateUserId(user.getUserId());
		
			personServer.addPerson(person); 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增任务出错", e);
			result.setCode("-1");
			result.setMessage("新增人物信息失败");
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/searchHitTerminalByFamilyId", method = RequestMethod.POST)
	public Result searchHitTerminalByFamilyId(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		List<Person> persons = personServer.searchHitTerminalByFamilyId(request.getParameter("familyId"));
		result.setData(persons);
		
		return	result;
	} 
	@ResponseBody
	@RequestMapping(value = "/checkFocus", method = RequestMethod.POST)
	public Result checkFocus(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			Map<String, Object> map = new HashMap<String, Object>(0);
			map.put("userId", user.getUserId());
			map.put("personId", request.getParameter("personId"));
			int count = personServer.checkFocus(map);
			result.setData(count); 
		 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增任务出错", e);
			result.setCode("-1");
			result.setMessage("新增人物信息失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/cancelFocus", method = RequestMethod.POST)
	public Result cancelFocus(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			Map<String, Object> map = new HashMap<String, Object>(0);
			map.put("userId", user.getUserId());
			map.put("personId", request.getParameter("personId"));
			personServer.cancelFocus(map);
		 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增任务出错", e);
			result.setCode("-1");
			result.setMessage("新增人物信息失败");
		}
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/modifyPersonForApp", method = RequestMethod.POST)
	public Result modifyPersonForApp(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			Person person = new Person();   
			person.setPersonId(request.getParameter("personId"));
			person.setFirstName(request.getParameter("firstName"));
//			person.setBirthDay(DateUtils.parse(MapUtils.getString(reqMap, "birthDay"), DateUtils.DATE_SMALL_STR));
			person.setLastName(request.getParameter("lastName"));
			person.setFullName(request.getParameter("fullName"));
			person.setBirthday(request.getParameter("birthday"));
			person.setNick(request.getParameter("nick"));
			person.setSex(Integer.parseInt(request.getParameter("sex") == null ? "1" : request.getParameter("sex")));
			person.setAddressId(Integer.parseInt(request.getParameter("addressId"))); 
//			person.setPhone(MapUtils.getString(reqMap, "phone"));
//			person.setEmail(MapUtils.getString(reqMap, "email"));
//			person.setCid(MapUtils.getString(reqMap, "cid"));  
			person.setProfile(request.getParameter("profile"));
			person.setHeadUrl(request.getParameter("headUrl"));
			person.setModifyUserId(user.getUserId()); 
			personServer.modifyPerson(person); 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改人物出错", e);
			result.setCode("-1");
			result.setMessage("修改人物信息失败");
		}  
		return result;
	} 
	 
	
	
	@ResponseBody
	@RequestMapping(value = "/addPersonForApp", method = RequestMethod.POST)
	public Result addPerson(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		try{
			
			Person person = new Person();   
			person.setPersonId(UUID.randomUUID().toString());
			person.setFirstName(request.getParameter("firstName"));
//			person.setBirthDay(DateUtils.parse(MapUtils.getString(reqMap, "birthDay"), DateUtils.DATE_SMALL_STR));
			person.setLastName(request.getParameter("lastName"));
			person.setFullName(request.getParameter("fullName"));
			person.setNick(request.getParameter("nick"));
			person.setSex(Integer.parseInt(request.getParameter("sex") == null ? "1" : request.getParameter("sex")));
			person.setAddressId(Integer.parseInt(request.getParameter("addressId"))); 
			person.setBirthday(request.getParameter("birthday"));
//			person.setPhone(MapUtils.getString(reqMap, "phone"));
//			person.setEmail(MapUtils.getString(reqMap, "email"));
//			person.setCid(MapUtils.getString(reqMap, "cid"));  
			person.setProfile(request.getParameter("profile"));
			person.setHeadUrl(request.getParameter("headUrl"));
			person.setParentId("0");
			int relationShipType = Integer.parseInt(request.getParameter("relationShipType")); 
			
			person.setRelationShipType(relationShipType); 
			ShiroRealm.ShiroUser user = personServer.addPerson(person); 
			
			result.setData(user);
			
		 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增任务出错", e);
			result.setCode("-1");
			result.setMessage("新增人物信息失败");
		}
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/addPersonNodeForApp", method = RequestMethod.POST)
	public Result addPersonNodeForApp(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		Person person = new Person();   
		
		person.setPersonId(UUID.randomUUID().toString());
		person.setFirstName(request.getParameter("firstName"));
//		person.setBirthDay(DateUtils.parse(MapUtils.getString(reqMap, "birthDay"), DateUtils.DATE_SMALL_STR));
		person.setLastName(request.getParameter("lastName"));
		person.setFullName(request.getParameter("fullName"));
//		person.setNick(MapUtils.getString(reqMap, "nick"));
		person.setSex(Integer.parseInt(request.getParameter("sex") == null ? "1" : request.getParameter("sex")));
		person.setAddressId(Integer.parseInt(request.getParameter("addressId"))); 
//		person.setPhone(MapUtils.getString(reqMap, "phone"));
//		person.setEmail(MapUtils.getString(reqMap, "email"));
//		person.setCid(MapUtils.getString(reqMap, "cid"));  
		person.setHeadUrl(request.getParameter("headUrl"));
		person.setProfile(request.getParameter("profile"));
		int relationShipType = Integer.parseInt(request.getParameter("relationShipType")); 
		
		RelationShip rs = new RelationShip();
		rs.setFamilyId(request.getParameter("familyId"));
		rs.setPersonId(person.getPersonId());
//		rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
		rs.setIsFamilyAdmin(0);
		rs.setParentId(request.getParameter("parentId"));
		rs.setRelationShipType(relationShipType);
		rs.setFamilyType(1);
		
		person.setRelationShipType(relationShipType);
		
		if(relationShipType == 1){//新增父节点
			rs.setRelationShipType(2);//默认为父节点的子女节点
			rs.setParentId("0");
			//更新当前节点的映射表父ID
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personId",request.getParameter("personId"));
			map.put("familyId", rs.getFamilyId());
			map.put("parentId", person.getPersonId());
			
			personServer.addParentNode(person, rs, map); 
		}else{ //新增子节点
			person.setParentId(request.getParameter("parentId"));
			personServer.addChildNode(person, rs);  
		}
		
//		//为用户查找可能的家谱
//		List<Family> families = familyServer.searchFamilyForRegUser(person);
//		result.setData(families);
		result.setData(person);
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/addPersonNode", method = RequestMethod.POST)
	public Result addPersonNode(@RequestBody Map<String,Object> reqMap) { 
		Result result = new Result("0", "");
		Person person = new Person();  
		person.setPersonId(UUID.randomUUID().toString());
		person.setFirstName(MapUtils.getString(reqMap, "firstName"));
//		person.setBirthDay(DateUtils.parse(MapUtils.getString(reqMap, "birthDay"), DateUtils.DATE_SMALL_STR));
		person.setLastName(MapUtils.getString(reqMap, "lastName"));
		person.setFullName(MapUtils.getString(reqMap, "fullName"));
//		person.setNick(MapUtils.getString(reqMap, "nick"));
		person.setSex(MapUtils.getInteger(reqMap, "sex"));
//		person.setAddressId(MapUtils.getInteger(reqMap, "areaId")); 
//		person.setPhone(MapUtils.getString(reqMap, "phone"));
//		person.setEmail(MapUtils.getString(reqMap, "email"));
//		person.setCid(MapUtils.getString(reqMap, "cid"));  
		person.setHeadUrl(StringUtil.objToStr(reqMap.get("headUrl")));
		person.setProfile(StringUtil.objToStr(reqMap.get("profile")));
		int relationShipType = MapUtils.getInteger(reqMap, "relationShipType");
		
		RelationShip rs = new RelationShip();
		rs.setFamilyId(MapUtils.getString(reqMap, "familyId"));
		rs.setPersonId(person.getPersonId());
//		rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
		rs.setIsFamilyAdmin(0);
		rs.setParentId(MapUtils.getString(reqMap, "parentId"));
		rs.setRelationShipType(MapUtils.getInteger(reqMap, "relationShipType"));
		person.setRelationShipType(MapUtils.getInteger(reqMap, "relationShipType"));
		if(relationShipType == 1){//新增父节点
			rs.setRelationShipType(2);//默认为父节点的子女节点
			rs.setParentId("0");
			//更新当前节点的映射表父ID
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personId", MapUtils.getString(reqMap, "personId"));
			map.put("familyId", rs.getFamilyId());
			map.put("parentId", person.getPersonId());
			
			personServer.addParentNode(person, rs, map); 
		}else{ //新增子节点
			person.setParentId(MapUtils.getString(reqMap, "parentId"));
			personServer.addChildNode(person, rs); 
		}
		
//		//为用户查找可能的家谱
//		List<Family> families = familyServer.searchFamilyForRegUser(person);
//		result.setData(families);
		result.setData(person);
		return result;
	} 
	
	@ResponseBody
	@RequestMapping(value = "/getUserPersonInfo", method = RequestMethod.GET)
	public Result getUserPersonInfo() {
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		Person person = personServer.getUserPersonInfoByUserId(user.getUserId());
		
		if(person == null){
			person = new Person();
		}
		person.setUserName(user.getUsername());
		
		result.setData(person);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delSpouse", method = RequestMethod.POST)
	public Result delSpouse(@RequestBody Map<String,Object> reqMap) {
		Result result = new Result("0", "");
//		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
//		Person person = personServer.getUserPersonInfoByUserId(user.getUserId());
//		result.setData(person);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", MapUtils.getString(reqMap, "personId"));
		map.put("familyId", MapUtils.getString(reqMap, "familyId")); 
		
		personServer.delSpouse(map);
		
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public String createPerson() { 
		
		return "user/personInfo";
	} 
	
	@ResponseBody
	@RequestMapping(value = "/uploadUserHead", method = RequestMethod.POST)
	public Map<String, Object> uploadUserHead(HttpServletRequest request, HttpServletResponse response){
       //		Result result = new Result(); 
		Map<String, Object> map = new HashMap<String, Object>();
		  try {  
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
			    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();   
			    String webPath = this.getClass().getResource("/").getPath();
			    File file = new File(webPath.substring(0, webPath.indexOf("WEB-INF")) + "custom/user/userHead");
		    	
		    	if(!file.exists()){
		    		file.mkdirs();
		    	} 
		    	
			    for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {   
			    	// 上传文件 
			    	MultipartFile mf = entity.getValue();  
			    	String name = mf.getName(); 
			    	if (name.indexOf("fileName") > -1)
					{ 
			    		String fileEnd = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
			    		String fileName = UUID.randomUUID().toString() + fileEnd.replace("'", ""); 
						InputStream inputStream = new BufferedInputStream(mf.getInputStream());
						BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getPath() + "/" + fileName)));
						Streams.copy(inputStream, outputStream, true); 
						
						map.put("status", true);
						map.put("sourceUrl", "custom/user/userHead/" + fileName); 
						
						System.out.println("=====================================================");
						if(request.getParameter("personId") != null){
							map.put("personId", request.getParameter("personId")); 
							personServer.updatePersonHeadUrl(map);
							map.remove("personId");
						}
						
						inputStream.close();
			            outputStream.flush(); 
			            outputStream.close();
					} 
			  	}  
 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
//				result.setCode("401");
//				result.setMessage("上传失败");
			}  
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/cutHeadForUser", method = RequestMethod.POST)
	public Result cutHeadForUser(@RequestBody Map<String,Object> reqMap){
		Result result = new Result(); 
		String webPath = this.getClass().getResource("/").getPath();
		String userHeadPath = webPath.substring(0, webPath.indexOf("WEB-INF")) + "custom/user/userHead";
	    File file = new File(userHeadPath);
	    if(!file.exists()){
    		file.mkdirs();
    	}
	    String uploadFileName = StringUtil.objToStr(reqMap.get("uploadFileName"));
	    uploadFileName = userHeadPath + uploadFileName.substring(uploadFileName.lastIndexOf("/"));
	    
	    String fileEnd = uploadFileName.substring(uploadFileName.lastIndexOf("."));
	    String newFileName = UUID.randomUUID().toString() + fileEnd;
    	
    	//切图的宽度
    	int w = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("w")));
    	//切图高度
    	int h = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("h")));
    	//切图的宽度
    	int x1 = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("x1")));
    	//切图的宽度
    	int y1 = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("y1")));
    	//切图的宽度
    	int sw = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("sw")));
    	//切图的宽度
    	int sh = StringUtil.strToInteger(StringUtil.objToStr(reqMap.get("sh")));
    	
    	
    	ImgCutUtil.cut(uploadFileName, userHeadPath + "/" + newFileName, w, h, x1, y1, sw, sh);
		
    	result.setCode("0");
    	result.setMessage("custom/user/userHead/" + newFileName);
		return result;
	} 
	
	
	// focus

	@ResponseBody
	@RequestMapping(value = "/getMyFocus", method = RequestMethod.GET)
	public Result getMyFocus(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal(); 
		
		List<Person> persons = personServer.getMyFocus(user.getUserId());
		result.setData(persons);
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/searchFocusPersons", method = RequestMethod.POST)
	public Result searchFocusPersons(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal(); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchValue", request.getParameter("searchValue"));
		map.put("userId", user.getUserId());
		
		List<Person> persons = personServer.searchFocusPersons(map);
		result.setData(persons);
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/addFocus", method = RequestMethod.POST)
	public Result addFocus(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal(); 
		personServer.addFocus(request.getParameter("personIds"), user.getUserId());
		
		return result;
	}
	
	
	
	
	 
 
	
}
