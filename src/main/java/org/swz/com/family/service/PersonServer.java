package org.swz.com.family.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.common.constants.Constants;
import org.swz.com.family.common.exception.BusinessException;
import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.FamilyForest;
import org.swz.com.family.entity.JitTreeNode;
import org.swz.com.family.entity.Person;
import org.swz.com.family.entity.RelationShip;
import org.swz.com.family.repository.mybatis.ApplyDao;
import org.swz.com.family.repository.mybatis.FamilyDao;
import org.swz.com.family.repository.mybatis.PersonDao;
import org.swz.com.family.repository.mybatis.UserDao;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.repository.mybatis.plugs.PaginationInterceptor;


@Component
@Monitored
public class PersonServer {
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private FamilyDao familyDao;
	
	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private UserDao userDao;  
	
	public JitTreeNode searchForJitTree( String personId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId" , personId);
		JitTreeNode node = new JitTreeNode();
		node.setId("0");
		node.setName("test"); 
		
		List<JitTreeNode> nodes = personDao.searchForJitTreeByCurrentPersonId(personId);  
		node.setChildren(nodes);
 
		return node;
	} 
	
	public Person searchPersonForTree( String personId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId" , personId);
		Person personRoot = new Person();
		personRoot.setPersonId("-1");
		personRoot.setFullName("家谱"); 
		
		List<Person> persons = personDao.searchForTreeByCurrentPersonId(map); 
		
		for(Person p : persons){
			if(p.getPersonId().equals(personId)){
				
			}
		}
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

	public void modifyPerson(Person person) {
		personDao.update(person); 
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("personId", person.getPersonId());
		parameters.put("nick", person.getNick());
		userDao.updateNick(parameters);
	} 

	public ShiroRealm.ShiroUser addPerson(Person person) throws BusinessException{ 
		Map<String, Object> reqMap = new HashMap<String, Object>();
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		person.setCreateUserId(user.getUserId());
		
		reqMap.put("userId", user.getUserId());
		reqMap.put("applyType", Constants.APPLY_TYPE_JOIN_FAMILY);
		if(applyDao.getApplyCountByParams(reqMap) == 0){
			 
			personDao.save(person);   
			//用户填写资料以后如过没有被匹配到家族,或者没有发出申请，就默认给用户创建一个自己的家族 
			Family family = new Family();
	    	family.setFamilyId(UUID.randomUUID().toString());
	    	family.setCreateUserId(person.getCreateUserId());
	    	family.setFamilyName(person.getFullName() + "的家族");
	    	family.setUserType(2); 
	    	family.setAreaId(person.getAddressId());
	    	family.setSurname(person.getFirstName());
	    	
	    	user.setFamilyId(family.getFamilyId());
	    	user.setFirstName(family.getSurname());
	    	user.setIsFamilyAdmin(1);
	    	user.setNick(person.getNick());
	    	user.setAddressId(person.getAddressId());
	    	user.setPersonId(person.getPersonId());
	    	
	    	RelationShip rs = new RelationShip();
			rs.setFamilyId(family.getFamilyId());
			rs.setPersonId(person.getPersonId());
//			rs.setGeneration(MapUtils.getInteger(reqMap, "generation"));
			rs.setIsFamilyAdmin(1);
			rs.setParentId("0");
			rs.setFamilyType(1);
			rs.setRelationShipType(2);
			
			FamilyForest familyForest = new FamilyForest();
			familyForest.setFamilyId(family.getFamilyId());
			familyForest.setContactPersonId(user.getPersonId());
			familyForest.setParentId(family.getFamilyId());
			familyForest.setIsChecked(1);
			familyForest.setRootId(family.getFamilyId());
			
			
			
			familyDao.save(family); 
			familyDao.saveRelationShip(rs);
			familyDao.saveFamilyForest(familyForest);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", person.getCreateUserId());
			map.put("personId", person.getPersonId()); 
			if(person.getNick() != null){
				map.put("nick", person.getNick());
			}
			userDao.setPersonIdForUser(map); 
			userDao.updateNick(map);
		}else{
			throw new BusinessException("-1", "你已经申请了一个家族，请等待审核");
		}
		
		return user;
	}

	public List<Person> searchPersonForCurentUser(String personId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId" , personId);
	 
		List<Person> persons = personDao.searchForTreeByCurrentPersonId(map); 
		
		return persons;
	}

	public List<Person> searchFamiliesByCurrentUser(String personId) {
		List<Person> persons = personDao.searchFamiliesByCurrentUser(personId);
		return persons;
	}

	public List<Person> getHistoricalFiguresByCurrentUser(String personId,int startIndex,int endIndex) {
		// TODO Auto-generated method stub
		Page page = new Page(startIndex, endIndex);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
		map.put("personId", personId);
		return personDao.getHistoricalFiguresByCurrentUser(map);
	}

	public List<Person> getMyFocus(String userId) {
		// TODO Auto-generated method stub
		return personDao.getMyFocus(userId);
	}

	public List<Person> searchFocusPersons(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return personDao.searchFocusPersons(map);
	}

	public void addFocus(String personIds, String userId) {
		// TODO Auto-generated method stub
		String[] personIdA = personIds.split(",");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> focus = null;
		for(int i = 0; i < personIdA.length; i++){
			focus = new HashMap<String , Object>();
			focus.put("userId", userId);
			focus.put("personId", personIdA[i]);
			list.add(focus);
		} 
		personDao.addFocus(list);
	}

	public JitTreeNode getFamilyRealtionShipByFamilyId(String familyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId" , familyId);
		JitTreeNode node = new JitTreeNode();
		node.setId("0");
		node.setName("test"); 
		
		List<JitTreeNode> nodes = personDao.getFamilyRealtionShipByFamilyId(familyId);  
		node.setChildren(nodes);
 
		return node;
	}

	public List<Person> searchHitTerminalByFamilyId(String familyId) {
		List<Person> nodes = personDao.searchHitTerminalByFamilyId(familyId);  
		 
		return nodes;
	}

	public int checkFocus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return personDao.checkFocus(map);
	}

	public void cancelFocus(Map<String, Object> map) {
		personDao.cancelFocus(map);
		
	}

	public void updatePersonHeadUrl(Map<String, Object> map) {
		personDao.updatePersonHeadUrl(map);
		
	} 
	
}
