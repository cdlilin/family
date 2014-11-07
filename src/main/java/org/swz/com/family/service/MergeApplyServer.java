package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.MergeApply;
import org.swz.com.family.entity.Person;
import org.swz.com.family.repository.mybatis.FamilyDao;
import org.swz.com.family.repository.mybatis.MergeApplyDao;
import org.swz.com.family.repository.mybatis.PersonDao;
import org.swz.com.family.repository.mybatis.UserDao;

@Component
@Transactional
@Monitored
public class MergeApplyServer {
	
	@Autowired
	private MergeApplyDao applyDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FamilyDao familyDao;
	
	@Autowired
	private PersonDao personDao;
	
	
	public List<MergeApply> getApplyByUserId(Map<String, Object> params){
		return applyDao.getApplyByParams(params);
	};
	
	public List<MergeApply> getApplyForFamilyAdmin(String familyId){
		return applyDao.getApplyForFamilyAdmin(familyId);
	};
	
	public void updateApplyStatus(MergeApply apply){
		//APPLYSTATUS = 2/ APPLY_RESULT 2不同意 1 同意
		Map<String, Object> map = new HashMap<String, Object>();
		if(apply.getApplyResult() == 1){//合并
			map.put("currentFamilyId", apply.getApplyFamilyId());   
			Person root = personDao.searchRootPerson(map); 
			if(apply.getMergeType() == 2){ 
				if(root != null){ 
					map.put("parentId", apply.getMergePersonId()); 
					map.put("mergeFamilyId", apply.getMergeFamilyId());
					map.put("personId", root.getPersonId());
					map.put("relationshipType", 4);
					map.put("isFamilyAdmin", 0);  
					familyDao.updateMergeRelationShip(map); 
					
					map.clear();
					map.put("currentFamilyId", apply.getApplyFamilyId());
					map.put("mergeFamilyId", apply.getMergeFamilyId());
					familyDao.updateFamilyIdForRelationShip(map);
					
				} 
			}else{//覆盖
				map.clear();
				map.put("mergeFamilyId", apply.getMergeFamilyId());
				map.put("mergePersonId", apply.getMergePersonId());   
				Map<String, Object> relathionship = personDao.searchParentByPersonId(map);
				//删除当前的节点在家谱中的关系
				familyDao.deleteRelationShipForMerge(map);
				
				map.clear();
				map.put("currentFamilyId", apply.getApplyFamilyId());   
				map.put("parentId", relathionship.get("personId")); 
				map.put("mergeFamilyId", apply.getMergeFamilyId());
				map.put("relationshipType", relathionship.get("relationshipType"));
				map.put("personId", root.getPersonId());
				map.put("isFamilyAdmin", 0);   
				familyDao.updateMergeRelationShip(map); 
				
				map.clear();
				map.put("currentFamilyId", apply.getApplyFamilyId());
				map.put("mergeFamilyId", apply.getMergeFamilyId());
				familyDao.updateFamilyIdForRelationShip(map); 
			}  
		
		}
		familyDao.delFamilyById(apply.getApplyFamilyId());
		applyDao.updateApplyStatus(apply);
	};
	
	public void save(MergeApply apply){
		applyDao.save(apply);
	}

	public void deleteApples(String objToStr) {
		applyDao.deleteApples(objToStr);
		
	}
 

}
