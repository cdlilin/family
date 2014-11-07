package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.swz.com.family.entity.FamilyPersonResume;
import org.swz.com.family.entity.FamilyTalkAgree;
import org.swz.com.family.entity.Talk;
import org.swz.com.family.repository.mybatis.TalkDao;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.repository.mybatis.plugs.PaginationInterceptor;


@Component
@Transactional
@Monitored
public class TalkServer {
	
	@Autowired
	private TalkDao talkDao;
	
	public List<Talk> getTalks(int startIndex, int endIndex, String personId) {
		// TODO Auto-generated method stub
		System.out.println(startIndex + "----------------------------------" + endIndex);
		Page page = new Page(startIndex, endIndex);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
		map.put("personId", personId);
		
		return talkDao.getTalksForPage(map);
	} 
	
	public void saveTalk(Talk talk) {
		 talkDao.save(talk);
		 if(talk.getTalkType() == 1){
			 talkDao.updateReplyCount(talk.getRepliedTalkId());
		 } 
	}
	
	
	public void addAgreeCount(String talkId) {
		 
		talkDao.updateAgreeCount(talkId);
		  
	}

	public Page getFamilyContribution(Map<String, Object> map ) {
		Page page = new Page(Integer.parseInt(String.valueOf(map.get("pageNum"))), Integer.parseInt(String.valueOf(map.get("pageSize"))), true);
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
	    List<Talk> resumes = talkDao.getTalksForPage(map);
	    page.setData(resumes);
	    
	    return page;
	}

	public void addAgree(FamilyTalkAgree agree) {
		talkDao.saveAgree(agree);
		
	}


}
