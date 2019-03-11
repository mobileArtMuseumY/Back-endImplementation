package com.artmall.service;

import com.artmall.DO.User;

public interface FollowerSerivce {


    void add(Long userId, Long followedId);

    void delete(User user, Long followedId);

    Byte isFollowed(Long userId, Long id);

    Long getFollowingCount(Long id);

    Long getFollowedCount(Long id);
}
