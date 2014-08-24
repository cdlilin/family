package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.FamilyDao;
import org.swz.com.family.repository.mybatis.PersonDao;
import org.swz.com.family.repository.mybatis.UserDao;

@Component
@Monitored
public class PersonServer {
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private FamilyDao familyDao;
	
	@Autowired
	private UserDao userDao;
	
	public void savePerson(Person person){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", person.getCreateUserId());
		map.put("personId", person.getPersonId()); 
		personDao.save(person);
		userDao.setPersonIdForUser(map);
	}
	
	public Person searchPersonForTree( String familyId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId" , familyId);
		Person personRoot = new Person();
		personRoot.setPersonId("-1");
		personRoot.setFullName("家谱"); 
		List<Person> persons = personDao.searchForTree(map); 
		
		personRoot.setChildren(persons);
 
		return personRoot;
	} 
	
	public List<Person>  searchPersonForGridTree( String familyId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId" , familyId);
	 
		List<Person> persons = personDao.searchForTree(map); 
	 
		return persons;
	}

	public Person getUserPersonInfoByUserId(String userId) {
		Person person = personDao.getUserPersonInfoByUserId(userId); 
		return person;
	} 

	public void addChildNode(Person person, RelationShip rs) {
		personDao.save(person);		
		familyDao.saveRelationShip(rs);
	}

	public void addParentNode(Person person, RelationShip rs, Map<String, Object> reqMap) {
		personDao.save(person);		
		familyDao.saveRelationShip(rs);
		familyDao.updateRelationShip(reqMap);
	}

	public void delSpouse(Map<String, Object> map) {
		//查询该人物关联的家庭
		familyDao.delSpouseRelation(map);
	}  
	
}
