package com.artmall.controller.front;

import com.artmall.DO.User;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import com.artmall.service.FollowerSerivce;
import com.artmall.service.StudentService;
import com.artmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 关注管理
 *
 * @author mllove
 * @create 2018-11-29 16:37
 **/

@RestController
@RequestMapping("/follower")
public class FollowerController {

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

    @Autowired
    BusinessService businessService;
    @Autowired
    FollowerSerivce followerSerivce;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse addFollower(@RequestBody Map<String,String> map){
        User user=userService.getUser();
        Long followedId = Long.valueOf(map.get("followedId"));
        followerSerivce.add(user.getUserId(),followedId);
        return ServerResponse.Success("关注成功");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST )
    public ServerResponse deleteFollower(@RequestBody Map<String,String> map){
        User user = userService.getUser();
        Long followedId= Long.valueOf(map.get("followedId"));
        followerSerivce.delete(user,followedId);
        return ServerResponse.Success("取消关注成功");

    }


}
