package org.swz.com.family.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
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
import org.swz.com.family.common.util.DateUtils;
import org.swz.com.family.common.util.ImgCutUtil;
import org.swz.com.family.common.util.RequestUtil;
import org.swz.com.family.common.util.StringUtil;
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
@RequestMapping(value = "/person")
public class PersonController {

    private final static Log logger = LogFactory.getLog(PersonController.class);
 
    
    @Autowired
    private PersonServer personServer; 
    
    @Autowired
    private FamilyServer familyServer; 
    
	@ResponseBody
	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	public Result savePerson(@RequestBody Map<String,Object> reqMap) { 
		Result result = new Result("0", "");
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		Person person = new Person();  
		person.setPersonId(UUID.randomUUID().toString());
		person.setFirstName(MapUtils.getString(reqMap, "firstName"));
		person.setBirthDay(DateUtils.parse(MapUtils.getString(reqMap, "birthDay"), DateUtils.DATE_SMALL_STR));
		person.setLastName(MapUtils.getString(reqMap, "lastName"));
		person.setFullName(MapUtils.getString(reqMap, "fullName"));
		person.setNick(MapUtils.getString(reqMap, "nick"));
		person.setSex(MapUtils.getInteger(reqMap, "sex"));
		person.setAddressId(MapUtils.getInteger(reqMap, "areaId")); 
		person.setPhone(MapUtils.getString(reqMap, "phone"));
		person.setEmail(MapUtils.getString(reqMap, "email"));
		person.setCid(MapUtils.getString(reqMap, "cid"));
		person.setHeadUrl(MapUtils.getString(reqMap, "headUrl"));
		person.setCreateUserId(user.getUserId());
		personServer.savePerson(person);  
		user.setPersonId(person.getPersonId()); 
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
//		person.setSex(MapUtils.getInteger(reqMap, "sex"));
//		person.setAddressId(MapUtils.getInteger(reqMap, "areaId")); 
//		person.setPhone(MapUtils.getString(reqMap, "phone"));
//		person.setEmail(MapUtils.getString(reqMap, "email"));
//		person.setCid(MapUtils.getString(reqMap, "cid"));  
		int relationShipType = MapUtils.getInteger(reqMap, "relationShipType");
		
		RelationShip rs = new RelationShip();
		rs.setFamilyId(MapUtils.getString(reqMap, "familyId"));
		rs.setPersonId(person.getPersonId());
//		rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
		rs.setIsFamilyAdmin(0);
		rs.setParentId(MapUtils.getString(reqMap, "parentId"));
		rs.setRelationShipType(MapUtils.getInteger(reqMap, "relationShipType"));
		
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
	public Result getFullStationTrains(HttpServletRequest request, HttpServletResponse response){
		Result result = new Result(); 
		  try {  
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
			    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();   
			    String webPath = this.getClass().getResource("/").getPath();
			    File file = new File(webPath.substring(0, webPath.indexOf("WEB-INF")) + "custom/user/userHead");
		    	
		    	if(!file.exists()){
		    		file.mkdirs();
		    	}
		    	String uploadFileName = request.getParameter("uploadFileName");
		    	System.out.println(uploadFileName);
		    	String fileEnd = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		    	
			    for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {   
			    	// 上传文件 
			    	MultipartFile mf = entity.getValue();  
			    	System.out.println(mf.getName());
			    	 
			    	String fileName = file.getPath() + "/" + UUID.randomUUID().toString() + fileEnd;
			    	File outFile = new File(fileName);
			    	FileOutputStream fileOut = new FileOutputStream(outFile);
			    	byte[] buff = new byte[4000];
			    	InputStream is = mf.getInputStream();
			    	int len = is.read(buff);
			    	while(len > 0){
			    		fileOut.write(buff);
			    		len = is.read(buff);
			    	}
			    	fileOut.close(); 
			    	result.setCode("0");
			    	result.setMessage("custom/user/userHead/" + outFile.getName());
			    	System.out.println("--------------save end---------------");
//			    	crossService.actionExcel(mf.getInputStream(), chartId, startDay, chartName, addFlag);
			  	}  
 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setCode("401");
				result.setMessage("上传失败");
			}  
		return result;
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
	
}
