package com.artmall.shiro.Realm;

import com.artmall.DO.Business;
import com.artmall.service.BusinessService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @create 2018-08-16 18:12
 **/

public class BusinessRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Autowired
    BusinessService businessService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("Business MyShiroRealm.doGetAuthenticationInfo()");


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Business business = businessService.selectBusinessByEmail(token.getUsername());
        ByteSource salt = ByteSource.Util.bytes(business.getSalt());
        if (business != null) {
            return new SimpleAuthenticationInfo(business.getEmail(), business.getHashedPwd(), salt, getName());
        }
        return null;

    }
}
