package com.artmall.service;

import com.artmall.DO.Admin;

/**
 * @author
 * @create 2018-08-20 8:52
 **/

public interface AdminService {
    Admin selectByUsername(String username);

    Admin addUser(Admin admin);

    Admin selectByUserId(Long userid);


}
