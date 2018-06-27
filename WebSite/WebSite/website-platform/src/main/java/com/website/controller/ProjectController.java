package com.website.controller;

import com.alibaba.fastjson.JSON;
import com.website.common.ServerResponse;
import com.website.controller.vo.ProjectVo;
import com.website.service.IProjectService;
import com.website.service.dto.ProjectDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Resource
    private IProjectService iprojectService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse<ProjectVo> addProject(@RequestBody ProjectVo projectVo){

        ProjectDto projectDto = JSON.parseObject(JSON.toJSONString(projectVo), ProjectDto.class);

        final ServerResponse<String> response = iprojectService.addProject(projectDto);
        return ServerResponse.createBySuccess(response.getMsg(),projectVo);
    }
}
