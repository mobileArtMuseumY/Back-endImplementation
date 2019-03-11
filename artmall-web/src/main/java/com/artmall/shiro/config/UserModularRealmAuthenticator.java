package com.artmall.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 自定义Authenticator,验证了一个Realm就算通过验证
 */
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
  /*  protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        UserToken userToken = (UserToken) authenticationToken;
        String loginType = userToken.getLoginType();
        Collection<Realm> realms = getRealms();
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms){
            if (realm.getName().contains(loginType))
                ((ArrayList<Realm>) typeRealms).add(realm);
        }

        if (typeRealms.size() == 1)
            return doSingleRealmAuthentication(typeRealms.iterator().next(),userToken);
        else
            return doMultiRealmAuthentication(typeRealms,userToken);
    }*/

  protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException{
      assertRealmsConfigured();
      List<Realm> realms = this.getRealms()
              .stream()
              .filter(realm -> {
                  return realm.supports(authenticationToken);
              })
              .collect(toList());
      return realms.size() == 1 ? this.doSingleRealmAuthentication(realms.iterator().next(),authenticationToken) : this.doMultiRealmAuthentication(realms,authenticationToken);
  }



}
