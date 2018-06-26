package com.website.service.impl;

import com.website.pojo.*;
import com.website.pojo.ProjectSkill;
import com.website.vo.ProjectVo;
import com.website.common.*;
import com.website.dao.*;
import com.website.service.inter.ProjectServiceIn;

import javax.annotation.Resource;
import java.sql.Date;

public class ProjectService implements ProjectServiceIn {

    @Resource
    private ProjectMapper projectMapper;
    private ProjectSkillMapper projectSkillMapper;
    private long businessId;
    @Override

    public  ServerResponse<String> addProject(ProjectVo project) {
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

        ProjectSkill projectSkil=new ProjectSkill();
/*数组的实现~
        projectSkil.setProjectId(newProject.getId());
        projectSkil.setSkillId()
        */
        final int insert2 = projectSkillMapper.insert(projectSkil);
        if (insert1 == 0&&insert2==0) {
            return ServerResponse.createByFailure("发布项目失败");
        }

        return ServerResponse.createBySuccess("发布项目成功");


    }
}
