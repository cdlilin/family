package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
	
	public List<Talk> getTalks(int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		Page page = new Page(startIndex, endIndex);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
		
		return talkDao.getTalksForPage(map);
	} 
	
	public void saveTalk(Talk talk) {
		 talkDao.save(talk);
		 if(talk.getTalkType() == 1){
			 talkDao.updateReplyCount(talk.getRepliedTalkId());
		 } 
	}

}
