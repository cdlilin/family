package org.swz.com.family.service;

import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.FamilyDao;

@Component
@Monitored
public class FamilyServer {
	
	@Autowired
	private FamilyDao familyDao;
	
	public List<Family> searchFamilyForRegUser(Map<String, Object> map){

		List<Family> families = familyDao.searchFamilyForRegUser(map);
		
		return families;
		
		
	} 
	public List<Family> searchFamilyForCurrentUser(String personId) {
		 
		List<Family> families = familyDao.searchFamilyForPerson(personId);
		
		return families;
	} 
	public void saveFamily(Family family) {
		familyDao.save(family); 
	}
	public void saveFamily(Family family, RelationShip rs) {
		familyDao.save(family); 
		familyDao.saveRelationShip(rs);
		
	}  

}
