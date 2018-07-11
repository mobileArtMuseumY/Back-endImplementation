package com.website.service;


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

	public void addProject(ProjectDto project) throws Exception;
	public void updateProject(ProjectDto project) throws Exception;
	public ProjectDto queryProject(Long id) throws Exception;
	public void deleteProject(Long id) throws Exception;
}
