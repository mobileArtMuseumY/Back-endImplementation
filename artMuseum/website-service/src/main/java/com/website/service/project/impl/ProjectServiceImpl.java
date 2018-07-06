package com.website.service.project.impl;

import com.website.common.IDUtils;
import com.website.common.ServerResponse;
import com.website.dao.ProjectMapper;
import com.website.pojo.Project;
import com.website.service.project.ProjectService;
import com.website.service.project.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    // TODO businessId 从session拿
    private long businessId = 1111;

    @Override
    public ServerResponse<String> addProject(ProjectDto project) {
        Project newProject = new Project();
        newProject.setId(IDUtils.getProjectId());
        newProject.setBusinessId(businessId);
        newProject.setProjectName(project.getProjectName());
        newProject.setProjectDescription(project.getProjectDescription());
        newProject.setIsVerified(Byte.valueOf("1"));//1为审核未通过
        newProject.setBudget(Long.valueOf(project.getBudget()));
        newProject.setTenderPeriod(Integer.valueOf(project.getTenderPeriod()));
        newProject.setExpectedTime(Integer.valueOf(project.getExpectedTime()));
//        newProject.setFinishTime(null);        ？？

        // TODO ?? new Date(7000) 是什么意思
        newProject.setGmtCreate(new Date(7000));
        newProject.setGmtModified(new Date(7000));
        newProject.setSkill(project.getSkillList());

        // final 修饰？
        final int insert = projectMapper.insert(newProject);
        if (insert == 0) {
            return ServerResponse.createByFailure("发布项目失败");
        }

        return ServerResponse.createBySuccess("发布项目成功");
    }

    @Override
    public ProjectDto updateProject(ProjectDto project) {
        return null;
    }

    @Override
    public ProjectDto queryProject(long id) {
        return null;
    }

    @Override
    public ProjectDto deleteProject(long id) {
        return null;
    }

    @Override
    public ProjectDto queryProjectSkill(long id) {
        return null;
    }
}
