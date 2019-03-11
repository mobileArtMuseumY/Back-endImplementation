package com.artmall.controller.front;

import com.artmall.DO.*;
import com.artmall.exception.NullException;
import com.artmall.response.ServerResponse;
import com.artmall.service.FavoriteService;
import com.artmall.service.ProjectService;
import com.artmall.service.UserService;
import com.artmall.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 收藏管理
 *
 * @author mllove
 * @create 2018-10-23 9:57
 **/

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/works/add",method = RequestMethod.POST )
    public ServerResponse WorksCollectAdd (@RequestBody Map<String,String> map) throws Exception {
        Long worksId = Long.valueOf(map.get("worksId"));
        User user =userService.getUser();
        Works works = worksService.selectWorksById(worksId);

        if (works.getStudentId().equals(user.getUserId())){
            return ServerResponse.Failure("本人不能收藏自己的作品");
        }
        worksService.addWorksCollectCount(worksId);

        favoriteService.addWorksCollect(user,worksId);
        return ServerResponse.Success("收藏作品成功");
    }

    @Autowired
    ProjectService projectService;
    @RequestMapping(value = "/project/add",method = RequestMethod.POST )
    public ServerResponse ProjectCollectAdd (@RequestBody Map<String,String> map) throws Exception {
        Long projectId = Long.valueOf(map.get("projectId"));
        User user =userService.getUser();
        Project project = projectService.selectById(projectId);
        if (project.getBusinessId().equals(user.getUserId())){
            return ServerResponse.Failure("本人不能收藏自己的项目");
        }
        projectService.addProjectCollectCount(projectId);

        favoriteService.addProjectCollect(user,projectId);
        return ServerResponse.Success("收藏项目成功");
    }


    @RequestMapping(value = "/works/delete",method = RequestMethod.POST )
    public ServerResponse WorksCollectDelete (@RequestBody Map<String,String> map) throws Exception {
        Long worksId = Long.valueOf(map.get("worksId"));
        worksService.deleteWorksCollectCount(worksId);
        User user =userService.getUser();
        favoriteService.deleteWorksCollect(user,worksId);
        return ServerResponse.Success("取消作品收藏成功");
    }

    @RequestMapping(value = "/project/delete",method = RequestMethod.POST )
    public ServerResponse ProjectCollectDelete (@RequestBody Map<String,String> map) throws Exception {
        Long projectId = Long.valueOf(map.get("projectId"));
        projectService.deleteProjectCollectCount(projectId);
        User user =userService.getUser();
        favoriteService.deleteProjectCollect(user,projectId);
         return ServerResponse.Success("取消项目收藏成功");
    }


    @Autowired
    WorksService worksService;
    @RequestMapping(value = "/home/favoriteWorksShow",method = RequestMethod.GET )
    public ServerResponse  showCollectWorks() {
        User user =userService.getUser();
        List<FavoriteWorks> list =favoriteService.selectWorksByUserId(user.getUserId());
        List<Works> worksList = new ArrayList<>();
        for(FavoriteWorks favoriteWorks :list){
            Works works = worksService.selectWorksById(favoriteWorks.getWorksId());
            if (works==null){
                throw new NullException("不存在这个works,worksId为:"+works.getId());
            }
            worksList.add(works);
        }
        return ServerResponse.Success("展示收藏的作品",worksList);
    }


    @RequestMapping(value = "/home/favoriteProjectShow",method = RequestMethod.GET )
    public ServerResponse  showCollectProject() {
        User user =userService.getUser();
        List<FavoriteProject> list =favoriteService.selectProjectByUserId(user.getUserId());
        List<Project> projectList = new ArrayList<>();
        for(FavoriteProject favoriteProject :list){
            Project project = projectService.selectById(favoriteProject.getProjectId());
            if (project==null){
                throw new NullException("不存在这个works,worksId为:"+project.getId());
            }
            projectList.add(project);
        }
        return ServerResponse.Success("展示收藏的项目",projectList);
    }
}
