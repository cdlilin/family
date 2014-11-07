package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.MergeApply;

@MyBatisRepository
public interface MergeApplyDao {
	
	List<MergeApply> getApplyByParams(Map<String, Object> params);
	
	int getApplyCountByParams(Map<String, Object> params);
	
	List<MergeApply> getApplyForFamilyAdmin(String familyId);
	
	void updateApplyStatus(MergeApply apply);
	
	void save(MergeApply apply);

	void deleteApples(String objToStr); 
}
