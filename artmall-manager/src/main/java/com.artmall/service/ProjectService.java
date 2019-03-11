package com.artmall.service;

import com.artmall.DO.Business;
import com.artmall.DO.ProjectAttachment;
import com.artmall.DTO.NewProjectDto;
import com.artmall.DO.Project;
import com.artmall.DTO.home.BrowseProjectDTO;
import com.artmall.DTO.projectShow.ProjectInfoToBusinessDTO;
import com.artmall.DTO.projectShow.ProjectInfoToStudentDTO;

import java.util.List;

public interface ProjectService {
    Project addProjectInfo(NewProjectDto project) throws Exception;

    Project selectById(Long projectId);

    ProjectInfoToStudentDTO selectProjectInfoToOthers(Project project) throws Exception;

    ProjectInfoToBusinessDTO selectProjectInfoToSelf(Project project) throws Exception;

    void deadProject(Project project);

    Business selectBusinessByProjectId(Long id) throws Exception;

    List<ProjectInfoToBusinessDTO> selectProjectStatus(Long id, byte underReviewStatus) throws Exception;

    List<Project> selectProjectByBusinessId(Long businessId);

    void setProjectStatus(Project project, byte projectStatus);

    String getRole();

    boolean isSelf(Project project) throws Exception;

    List<Project> selectProjectByStatus(byte projectStatusAdd);

    void delete(Project project);

    void update(Project project);

    List<Project> show(int page, int rows);

    List<Project> showVerified(int page, int rows);

    List<ProjectAttachment> selectProjectAttachmentByProjectId(Long projectId);

    void addProjectCollectCount(Long projectId);

    void deleteProjectCollectCount(Long projectId);



    List<Project> selectProject(String condition);

    BrowseProjectDTO getBrowseProjectDto(Project project);


    List<ProjectInfoToBusinessDTO> selectProjectBidding(Long id);

    List<ProjectInfoToBusinessDTO> selectProjectFailed(Long id);
}