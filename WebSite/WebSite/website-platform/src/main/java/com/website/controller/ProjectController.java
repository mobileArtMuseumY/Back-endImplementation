package com.website.controller;

import com.website.common.ResponseCode;
import com.website.common.ServerResponse;
import com.website.controller.vo.ProjectVo;
import com.website.service.IProjectService;
import com.website.service.dto.ProjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: artMesuem
 *
 * @description: 项目controller
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private IProjectService iprojectService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse<ProjectVo> addProject(@RequestBody ProjectVo projectVo){

        LOGGER.info("ProjectController projectVo is {}", projectVo);

        if (null == projectVo){
            return ServerResponse.createByFailure(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {

            iprojectService.addProject(projectVo.convertProjectDto());

        } catch (Exception e) {
            LOGGER.error("ProjectController addProject exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess(projectVo);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public ServerResponse<ProjectVo> getProjectById(@RequestParam("projectId") Long projectId){

        if (null == projectId){
            return ServerResponse.createByFailure(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            final ProjectDto projectDto = iprojectService.queryProject(projectId);

            ProjectVo projectVo = new ProjectVo(projectDto);

            return ServerResponse.createBySuccess(projectVo);
        } catch (Exception e) {
            LOGGER.error("ProjectController getProjectById exception is {}", e);
            return ServerResponse.createByFailure();
        }

    }
}
