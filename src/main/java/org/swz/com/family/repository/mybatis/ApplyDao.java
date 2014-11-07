package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Apply;
import org.swz.com.family.entity.FamilyIncludApply;

@MyBatisRepository
public interface ApplyDao {
	
	List<Apply> getApplyByParams(Map<String, Object> params);
	
	int getApplyCountByParams(Map<String, Object> params);
	
	List<Apply> getApplyForFamilyAdmin(String familyId);
	
	void updateApplyStatus(Apply apply);
	
	void save(Apply apply);

	void deleteApples(String objToStr); 

}
