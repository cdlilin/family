package org.swz.com.family.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.entity.Area;
import org.swz.com.family.service.CommonService;
import org.swz.com.family.web.dto.Result;


/**
 * Created by star on 5/15/14.
 */
@Controller 
@RequestMapping(value = "/common")
public class CommonController {

    private final static Log logger = LogFactory.getLog(CommonController.class);

    @Autowired
    private CommonService commonService; 
     

	@ResponseBody
    @RequestMapping(value = "/searchAreaByName", method = RequestMethod.POST)
   	public Result getFamilyRealtionShipForCurrentUser(HttpServletRequest request, HttpServletResponse response) {
    	Result result = new Result("0", "");
		List<Area> areas = commonService.searchAreaByName(request.getParameter("areaName"));
		result.setData(areas);
		return result;
   	} 
    

}
