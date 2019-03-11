package com.artmall.service;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * 文件上传和下载
 */
public interface StorageService {
    void init();

    Path store(MultipartFile file,Path path);

    void deleteAll();

    Path[] fileUpload(String path_sign, MultipartFile []file);

    void uploadExcel(MultipartFile multipartFile) throws Exception;
//    ServerResponse<Object> addInfoAttachment(MultipartFile file,Long id);
    Path makeWatermarkPath(String path_sign, String filename);

    Path makeImageDirectoryPath(String pathSign, String originalFilename);

    Path getBusinessAttachmentPath(String pathSign,String originalFilename);

    ResponseEntity<InputStreamResource> fileDownload(String name, String attachmentName, Long attachmentSize);

    Path getProjectDirectory(Long projectId);

    Path getAvatarPath(String pathSign,String originalFilename);
}
