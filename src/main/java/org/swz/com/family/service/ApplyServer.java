package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.Apply;
import org.swz.com.family.repository.mybatis.ApplyDao;
import org.swz.com.family.repository.mybatis.UserDao;

@Component
@Transactional
@Monitored
public class ApplyServer {
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Apply> getApplyByUserId(String userId){
		return applyDao.getApplyByUserId(userId);
	};
	
	public List<Apply> getApplyForFamilyAdmin(String familyId){
		return applyDao.getApplyForFamilyAdmin(familyId);
	};
	
	public void updateApplyStatus(Apply apply){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", apply.getApplyUserId());
		map.put("personId", apply.getApplyPersonId()); 
		userDao.setPersonIdForUser(map); 
		applyDao.updateApplyStatus(apply);
	};
	
	public void save(Apply apply){
		applyDao.save(apply);
	}; 

}
