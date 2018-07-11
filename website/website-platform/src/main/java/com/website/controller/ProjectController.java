package com.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.website.common.HttpServletRequestUtils;
import com.website.common.ResponseCode;
import com.website.common.ServerResponse;
import com.website.controller.vo.ProjectVo;
import com.website.service.IProjectService;
import com.website.service.dto.ProjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @program: artMesuem
 *
 * @description: 项目controller
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private IProjectService iprojectService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse<ProjectVo> addProject(HttpServletRequest request){

        try {
            String projectStr = HttpServletRequestUtils.getString(request, "projectStr");

            if (null == projectStr) {
                return ServerResponse.createByFailure("projectStr is null");
            }

            //转换表单json为实体类
            ProjectVo projectVo = JSONObject.parseObject(projectStr, ProjectVo.class);

            LOGGER.debug("projectVo is {}", projectVo);

            List<MultipartFile> projectAttachments;

            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //判断是否有文件流
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

                projectAttachments = multipartHttpServletRequest.getFiles("projectAttachments");
            } else {
                return ServerResponse.createByFailure("请上传附件！");
            }

            //执行注册
            if (projectAttachments == null || projectAttachments.isEmpty()) {
                return ServerResponse.createByFailure("请上传附件！");
            }

            //key为文件输入流,V为文件名
            HashMap<InputStream, String> attachmentsMap = Maps.newHashMapWithExpectedSize(projectAttachments.size());

            for ( MultipartFile attachment:projectAttachments) {
                attachmentsMap.put(attachment.getInputStream(), attachment.getOriginalFilename());
            }

            projectVo.setAttachmentsMap(attachmentsMap);

            iprojectService.addProject(projectVo.convertProjectDto());

        } catch (Exception e) {
            LOGGER.error("ProjectController addProject exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public ServerResponse<ProjectVo> getProjectById(@RequestParam("projectId") Long projectId){

        if (null == projectId){
            return ServerResponse.createByFailure(ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        try {
            final ProjectDto projectDto = iprojectService.queryProject(projectId);

            ProjectVo projectVo = new ProjectVo(projectDto);

            return ServerResponse.createBySuccess(projectVo);
        } catch (Exception e) {
            LOGGER.error("ProjectController getProjectById exception is {}", e);
            return ServerResponse.createByFailure();
        }

    }
}
