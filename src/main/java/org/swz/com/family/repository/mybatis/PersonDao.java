package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Person;

@MyBatisRepository
public interface PersonDao {
	
	List<Person> search(Map<String, Object> params); 
	
	List<Person> searchForTree(Map<String, Object> params);  
	
	void save(Person person);
	
	void modify(Person person);

	Person getUserPersonInfoByUserId(String userId); 
	
	 
}
