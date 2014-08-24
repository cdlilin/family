package org.swz.com.family.service;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.UserDao;
@Component
@Monitored
public class UserService {
	
	@Autowired
	private UserDao userDao;
	 
	public void saveUser(User user){
		userDao.save(user);
	} 

}
