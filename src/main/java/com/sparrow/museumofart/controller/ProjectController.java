package com.sparrow.museumofart.controller;

import com.alibaba.fastjson.JSON;
import com.sparrow.museumofart.controller.util.BaseResult;
import com.sparrow.museumofart.controller.vo.ProjectVo;
import com.sparrow.museumofart.service.impl.ProjectService;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	private ProjectService projectService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResult addProject(@RequestBody ProjectVo projectVo) {

		logger.warn("--------------------ProjectController---------------------");
		logger.warn("projectVo is " + JSON.toJSONString(projectVo));
		projectService.addProject(projectVo);

		return new BaseResult(200, "ok", JSON.toJSONString(projectVo));

	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryProject(HttpServletRequest request, Model model) {

		logger.warn("--------------------ProjectController--------query-------------");
		logger.warn("projectVo is " + JSON.toJSONString(model.addAttribute("user", "pp")));

		return "111111111";

	}
}
