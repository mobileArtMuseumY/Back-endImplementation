package com.website.controller;

import com.website.common.ServerResponse;
import com.website.service.impl.ProjectService;
import com.website.vo.ProjectVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @RequestMapping(value = "/append",method = RequestMethod.POST)
    public ServerResponse<ProjectVo> addProject(@RequestBody ProjectVo project){
        ProjectService projectService =new ProjectService();
        final ServerResponse<String> response = projectService.addProject(project);
        return ServerResponse.createBySuccess(response.getMsg(),project);
    }
}
