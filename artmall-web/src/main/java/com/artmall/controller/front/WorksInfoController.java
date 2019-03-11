package com.artmall.controller.front;

import com.artmall.DO.User;
import com.artmall.DTO.home.WorksInfoDTO;
import com.artmall.response.ServerResponse;
import com.artmall.service.ContentService;
import com.artmall.service.FavoriteService;
import com.artmall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class WorksInfoController {


    @Autowired
    UserService userService;
    @Autowired
    FavoriteService favoriteService;

    @Autowired
    ContentService contentService;
    @ApiOperation("查看某个作品的详情")
    @RequestMapping(value = "/works/attachment",method = RequestMethod.GET)
    public ServerResponse<WorksInfoDTO> showWorksAttachment (@RequestParam("worksId")Long worksId) throws Exception {


        User user = userService.getUser();

        Byte collectStatus = favoriteService.isCollectWorks(user.getUserId(),worksId);
        WorksInfoDTO worksInfoDTO = contentService.getWorksInfoByWorksId(worksId);
        worksInfoDTO.setIsCollect(collectStatus);
        return ServerResponse.Success("展示成功", worksInfoDTO);
    }
}
