package com.sparrow.service;

import com.sparrow.service.dto.ProjectDto;

/**
 * example
 *
 * @author hjy
 * @date 2018/7/23
 **/
public interface ProjectService {
    public void addProject(ProjectDto project) throws Exception;
    public void updateProject(ProjectDto project) throws Exception;
    public ProjectDto queryProject(Long id) throws Exception;
    public void deleteProject(Long id) throws Exception;
}
