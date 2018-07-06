package com.website.service.project;

import com.website.common.ServerResponse;
import com.website.service.project.dto.ProjectDto;

public interface ProjectService {

    ServerResponse<String> addProject(ProjectDto project);

    ProjectDto updateProject(ProjectDto project);

    ProjectDto queryProject(long id);

    ProjectDto deleteProject(long id);

    ProjectDto queryProjectSkill(long id);
}