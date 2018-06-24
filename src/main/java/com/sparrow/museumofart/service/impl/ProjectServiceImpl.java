package com.sparrow.museumofart.service.impl;

import com.sparrow.museumofart.controller.vo.ProjectVo;
import com.sparrow.museumofart.dao.ProjectDao;
import com.sparrow.museumofart.dao.entity.Project;
import com.sparrow.museumofart.util.IDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @program: artMesuem
 *
 * @description: 项目service实现类
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Override
    public void addProject(ProjectVo projectVo) {

        Project project = new Project();

        project.setId(IDUtils.genItemId());
        project.setBusinessId(IDUtils.genItemId());
        project.setBudget(new BigDecimal(1000));
        project.setExpectedTime(Integer.valueOf(projectVo.getExpectedTime()));
        project.setFinishTime(new Timestamp(9000));
        project.setGmtCreate(new Timestamp(8000));
        project.setGmtModified(new Timestamp(7000));

        /*projectVo.getAttachmentList();
        projectVo.getSkillList();*/

        project.setBudget(new BigDecimal(projectVo.getBudget()));
        project.setProjectDescription(projectVo.getProjectDescription());
        project.setProjectName(projectVo.getProjectName());
        project.setIsVarified(2);
        project.setTenderPeriod(Integer.valueOf(projectVo.getTenderPeriod()));

        projectDao.addProject(project);
    }

    @Override
    public ProjectVo updateProject(ProjectVo project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectVo queryProject(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectVo deleteProject(long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
