package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.RelationShip;


@MyBatisRepository
public interface FamilyDao {
	
	List<Family> searchFamily(Map<String, Object> map);
	 
	
	void save(Family family);


	List<Family> searchFamilyForRegUser(Map<String, Object> map);


	List<Family> searchFamilyForPerson(String personId);
	
	
	void saveRelationShip(RelationShip relationShip);


	void updateRelationShip(Map<String, Object> reqMap);


	int searchFamilysCountForPerson(String valueOf);


	void delSpouseRelation(Map<String, Object> map);

}
