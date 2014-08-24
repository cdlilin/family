package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Apply;

@MyBatisRepository
public interface ApplyDao {
	
	List<Apply> getApplyByUserId(String userId);
	
	List<Apply> getApplyForFamilyAdmin(String familyId);
	
	void updateApplyStatus(Apply apply);
	
	void save(Apply apply); 

}
