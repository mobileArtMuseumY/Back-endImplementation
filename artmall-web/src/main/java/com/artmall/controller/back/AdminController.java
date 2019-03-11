package com.artmall.controller.back;

import com.artmall.DO.Admin;

import com.artmall.exception.ArtmallException;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.AdminService;
import com.artmall.service.RoleService;

import com.artmall.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mllove
 * @create 2018-08-20 10:09
 **/
@RestController

@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    //管理员登入后

    /**
     * 事务回滚还未做
     * @param map
     * @return
     */
    @ApiOperation(value = "添加管理员")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse addAdmin(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");

        Admin admin = new Admin();
        admin.setLoginName(username);
        admin.setHashedPwd(password);

        admin = adminService.addUser(admin);

        return ServerResponse.Success("add admin success");


    }

    @ApiOperation(value = "管理员登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST )
    public ServerResponse AdminLogin(@RequestBody Map<String,String> map) {

        String username = map.get("username");
        String password = map.get("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Admin admin = adminService.selectByUsername(username);
        if (admin==null){
            throw new ArtmallException("没有此用户");
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);

        }catch (UnknownAccountException uae){
            return ServerResponse.Failure("用户不存在");
        }catch (IncorrectCredentialsException ice) {
            return ServerResponse.Failure("信息不匹配");
        }catch (AuthenticationException ae){
            return ServerResponse.Failure("密码有误");
        }
        return ServerResponse.Success(JWTUtil.sign(admin.getId(),Const.LoginType.ADMIN));
    }


@RequestMapping(value = "/test",method = RequestMethod.GET)
public ServerResponse test (@RequestParam("userid")Long userId){
        Admin admin = adminService.selectByUserId(userId);
        return ServerResponse.Success("找到啦",admin);
}

}
