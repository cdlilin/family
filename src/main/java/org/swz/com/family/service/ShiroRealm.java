package org.swz.com.family.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.swz.com.family.entity.Role;
import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.RoleDao;
import org.swz.com.family.repository.mybatis.UserDao;

/**
 * Created by star on 5/15/14.
 */
public class ShiroRealm extends AuthorizingRealm {

    private final static Log logger = LogFactory.getLog(ShiroRealm.class);

    private UserDao userDao;

    private RoleDao roleDao;

    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	logger.info("doGetAuthorizationInfo::::");
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        logger.info("Authorization::::" + shiroUser.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
            if(shiroUser != null){
            	List<Role> roleList = shiroUser.getRoleList();
            	List<String> permissionList = shiroUser.getPermissionList();
                if(roleList != null && !roleList.isEmpty()) {
                    StringBuffer roleIdSbf = new StringBuffer();	//保存角色Id
                    for(Role role: roleList) {
                        info.addRole(role.getName());
                        roleIdSbf.append(role.getId()).append(",");
                        logger.debug("添加角色::" + role.getName() + " TO 用户:::" + shiroUser.getName());
                    }
                    
                    if(permissionList != null && !permissionList.isEmpty()) {
                    	for(String permissionStr: permissionList) {
                    		info.addStringPermission(permissionStr);
                    		logger.debug("添加权限::" + permissionStr + " TO 用户:::" + shiroUser.getName());
                        }
                    } 
                    return info;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("查询角色出错::::::");
            logger.error(e);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername(); 
        logger.info("Authentication:::::" + username + "---" + new String(usernamePasswordToken.getPassword()));
       
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        try {
            User user = userDao.getUserByUsernameAndAccId(params);
            if (user != null) {
                if ("".equals(user.getUserName())) {
                    throw new DisabledAccountException();
                }
                
                ShiroUser shiroUser = new ShiroUser(user.getUserName()); 
                
                shiroUser.setUserId(user.getId());
                shiroUser.setPersonId(user.getPersonId());
                System.out.println("返回 SimpleAuthenticationInfo" + getName());
                return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), getName());
            }
        }catch(Exception e) {
        	e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public static class ShiroUser implements Serializable {

        private String username;

        private String name; 
        
        private String userId;
        
        private String personId;
        
        private List<Role> roleList;
        
        private List<String> permissionList;

        
        

		public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
 

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        } 

       

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPersonId() {
			return personId;
		}

		public void setPersonId(String personId) {
			this.personId = personId;
		}

		public List<Role> getRoleList() {
			return roleList;
		}

		public void setRoleList(List<Role> roleList) {
			this.roleList = roleList;
		}
		

		public List<String> getPermissionList() {
			return permissionList;
		}

		public void setPermissionList(List<String> permissionList) {
			this.permissionList = permissionList;
		} 

        public ShiroUser(String username, String name) {
            this.username = username;
            this.name = name; 
        }
        
        public ShiroUser(String username) {
            this.username = username; 
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.username);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (this.username == null) {
                if (other.getUsername() != null) {
                    return false;
                }
            } else if (!this.username.equals(other.getUsername())) {
                return false;
            } 
            return true;
        }
    }
}
