package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.Apply;
import org.swz.com.family.entity.FamilyIncludApply;
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
	
	public List<Apply> getApplyByUserId(Map<String, Object> params){
		return applyDao.getApplyByParams(params);
	};
	
	public List<Apply> getApplyForFamilyAdmin(String familyId){
		return applyDao.getApplyForFamilyAdmin(familyId);
	};
	
	public void updateApplyStatus(Apply apply){
		
		if(apply.getApplyResult() == 1){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", apply.getApplyUserId());
			map.put("personId", apply.getApplyPersonId()); 
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			user.setPersonId(apply.getApplyPersonId());//设置当前的用户任务ID
			userDao.setPersonIdForUser(map); 
		}
		applyDao.updateApplyStatus(apply);
	};
	
	public void save(Apply apply){
		applyDao.save(apply);
	}

	public void deleteApples(String objToStr) {
		applyDao.deleteApples(objToStr);
		
	}
 

}
