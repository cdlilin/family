package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.FamilyTalkAgree;
import org.swz.com.family.entity.Talk;

@MyBatisRepository
public interface TalkDao {
	
	 void save(Talk talk);
	 
	 List<Talk> getTalksForPage(Map<String, Object> map);

	void updateReplyCount(String repliedTalkId);

	void updateAgreeCount(String talkId);

	void saveAgree(FamilyTalkAgree agree);

}
