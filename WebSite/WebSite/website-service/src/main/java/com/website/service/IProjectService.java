package com.website.service;


import com.website.common.ServerResponse;
import com.website.service.dto.ProjectDto;

/**
 * @program: artMesuem
 *
 * @description: 项目service
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
public interface IProjectService {

	public ServerResponse<String> addProject(ProjectDto project);
	public ProjectDto updateProject(ProjectDto project);
	public ProjectDto queryProject(long id);
	public ProjectDto deleteProject(long id);
}
