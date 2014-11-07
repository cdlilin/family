package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.FamilyPersonResume;


@MyBatisRepository
public interface FamilyPersonResumeDao {
	
	 void addFamilyPersonResume(FamilyPersonResume familyPersonResume); 
	 
	 void updateFamilyPersonResume(FamilyPersonResume familyPersonResume);
	 
	 void delete(String familyPersonResumeId);  
	 
	 List<FamilyPersonResume> selectFamilyPersonResumeByPersonId(String personId);

	List<FamilyPersonResume> getFamilyContributionForPage(Map<String, Object> map);  
}
