package com.sparrow.artmuseum.service.impl;

import com.sparrow.artmuseum.dao.ProjectMapper;
import com.sparrow.artmuseum.pojo.Project;
import com.sparrow.artmuseum.service.IProjectService;
import com.sparrow.artmuseum.service.dto.ProjectDto;
import com.sprraw.artmuseum.common.IDUtils;
import com.sprraw.artmuseum.common.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.sql.Timestamp;

/**
 * @program: artMesuem
 * @description: 项目service实现类
 * @author: smallsoup
 * @create: 2018/6/24
 **/
@Service("iProjectService")
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public ServerResponse<String> addProject(ProjectDto projectDto) {
        Project project = new Project();

        project.setId(IDUtils.genItemId());
        project.setBusinessId(IDUtils.genItemId());
        project.setBudget(Long.valueOf(projectDto.getBudget()));
        project.setExpectedTime(Integer.valueOf(projectDto.getExpectedTime()));
        project.setFinishTime(new Timestamp(9000));
        project.setGmtCreate(new Timestamp(8000));
        project.setGmtModified(new Timestamp(7000));

        //todo 存到其他库
        //  projectDto.getAttachmentList();
        //todo 存到其他库
    //        projectDto.getSkillList();

        project.setProjectDescription(projectDto.getProjectDescription());
        project.setProjectName(projectDto.getProjectName());
        project.setIsVaried(Byte.valueOf("1"));
        project.setTenderPeriod(Integer.valueOf(projectDto.getTenderPeriod()));

        final int insert = projectMapper.insert(project);

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

}
