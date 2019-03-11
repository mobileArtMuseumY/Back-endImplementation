package com.artmall.Impl;

import com.artmall.DO.*;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.NullException;
import com.artmall.mapper.UserMapper;
import com.artmall.mapper.UserRelationMapper;
import com.artmall.response.Const;
import com.artmall.service.UserService;
import com.artmall.utils.IDUtils;
import com.artmall.utils.JWTUtil;
import com.artmall.utils.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2018-08-15 17:50
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public Set<String> getRoles(Long id) {
        Set<String> set=userMapper.getRoles(id);
        if (set.size()==0){
            throw new NullException("没有此角色");
        }
        return set;
    }

    @Override
    public Set<String> getPermissions(Long id) {

        Set<String> set = userMapper.getPermissions(id);
        if (set.size()==0){
            throw new NullException("没有此权限");
        }
        return set;
    }

    @Override
    public User getUser(){
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        Long id = JWTUtil.getUserNo(token);
        if (id == null)
            throw new ArtmallException("token有错误");
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<User> users= userMapper.selectByExample(example);
        if (users.size()<= 0 ){
            throw new NullException("数据库中没此用户信息");
        }
        return users.get(0);
    }

    @Override
    public void deleteUserRole(Long id) {
        User user = getUserByUserId(id);
        if (user==null){
            throw new NullException("没有此user,无法删除");
        }
        userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    public void addUserRole(Long id, Long roleStudent) {
        User user = new User();
        user.setId(Tools.initId());
        user.setRoleId(roleStudent);
        user.setUserId(id);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper.insert(user);
    }

    @Autowired
    UserRelationMapper userRelationMapper;
    @Override
    public void addUserRelation(Long userId, Long followedId) {
        UserRelation userRelation=selectUserRelationById(userId,followedId);
        if (userRelation==null){
            UserRelation newUserRelation = new UserRelation();
            newUserRelation.setFollowedId(followedId);
            newUserRelation.setRelation(Const.IS_FOLLOW);
            newUserRelation.setFollowingId(userId);
            newUserRelation.setGmtCreate(new Date());
            newUserRelation.setGmtModified(new Date());
            userRelationMapper.insert(newUserRelation);
        }else {
            userRelation.setRelation(Const.IS_FOLLOW);
            userRelation.setGmtModified(new Date());
            userRelationMapper.updateByPrimaryKey(userRelation);
        }



    }

    private UserRelation selectUserRelationById(Long userId, Long followedId) {
        UserRelationExample example = new UserRelationExample();
        UserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowedIdEqualTo(followedId);
        criteria.andFollowingIdEqualTo(userId);
        List<UserRelation> list = userRelationMapper.selectByExample(example);
        if (list.size()==0){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public void deleteUserRelation(Long userId, Long followedId) {
        UserRelation userRelation = selectUserRelationById(userId,followedId);
        userRelation.setRelation(Const.DELETE_FOLLOW);
        userRelation.setGmtModified(new Date());
        userRelationMapper.updateByPrimaryKey(userRelation);
    }




    public User getUserByUserId(Long id) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()==0){
            throw new NullException("没有此用户,用户Id为:"+id);
        }
        return users.get(0);

    }
}
