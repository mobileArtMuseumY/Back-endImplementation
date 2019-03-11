package com.artmall.service;

import com.artmall.DO.User;

import java.util.Set;

public interface UserService {
    Set<String> getRoles(Long id);

    Set<String> getPermissions(Long id);

    User getUser();

    void deleteUserRole(Long id);

    void addUserRole(Long id, Long roleStudent);


    void addUserRelation(Long userId, Long followedId);

    void deleteUserRelation(Long userId, Long followedId);

    User getUserByUserId(Long id);


}
