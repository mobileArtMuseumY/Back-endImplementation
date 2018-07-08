package com.website.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.website.common.IDUtils;
import com.website.common.IOUtils;
import com.website.common.ImageUtils;
import com.website.dao.ProjectAttachmentMapper;
import com.website.dao.ProjectMapper;
import com.website.po.Project;
import com.website.po.ProjectAttachment;
import com.website.service.IProjectService;
import com.website.service.ISkillService;
import com.website.service.dto.ProjectDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ProjectAttachmentMapper projectAttachmentMapper;

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

        //生成ID给附件
        long projectId = IDUtils.generateId();
        List<ProjectAttachment> projectAttachments = convertToProjectAttachment(projectDto.getAttachmentsMap(), projectId);

        //DTO转换为PO
        final Project project = projectDto.convertToProject();
        project.setId(projectId);

        //todo 入附件表
        int projectAttachmentCount = projectAttachmentMapper.insertBatch(projectAttachments);

        if (projectAttachmentCount != projectAttachments.size()) {
            throw new Exception("附件入库失败");
        }

        final int projectCount = projectMapper.insert(project);

        if (projectCount == 0) {
            throw new Exception("发布项目失败");
        }

    }

    private List<ProjectAttachment> convertToProjectAttachment(HashMap<InputStream, String> attachmentsMap, long projectId) {

        List<ProjectAttachment> projectAttachments = Lists.newArrayListWithCapacity(attachmentsMap.size());

        for (Map.Entry<InputStream, String> entry : attachmentsMap.entrySet()) {

            //生成随机文件名的文件

            String pathDir = ImageUtils.makePathDir("projectAttachmentImg/");
            File attachment = new File(pathDir + ImageUtils.getRandomFileName() + ImageUtils.getFileExtensionName(entry.getValue()));
            IOUtils.inputStreamToFile(entry.getKey(), attachment);

            ProjectAttachment projectAttachment = new ProjectAttachment();

            projectAttachment.setId(IDUtils.generateId());

            projectAttachment.setAttachmentName(attachment.getName());

            projectAttachment.setAttachmentPath(attachment.getAbsolutePath());

            projectAttachment.setAttachmentSize(FileUtils.sizeOf(attachment));

            projectAttachment.setGmtCreate(new Date());
            projectAttachment.setGmtModified(new Date());

            projectAttachment.setProjectId(projectId);

            projectAttachments.add(projectAttachment);
        }

        return projectAttachments;
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
