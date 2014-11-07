package org.swz.com.family.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.UserDao;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.repository.mybatis.plugs.PaginationInterceptor;

/**
 * Created by star on 5/15/14.
 */
@Component
@Monitored
public class LoginService {

    @Autowired
    private UserDao userDao;

    public List<Map<String, Object>> getAccountByLoginName(String username) {
        return userDao.getAccountbyUsername(username);
    }
    
	public List<User> getAccountForPage(String username) {
		// TODO Auto-generated method stub
		Page page = new Page(0, 20);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PaginationInterceptor.DEFAULT_PAGE_PARAM_KEY, page);
		map.put("user_name", "lilin");
		
		return userDao.getAccountForPage(map);
	}
	
	
	public User login(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		
		return userDao.getUserByParam(map);
	}
	
	
}
