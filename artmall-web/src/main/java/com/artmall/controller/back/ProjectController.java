package com.artmall.controller.back;

import com.artmall.DO.OrderForm;
import com.artmall.DO.Project;
import com.artmall.DO.Student;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.ProjectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mllove
 * @create 2018-11-14 11:29
 **/

@RestController
@RequestMapping("/projectController")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * 删除project及其对应的附件，skill，complaint，favorite_project
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ServerResponse delete (@RequestBody Map<String,String> map){
        Long projectId = Long.valueOf(map.get("projectId"));
        Project project = projectService.selectById(projectId);
        projectService.delete(project);
        return ServerResponse.Success("删除成功");
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ServerResponse update (@RequestBody Project project){
         projectService.update(project);
        return ServerResponse.Success("更改成功");
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET )
    public ServerResponse show (@RequestParam("page") int page,
                                @RequestParam("rows") int rows){
        List<Project> projects = new ArrayList<>();
        projects = projectService.show(page, rows);
        return ServerResponse.Success("展示成功",projects);
    }

    /**
     * 审核作品，审核通过为3，未通过未1
     * @return
     */
    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    public ServerResponse verify (@RequestBody Map<String,String> map){
        Long projectId = Long.valueOf(map.get("projectId"));
        Byte isVerified = Byte.valueOf(map.get("status"));
        Project project = projectService.selectById(projectId);
        projectService.setProjectStatus(project,isVerified);
        return ServerResponse.Success("审核完毕");
    }

    /**
     * 展示未审核的project
     * @return
     */
    @RequestMapping(value = "/show/verify",method = RequestMethod.GET )
    public ServerResponse showVerify (@RequestParam("page") int page,
                                      @RequestParam("rows") int rows) throws Exception {
        List<Project> projects = projectService.showVerified(page,rows);
        return ServerResponse.Success("展示成功",projects);
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.POST )
    public ServerResponse deleteAll (@RequestBody Map<String,String> map){
        String data = map.get("id");
        String params[] = data.split(",");
        for (int i=0;i<params.length;i++){
            if (params[i]==null){
                i++;
            }
            Project project = projectService.selectById(Long.valueOf(params[i]));
           projectService.delete(project);
        }
        return ServerResponse.Success("删除成功");
    }
}
