package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Family;
import org.swz.com.family.entity.FamilyCulture;
import org.swz.com.family.entity.FamilyForest;
import org.swz.com.family.entity.FamilyRule;
import org.swz.com.family.entity.RelationShip;


@MyBatisRepository
public interface FamilyDao {
	
	List<Family> searchFamily(Map<String, Object> map);
	 
	
	void save(Family family);


	List<Family> searchFamilyForRegUser(Map<String, Object> map);
	
	List<Family> searchHitPersonByPersonId(Map<String, Object> map); 
	

	List<Family> searchFamilyForPerson(String personId);
	
	
	void saveRelationShip(RelationShip relationShip);


	void updateRelationShip(Map<String, Object> reqMap);
	
	void updateFamilyIdForRelationShip(Map<String, Object> reqMap); 


	int searchFamilysCountForPerson(String valueOf);


	void delSpouseRelation(Map<String, Object> map);


	int searchFamilyCountByShip(Map<String, Object> map);
	
	
	int searchFamilyCountForRegUser(Map<String, Object> map);


	List<FamilyRule> getFamilyRule(Map<String, Object> map);
	
	
	List<Family> searchFamilyForManage(Map<String, Object> map);
	
	List<Family> searchFamilyForMerge(Map<String, Object> map);
	
	void saveFamilyForest(FamilyForest familyForest); 
	
	int searchContactFamily(String familyId);
	
	FamilyForest searchFamilyForest(String familyId);
	
	void updateFamilyForest(FamilyForest familyForest);


	void updateMergeRelationShip(Map<String, Object> map);


	void deleteRelationShipForMerge(Map<String, Object> map);


	void delFamilyById(String applyFamilyId);


	List<FamilyCulture> getFamilyCulture(Map<String, Object> map);


	void saveFamilyRule(FamilyRule familyRule);


	void saveFamilyCulture(FamilyCulture familyCulture);
}
