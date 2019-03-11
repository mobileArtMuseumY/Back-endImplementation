package com.artmall.Impl;

import com.artmall.exception.ArtmallException;
import com.artmall.exception.NullException;
import com.artmall.mapper.AdminMapper;
import com.artmall.DO.Admin;
import com.artmall.DO.AdminExample;
import com.artmall.response.Const;
import com.artmall.service.AdminService;
import com.artmall.service.UserService;
import com.artmall.utils.IDUtils;
import com.artmall.utils.SaltUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2018-08-20 8:53
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin selectByUsername(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    @Autowired
    UserService userService;
    @Override
    @Transactional
    public Admin addUser(Admin admin) {
        Admin newAdmin = new Admin();
        newAdmin.setId(new IDUtils(2,3).nextId());
        newAdmin.setLoginName(admin.getLoginName());
        newAdmin.setSalt(SaltUtil.InitSalt());
        newAdmin.setHashedPwd(new SimpleHash("MD5",admin.getHashedPwd(),ByteSource.Util.bytes(newAdmin.getSalt()),1024).toString());
        newAdmin.setGmtCreate(new Date());
        try {
            adminMapper.insert(newAdmin);
            userService.addUserRole(admin.getId(), Const.ROLE_ADMIN);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("数据写入失败");
        }

        return newAdmin;
    }


    @Override
    public Admin selectByUserId(Long userid) {
        Admin admin = adminMapper.selectByPrimaryKey(userid);
        if (admin==null){
            throw new NullException("没有此管理员");
        }
        return admin;
    }

}
