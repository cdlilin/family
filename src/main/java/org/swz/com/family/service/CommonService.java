package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.Area;
import org.swz.com.family.repository.mybatis.AreaDao;
 

/**
 * 基本的service业务功能
 * @author join
 *
 */
@Component
@Transactional
@Monitored
public class CommonService {
	private static final Logger logger = Logger.getLogger(CommonService.class);
	private Map<Integer, List<Area>> areaMap = new HashMap<Integer, List<Area>>();
	
	@Autowired
	private AreaDao areaDao;

	public List<Area> getAreaByParentId(Object parentId) {
		int pid = parentId == null ? 0 : Integer.parseInt(String.valueOf(parentId)) < 0 ? 0 : Integer.parseInt(String.valueOf(parentId));
		if(areaMap.get(pid) == null){
			List<Area> areas = areaDao.getAreaByParentId(pid);
			areaMap.put(pid, areas);
		}
		
		return areaMap.get(pid);  
	}
	
	public List<Area> searchAll() { 
		List<Area> areas = areaDao.searchAll();  
		return areas;  
	}
	
	
	public List<Area> searchAreaByName(String areaName) { 
		List<Area> areas = areaDao.searchAreaByName(areaName);
		return areas;  
	}
	
	
}
