package com.artmall.controller.front;

import com.artmall.DO.Project;
import com.artmall.DO.User;
import com.artmall.DO.Works;
import com.artmall.DTO.projectShow.ProjectInfoToBusinessDTO;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;
import com.artmall.DTO.projectShow.ProjectInfoToStudentDTO;
import com.artmall.DTO.projectShow.ProjectInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 投标管理
 *
 * @author mllove
 * @create 2018-09-16 10:04
 **/
@RestController
public class BidController {

    @Autowired
    ProjectService projectService;

    /**
     * 学生查看项目详情
     * @param projectId
     * @return
     */
//    @RequestMapping(value = "/project/student/info",method = RequestMethod.GET)
//    public ServerResponse getProjectInfoByStudent (@RequestParam("projectId")Long projectId){
//        Project project = projectService.selectById(projectId);
//        ProjectInfoToStudentDTO projectInfo = projectService.selectProjectInfoToStudent(project);
//        if (project==null){
//            return ServerResponse.Failure("nothing");
//        }
//        else if (projectInfo.getLeftTime()<=0){
//            return ServerResponse.Expired("此项目过期",projectInfo);
//        }
//        else{
//            return ServerResponse.Success("project info show success",projectInfo);
//        }
//
//}
    @Autowired
    UserService userService;

    @Autowired
    FavoriteService favoriteService;
    @RequestMapping(value = "/project/info",method = RequestMethod.GET)
    public ServerResponse getProjectInfo (@RequestParam("projectId")Long projectId) throws Exception {
        Project project = projectService.selectById(projectId);
        //是否是本人的project
        if (projectService.isSelf(project)){
            ProjectInfoToBusinessDTO projectInfo = projectService.selectProjectInfoToSelf(project);
            return ServerResponse.Success("project business info show success",projectInfo);
        }else {
            User user = userService.getUser();
            Byte isCollect = favoriteService.isCollectProject(user.getUserId(),project.getId());
            ProjectInfoToStudentDTO projectInfo = projectService.selectProjectInfoToOthers(project);
            projectInfo.setIsCollect(isCollect);
            return ServerResponse.Success("project info show success",projectInfo);
        }
    }

    @Autowired
    BidService bidService;

    /**
     * 学生进行投标
     * @param map
     * @return
     */
    @RequestMapping(value = "/project/student/bidding",method = RequestMethod.POST )
    public ServerResponse addBid(@RequestBody Map<String,Long> map) throws Exception {
        Long projectId = map.get("projectId");
        Long worksId = map.get("worksId");
        bidService.addBidById(projectId,worksId);
        return ServerResponse.Success("投标成功");
    }




    /**
     * 企业项目信息简介
     * @param projectId
     * @return
     */
//    @RequestMapping(value = "/project/business/info",method = RequestMethod.GET )
//    public ServerResponse getProjectInfoToBusiness ( @RequestParam("projectId") Long projectId){
//        Project project = projectService.selectById(projectId);
//        ProjectInfoDTO projectInfo = projectService.selectProjectInfoToBusiness(project);
//        if (project==null)
//            return ServerResponse.Failure("nothing");
//        else if (projectInfo.getLeftTime()<=0)
//            return ServerResponse.Expired("此项目过期",projectInfo);
//        else
//            return ServerResponse.Success("project info show success",projectInfo);
//
//    }







}
