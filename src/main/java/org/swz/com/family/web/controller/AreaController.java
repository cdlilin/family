package org.swz.com.family.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/area")
public class AreaController {

    private final static Log logger = LogFactory.getLog(AreaController.class);

    @Autowired
    private CommonService commonService;   
	
    @ResponseBody
	@RequestMapping(value = "{areaId}/getAreaByParentId", method = RequestMethod.GET)
	public Result getAreaByParentId(@PathVariable String areaId) {
		Result result = new Result();
		List<Area> areas = null;
		try{
			areas = commonService.getAreaByParentId(areaId);
			result.setData(areas);
		}catch(Exception e){
			 e.printStackTrace();
		}
		
		return result;
	}
}
