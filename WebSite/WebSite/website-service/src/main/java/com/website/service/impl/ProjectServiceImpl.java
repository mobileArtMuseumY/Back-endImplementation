package com.website.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.website.dao.ProjectMapper;
import com.website.po.Project;
import com.website.service.IProjectService;
import com.website.service.ISkillService;
import com.website.service.dto.ProjectDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: WebSite
 * @description: 项目处理业务类
 * @author: smallsoup
 * @create: 2018-06-30 16:48
 **/
@Service
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ISkillService iSkillService;

    @Override
    public void addProject(ProjectDto projectDto) throws Exception {

        List<Long> ids = JSONArray.parseArray(projectDto.getSkillList(), Long.class);

        //能力id不为空则检验skill表是否存在
        if (CollectionUtils.isNotEmpty(ids)) {
            final Long count = iSkillService.querySkillCountByIds(ids);

            if (count != ids.size()) {
                throw new Exception("包含不存在的能力");
            }
        }

        final Project project = projectDto.convertToProject();

        //todo 入附件表
//        projectDto.getAttachmentList();

        final int insert1 = projectMapper.insert(project);

        if (insert1 == 0) {
            throw new Exception("发布项目失败");
        }

    }

    @Override
    public void updateProject(ProjectDto project) throws Exception {
    }

    @Override
    public ProjectDto queryProject(Long id) throws Exception {

        final Project project = projectMapper.selectByPrimaryKey(id);

        if (null == project) {
            throw new Exception("项目不存在");
        }

        return new ProjectDto(project);
    }

    @Override
    public void deleteProject(Long id) throws Exception {
    }
}
