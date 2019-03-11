package com.artmall.shiro.config;

/**
 * 配置shiro
 *
 * @author
 * @create 2018-08-08 10:18
 **/

import com.artmall.shiro.JWT.JWTToken;
import com.artmall.shiro.Realm.AdminRealm;
import com.artmall.shiro.Realm.BusinessRealm;
import com.artmall.shiro.Realm.JWTRealm;
import com.artmall.shiro.Realm.StudentRealm;

import com.artmall.shiro.filter.JWTFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;


/**
 * shiro核心通过Filter实现，通过URL规则进行过滤和权限校验
 * 配置shiro，相当于ssm结构中xml文件
 */
@Configuration
public class ShiroConfiguration {



    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //把自定义的filter集成到shiro配置里面，添加自定义拦截器
        Map<String,Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //定义url规则
        Map<String,String> filterRuleMap = new HashMap<>();

        filterRuleMap.put("/*/login","anon");
        filterRuleMap.put("/*/download/**","anon");
        filterRuleMap.put("/count/*","anon");
        filterRuleMap.put("/*/password/**","anon");
        filterRuleMap.put("/alipay/*/notic","anon");
        filterRuleMap.put("/business/register/**","anon");
        filterRuleMap.put("/show/**","anon");
        filterRuleMap.put("/image/**","anon");
        filterRuleMap.put("401","anon");
//        filterRuleMap.put("/**","anon");
        filterRuleMap.put("/**","jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;

        //        Map<String,String> filterChainDefinitionMap =  new LinkedHashMap<String,String>();
        //配置不会被拦截的链接 顺序判断
        //anon:所有url都可以匿名访问


//        filterChainDefinitionMap.put("/static/**","anon");
//        //配置退出,shiro已内置logout过滤器
//        filterChainDefinitionMap.put("/logout","logout");
//        //
//        filterChainDefinitionMap.put("/register","anon");
//        filterChainDefinitionMap.put("/hello","anon");
//        filterChainDefinitionMap.put("/info","anon");
//        filterChainDefinitionMap.put("/login","anon");


//        //表示需要认证，没有登陆是不能访问的
//        filterChainDefinitionMap.put("/**","authc");
//        //配置shiro默认登陆界面，不过在前后端分离中应该有前端路由控制
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
//        /*shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        shiroFilterFactoryBean.setUnauthorizedUrl("/401");

    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }
    @Bean
    public StudentRealm studentRealm(){
        StudentRealm studentRealm = new StudentRealm();
        studentRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        studentRealm.setAuthenticationTokenClass(UserToken.class);
        return studentRealm;
    }

    @Bean
    public BusinessRealm businessRealm(){
        BusinessRealm businessRealm = new BusinessRealm();
        businessRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        businessRealm.setAuthenticationTokenClass(UserToken.class);
        return businessRealm;
    }
    @Bean
    public AdminRealm adminRealm(){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }
//   @Bean
//   public StudentRealm myRealm(){
//        StudentRealm myRealm = new StudentRealm();
//        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//       return myRealm;
//   }

   @Bean
   public JWTRealm jwtRealm(){
       JWTRealm jwtRealm = new JWTRealm();
       jwtRealm.setAuthenticationTokenClass(JWTToken.class);
       return jwtRealm;
   }

    @Bean
    public SecurityManager securityManager(){
       System.out.println("securityManager.log");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new LinkedList<>();
//        //添加多个Realm
        realms.add(studentRealm());
        realms.add(businessRealm());
        realms.add(adminRealm());
        realms.add(jwtRealm());
//        realms.add(myRealm());

//        securityManager.setRealm(myRealm());
        securityManager.setRealms(realms);


        /*
        关闭shiro自带的session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    /**
     * Realm管理(使用自己重写的Realm策略，只要一个通过验证就通过验证)
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }



}
