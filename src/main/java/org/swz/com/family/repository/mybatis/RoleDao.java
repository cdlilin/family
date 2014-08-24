package org.swz.com.family.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.swz.com.family.entity.Permission;
import org.swz.com.family.entity.Role;

/**
 * Created by star on 5/15/14.
 */
@MyBatisRepository
public interface RoleDao {

    List<Role> getRoleByAccId(int accId);
    
    List<Permission> getPermissionByRoleIds(Map<String, String> queryMap);
}
