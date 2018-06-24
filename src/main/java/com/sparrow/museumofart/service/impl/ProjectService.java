package com.sparrow.museumofart.service.impl;

import com.sparrow.museumofart.controller.vo.ProjectVo;

/**
 * @program: artMesuem
 *
 * @description: 项目service
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
public interface ProjectService {

	public void addProject(ProjectVo project);
	public ProjectVo updateProject(ProjectVo project);
	public ProjectVo queryProject(long id);
	public ProjectVo deleteProject(long id);
}
