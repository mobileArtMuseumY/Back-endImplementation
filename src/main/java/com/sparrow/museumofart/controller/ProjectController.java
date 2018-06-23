package com.sparrow.museumofart.controller;

import javax.annotation.Resource;

import com.sparrow.museumofart.controller.util.BaseResult;
import com.sparrow.museumofart.controller.vo.ProjectVo;
import com.sparrow.museumofart.service.impl.ProjectService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

	private static Logger logger = Logger.getLogger(ProjectController.class);

	@Resource
	private ProjectService projectService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResult addProject(@RequestBody ProjectVo projectVo) {

		logger.warn("--------------------ProjectController---------------------");
		logger.warn("projectVo is " + JSON.toJSONString(projectVo));
		projectService.addProject(projectVo);

		return new BaseResult(200, "ok", JSON.toJSONString(projectVo));

	}
}
