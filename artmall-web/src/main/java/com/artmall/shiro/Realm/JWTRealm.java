package com.artmall.shiro.Realm;

import com.artmall.DO.Student;
import com.artmall.DO.UserMember;
import com.artmall.response.Const;
import com.artmall.service.AdminService;
import com.artmall.service.BusinessService;
import com.artmall.service.StudentService;
import com.artmall.service.UserService;
import com.artmall.shiro.JWT.JWTToken;
import com.artmall.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @create 2018-08-16 14:03
 **/

public class JWTRealm extends AuthorizingRealm {
    @Autowired
    StudentService studentService;
    @Autowired
    BusinessService businessService;
    @Autowired
    AdminService adminService;

    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof JWTToken;
    }
//    public Class<?> getAuthenticationTokenClass(){
//        return JWTToken.class;
//    }
    @Autowired

    UserService userService;

    /**
     * 通过token获取到id，然后获取到角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        Long userId = JWTUtil.getUserNo(token);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.getRoles(userId));
        simpleAuthorizationInfo.setStringPermissions(userService.getPermissions(userId));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JWTToken)){ return null; }
        JWTToken jwtToken =(JWTToken) authenticationToken;
        String jwt = (String) jwtToken.getCredentials();
        UserMember user = null;
        String type;
            Long userid = JWTUtil.getUserNo(jwt);

            if (JWTUtil.getUserType(jwt).equals(String.valueOf(Const.LoginType.STUDENT))){
                user=studentService.selectStudentById(userid);
            }
            if (JWTUtil.getUserType(jwt).equals(String.valueOf(Const.LoginType.BUSINESS))){
                user=businessService.selectBusinessById(userid);
            }
            if (JWTUtil.getUserType(jwt).equals(String.valueOf(Const.LoginType.ADMIN))){

                user = adminService.selectByUserId(userid);
            }
            if (user == null)
                throw new AuthenticationException("User didn't exit it");
            if (!JWTUtil.verify(jwt,user.getId()))
                throw new AuthenticationException("username or password error");
            return new SimpleAuthenticationInfo(jwt,jwt,getName());

    }
}
