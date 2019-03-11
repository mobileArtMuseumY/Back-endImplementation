package com.artmall.Impl;

import com.artmall.exception.ArtmallException;
import com.artmall.mapper.UserMapper;
import com.artmall.DO.*;
import com.artmall.service.RoleService;
import com.artmall.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author mllove
 * @create 2018-09-26 10:31
 **/
@Service
public class RoleServiceImpl implements RoleService {

    private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static final Long STUDENT_ROLE = Long.valueOf(1);
    private static final Long BUSINESS_ROLE = Long.valueOf(2);
    private static final Long ADMIN_ROLE = Long.valueOf(3);


    @Autowired
    UserMapper userMapper;
    @Override
    public void addUserRole(Object object,Long id){
        User user = new User();
        Long roleId = null;
        if (object instanceof Business) {
            roleId = BUSINESS_ROLE;
        } else if (object instanceof Student) {
            roleId = STUDENT_ROLE;
        } else if (object instanceof Admin) {
            roleId = ADMIN_ROLE;
        } else {
            throw new ArtmallException("不存在这个角色");
        }
        user.setId(new IDUtils(6, 7).nextId());
        user.setUserId(id);
        user.setRoleId(roleId);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        if (userMapper.insert(user) != 1) {
            throw new ArtmallException("录入角色失败");
        }
    }
}
