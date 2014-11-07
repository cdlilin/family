package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.FamilyPersonResume;
import org.swz.com.family.repository.mybatis.FamilyPersonResumeDao;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.repository.mybatis.plugs.PaginationInterceptor;

@Component
@Transactional
@Monitored
public class FamilyPersonResumeServer {

	@Autowired
	private FamilyPersonResumeDao familyPersonResumeDao;

	public void addFamilyPersonResume(FamilyPersonResume familyPersonResume) {
		familyPersonResumeDao.addFamilyPersonResume(familyPersonResume);
	}

	public void updateFamilyPersonResume(FamilyPersonResume familyPersonResume) {
		familyPersonResumeDao.updateFamilyPersonResume(familyPersonResume);
	}

	public void delete(String familyPersonResumeId) {
		familyPersonResumeDao.delete(familyPersonResumeId);
	}

	public List<FamilyPersonResume> selectFamilyPersonResumeByPersonId(
			String personId) {
		return familyPersonResumeDao.selectFamilyPersonResumeByPersonId(personId);
		

	}
 

	public Page getFamilyContribution(String personId, int startIndex,
			int endIndex) {
		// TODO Auto-generated method stub 
		Page page = new Page(startIndex, endIndex, true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
		map.put("personId", personId);
		
	    List<FamilyPersonResume> resumes = familyPersonResumeDao.getFamilyContributionForPage(map);
	    page.setData(resumes);
	    
		return page;
	}
}
