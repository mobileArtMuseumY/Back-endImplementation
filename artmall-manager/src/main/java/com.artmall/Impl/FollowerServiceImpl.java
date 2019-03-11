package com.artmall.Impl;

import com.artmall.DO.User;
import com.artmall.DO.UserRelation;
import com.artmall.DO.UserRelationExample;
import com.artmall.mapper.UserRelationMapper;
import com.artmall.response.Const;
import com.artmall.service.BusinessService;
import com.artmall.service.FollowerSerivce;
import com.artmall.service.StudentService;
import com.artmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerSerivce {


    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    BusinessService businessService;
    @Override
    public void add(Long userId, Long followedId) {
        User followedUser = userService.getUserByUserId(followedId);
        if (followedUser.getRoleId().equals(Const.ROLE_STUDENT)){
            studentService.addFollowerCount(followedUser.getUserId());
        }else if (followedUser.getRoleId().equals(Const.ROLE_BUSINESS)){
            businessService.addFollowerCount(followedUser.getUserId());
        }

        userService.addUserRelation(userId,followedId);
    }

    @Override
    public void delete(User user, Long followedId) {
        Long role = user.getRoleId();
        User followedUser = userService.getUserByUserId(followedId);
        if (followedUser.getRoleId().equals(Const.ROLE_STUDENT)){
            studentService.deleteFollowerCount(followedUser.getUserId());
        }else if (followedUser.getRoleId().equals(Const.ROLE_BUSINESS)){
            businessService.deleteFollowerCount(followedUser.getUserId());
        }
        userService.deleteUserRelation(user.getUserId(),followedId);
    }

    @Autowired
    UserRelationMapper userRelationMapper;
    @Override
    public Byte isFollowed(Long userId, Long followedId) {
        UserRelationExample example = new UserRelationExample();
        UserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowingIdEqualTo(userId);
        criteria.andFollowedIdEqualTo(followedId);
        List<UserRelation> userRelationList=userRelationMapper.selectByExample(example);
        if (userRelationList.size()!=0){
            if (userRelationList.get(0).getRelation().equals(Const.IS_FOLLOW)||userRelationList.get(0).getRelation().equals(Const.FOLLOW_EACHOTHER)){
                return Const.IS_FOLLOW;
            }
        }
        return Const.DELETE_FOLLOW;
    }

    @Override
    public Long getFollowingCount(Long id) {
        UserRelationExample example = new UserRelationExample();
        UserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowingIdEqualTo(id);
        criteria.andRelationEqualTo(Const.IS_FOLLOW);
        Long count = userRelationMapper.countByExample(example);
        return count;
    }

    @Override
    public Long getFollowedCount(Long id) {
        UserRelationExample example = new UserRelationExample();
        UserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowedIdEqualTo(id);
        criteria.andRelationEqualTo(Const.IS_FOLLOW);
        Long count = userRelationMapper.countByExample(example);
        return count;
    }
}
