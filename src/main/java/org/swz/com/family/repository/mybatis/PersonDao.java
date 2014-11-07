package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.JitTreeNode;
import org.swz.com.family.entity.Person;

@MyBatisRepository
public interface PersonDao {
	
	List<Person> search(Map<String, Object> params); 
	
	List<Person> searchForTree(Map<String, Object> params);  
	
	void save(Person person);
	
	List<JitTreeNode> searchForJitTree(Map<String, Object> params);
	
	void modify(Person person);

	Person getUserPersonInfoByUserId(String userId);
	
	Person searchRootPerson(Map<String, Object> params);

	void update(Person person);

	List<Person> searchForTreeByCurrentPersonId(Map<String, Object> map);

	List<JitTreeNode> searchForJitTreeByCurrentPersonId(String personId);

	List<Person> searchFamiliesByCurrentUser(String personId);

	List<Person> getHistoricalFiguresByCurrentUser(Map<String, Object> map);

	List<Person> getMyFocus(String userId);

	List<Person> searchFocusPersons(Map<String, Object> map);

	void addFocus(List<Map<String, Object>> list);

	List<JitTreeNode> getFamilyRealtionShipByFamilyId(String familyId);

	List<Person> searchHitTerminalByFamilyId(String familyId);

	Map<String, Object> searchParentByPersonId(Map<String, Object> map);

	int checkFocus(Map<String, Object> map);

	void cancelFocus(Map<String, Object> map);

	void updatePersonHeadUrl(Map<String, Object> map); 
	
	 
}
