package com.sparrow.museumofart.service.impl;

import com.sparrow.museumofart.controller.vo.ProjectVo;

public interface ProjectService {
	public void addProject(ProjectVo project);
	public ProjectVo updateProject(ProjectVo project);
	public ProjectVo queryProject(long id);
	public ProjectVo deleteProject(long id);
}
