package com.sparrow.artmuseum.controller;

import com.alibaba.fastjson.JSON;
import com.sparrow.artmuseum.controller.vo.ProjectVo;
import com.sparrow.artmuseum.service.IProjectService;
import com.sparrow.artmuseum.service.dto.ProjectDto;
import com.sprraw.artmuseum.common.ServerResponse;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;

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

	private static Logger logger = Logger.getLogger(ProjectController.class);

	@Resource
	private IProjectService iProjectService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ServerResponse<ProjectVo> addProject(@RequestBody ProjectVo projectVo) {

		logger.warn("--------------------ProjectController---------------------");
		logger.warn("projectVo is " + JSON.toJSONString(projectVo));

		ProjectDto projectDto = JSON.parseObject(JSON.toJSONString(projectVo), ProjectDto.class);

		final ServerResponse<String> response = iProjectService.addProject(projectDto);

		return ServerResponse.createBySuccess(response.getMsg(), projectVo);
	}

/*	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryProject(HttpServletRequest request, Model model) {

		logger.warn("--------------------ProjectController--------query-------------");
		logger.warn("projectVo is " + JSON.toJSONString(model.addAttribute("user", "pp")));

		return "111111111";
	}*/
}
