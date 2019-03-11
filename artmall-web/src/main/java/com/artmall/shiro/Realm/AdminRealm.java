package com.artmall.shiro.Realm;

import com.artmall.DO.Admin;
import com.artmall.service.AdminService;
import com.artmall.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @create 2018-08-20 8:44
 **/

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//       String username = (String) principalCollection.getPrimaryPrincipal();
//       Admin admin = adminService.selectByUsername(username);
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setRoles(userService.getRoles(admin.getId()));
//        simpleAuthorizationInfo.setStringPermissions(userService.getPermissions(admin.getId()));
//        return simpleAuthorizationInfo;
        return null;
    }

    @Autowired
    AdminService adminService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("Admin MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Admin admin = adminService.selectByUsername(token.getUsername());
        ByteSource salt = ByteSource.Util.bytes(admin.getSalt());
        if (admin != null) {
            return new SimpleAuthenticationInfo(admin.getLoginName(), admin.getHashedPwd(), salt, getName());
        }
        return null;

    }
}
