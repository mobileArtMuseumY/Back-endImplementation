package com.artmall.service;

import com.artmall.DTO.NewProjectDto;
import com.artmall.DTO.NewWorksDto;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.DO.*;
import com.artmall.response.ServerResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author mllove
 * @create 2018-09-11 19:37
 **/

public interface UploadService  {
    BusinessAttachment uploadBusinessAttachment(MultipartFile file);

//    BusinessAttachment addBusinessAttachmentInfoToDatabase(NewBusinessDTO newBusinessDTO) throws Exception;

    List<ProjectAttachment> uploadProjectAttachment(MultipartFile[] files);

    List<WorksAttachment> uploadWorksAttachment(MultipartFile[] files);

    List<ProjectAttachment> addProjectAttachmentToDatabase(NewProjectDto newProjectDto, Project project);

    List<WorksAttachment> addWorksAttachmentInfoToDatabase(NewWorksDto newWorksDto, Works newWorks);

    ResponseEntity<InputStreamResource> downloadBusinessAttachment(BusinessAttachment businessAttachment) throws FileNotFoundException;

    void getZipFile(List<ProjectAttachment> list, HttpServletResponse response);

    Business uploadBusinessAvatar(MultipartFile file, Business business);

    Student uploadStudentAvatar(MultipartFile file, Student student);
}
