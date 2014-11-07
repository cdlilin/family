package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.FamilyCulture;
import org.swz.com.family.entity.FamilyForest;
import org.swz.com.family.entity.FamilyRule;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.FamilyDao;
import org.swz.com.family.repository.mybatis.PersonDao;

@Component
@Monitored
public class FamilyServer {
	
	@Autowired
	private FamilyDao familyDao;
	
	@Autowired
	private PersonDao personDao;
	
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
	
	public void saveFamily(Family family, RelationShip rs, FamilyForest familyForest) {
		familyDao.save(family); 
		familyDao.saveFamilyForest(familyForest);
		familyDao.saveRelationShip(rs);
		
	}
	
	public List<Family> hitFamilyByRelationShip(Map<String, Object> map) {
		
		List<Family> families = familyDao.searchFamilyForRegUser(map);
		
		return families;
	}  
	
	public List<Family> searchHitPersonByPersonId(Map<String, Object> map){
		List<Family> families = null;
		Person person = personDao.searchRootPerson(map);
		if(person != null){
			map.put("fullName", person.getFullName());
		    families = familyDao.searchHitPersonByPersonId(map);
		} 
		return families;
	}
	
	public int searchFamilyCountForRegUser(Map<String, Object> map) {
		
		int count = familyDao.searchFamilyCountForRegUser(map);
		
		return count;
	}

	public List<FamilyRule> getFamilyRule(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<FamilyRule> familys = familyDao.getFamilyRule(map);
		return familys;
	} 
	

	public List<FamilyCulture> getFamilyCulture(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<FamilyCulture> familys = familyDao.getFamilyCulture(map);
		return familys;
	} 
	
	public List<Family> searchFamilyForManage(List<String> areaIds, String firstName){
		String whereSql = "";
		for(String areaId : areaIds){
			whereSql += ("".equals(whereSql) ? "'" : ",'") + areaId + "'"; 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaIds", whereSql);
		if(firstName != null){
			map.put("surname", firstName);
		} 
			
		List<Family> familys = familyDao.searchFamilyForManage(map);
		return familys;
	}
	
	public FamilyForest searchFamilyForest(String familyId){
		FamilyForest familyForest = familyDao.searchFamilyForest(familyId);
		return familyForest;
	}

	public void contactFamily(FamilyForest familyForest) {
		// TODO Auto-generated method stub
		familyDao.updateFamilyForest(familyForest);
		
	}

	public void saveFamilyForest(FamilyForest familyForest) {
		// TODO Auto-generated method stub
		familyDao.saveFamilyForest(familyForest);
	}

	public void saveFamily(Family family, RelationShip rs, Person person) {
		familyDao.save(family); 
		personDao.save(person);
		familyDao.saveRelationShip(rs);
		
	}

	public void saveFamilyRule(FamilyRule familyRule) {
		// TODO Auto-generated method stub
		familyDao.saveFamilyRule(familyRule);
	}

	public void saveFamilyCulture(FamilyCulture familyCulture) {
		familyDao.saveFamilyCulture(familyCulture);
		
	}

	public List<Family> searchFamilyForMerge(List<String> areaIds,  String firstName) {
		String whereSql = "";
		for(String areaId : areaIds){
			whereSql += ("".equals(whereSql) ? "'" : ",'") + areaId + "'"; 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaIds", whereSql);
		if(firstName != null){
			map.put("surname", firstName);
		} 
			
		List<Family> familys = familyDao.searchFamilyForMerge(map);
		return familys;
	}

}
