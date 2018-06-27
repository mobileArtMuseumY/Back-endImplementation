package com.website.service.impl;

import com.website.common.IDUtils;
import com.website.common.ServerResponse;
import com.website.dao.ProjectMapper;
import com.website.pojo.Project;
import com.website.service.IProjectService;
import com.website.service.dto.ProjectDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectMapper projectMapper;
    //private ProjectSkillMapper projectSkillMapper;
    private long businessId;

    @Override
    public  ServerResponse<String> addProject(ProjectDto project) {
        //插入project表数据
        Project newProject= new Project();

        newProject.setId(IDUtils.getProjectId());
        //这个时候应该是已经有BusinessID
        newProject.setBusinessId(businessId);
        newProject.setProjectName(project.getProjectName());
        newProject.setProjectDescription(project.getProjectDescription());
        newProject.setIsVaried(Byte.valueOf("1"));//1为审核未通过
        newProject.setBudget(Long.valueOf(project.getBudget()));
        newProject.setTenderPeriod(Integer.valueOf(project.getTenderPeriod()));
        newProject.setExpectedTime(Integer.valueOf(project.getExpectedTime()));
        newProject.setFinishTime(null);
        //完成时间应该不是直接获取当前时间
        newProject.setGmtCreate(new Date(7000));
        newProject.setGmtModified(new Date(7000));

        final int insert1 = projectMapper.insert(newProject);

        //插入project_skill表数据
/*
        ProjectSkill projectSkil=new ProjectSkill();
//数组的实现~
        projectSkil.setProjectId(newProject.getId());
        projectSkil.setSkillId(Long.valueOf(project.getSkillList().charAt(1)));

        final int insert2 = projectSkillMapper.insert(projectSkil);*/
        if (insert1 == 0) {
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
}
