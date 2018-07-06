package com.website.controller.project;

import com.alibaba.fastjson.JSON;
import com.website.common.ServerResponse;
import com.website.controller.project.vo.ProjectVo;
import com.website.service.project.ProjectService;
import com.website.service.project.dto.ProjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hjy
 * @Date 2018/7/5 14:34
 * @Version 1.0
 **/
@Api(tags = "项目相关接口")
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "项目发布")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse<ProjectVo> addProject(@RequestBody ProjectVo projectVo) {

        ProjectDto projectDto = JSON.parseObject(JSON.toJSONString(projectVo), ProjectDto.class);

        final ServerResponse<String> response = projectService.addProject(projectDto);
        return ServerResponse.createBySuccess(response.getMsg(), projectVo);
    }

}
