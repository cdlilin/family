package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.plugs.Page;

/**
 * Created by star on 5/15/14.
 */
@MyBatisRepository
public interface UserDao {
    User getUserByParam(Map<String, Object> params);

    List<Map<String, Object>> getAccountbyUsername(String username);

	List<User> getAccountForPage(Map<String, Object> reqMap);
	
	User get(Long id);

	List<User> search(Map<String, Object> parameters);

	void save(User user);

	void delete(Long id);
	
	void setPersonIdForUser(Map<String, Object> parameters);

	void updateUserStatus(User user);

	void updateUserConfirmCode(User confirmUser);

	void setPassword(User user);
	
	void updateNick(Map<String, Object> parameters);
}
