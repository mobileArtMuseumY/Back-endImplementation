package com.artmall.Impl;

import com.artmall.DTO.NewProjectDto;
import com.artmall.DTO.NewWorksDto;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.exception.ArtmallException;
import com.artmall.mapper.*;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.StorageService;
import com.artmall.service.StudentService;
import com.artmall.service.UploadService;
import com.artmall.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件的上传和下载
 *
 * @author mllove
 * @create 2018-09-11 19:37
 **/
@Service
public class UploadServiceImpl implements UploadService {

private final static Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    StorageService storageService;

    /**
     * 将企业注册时上传的工商证明图片存入服务器，并将信息打包成javabean封装
     * @param file
     * @return
     */
    @Override
    public BusinessAttachment uploadBusinessAttachment(MultipartFile file) {

        BusinessAttachment businessAttachment =new BusinessAttachment();
        Long id = Tools.initId();

        Path  path = storageService.getBusinessAttachmentPath(id.toString(),FileUtils.creatFileName(file));

        storageService.store(file,path);
        businessAttachment.setId(id);
        businessAttachment.setAttachmentName(path.getFileName().toString());
        businessAttachment.setAttachmentPath(path.toString());
        businessAttachment.setAttachmentSize(file.getSize());
        businessAttachment.setAttachmentType("0");
        businessAttachment.setGmtCreate(new Date());
        businessAttachment.setGmtModified(new Date());

        return businessAttachment;
    }



    /**
     * 将数据录入数据库
     */
/*
    @Autowired
    BusinessAttachmentMapper businessAttachmentMapper;
    @Override
    public BusinessAttachment addBusinessAttachmentInfoToDatabase(NewBusinessDTO newBusinessDTO) throws Exception {
        try {
            BusinessAttachment businessAttachment = newBusinessDTO.getBusinessAttachment();
            businessAttachment.setBusinessId(newBusinessDTO.getId());
            businessAttachmentMapper.insert(businessAttachment);
            return businessAttachment;
        }catch (Exception e){
            throw new Exception("BusinessAttachmentInfoToDatabase插入数据错误");
        }
    }
*/

    @Autowired
    ProjectAttachmentMapper projectAttachmentMapper;

    /**
     * 将projectAttachment上传至服务器，并返回附件的信息，但是信息不存入数据库
     * @param files
     * @return
     */
    @Override
    public List<ProjectAttachment> uploadProjectAttachment(MultipartFile[] files) {

        int len = files.length;
        Long projectId =new IDUtils(2,5).nextId();
        Path [] paths= storageService.fileUpload("project//"+projectId.toString()+"//",files);
        List<ProjectAttachment> projectAttachmentList = new ArrayList<>();

        for (int i=0;i<len;i++){

            ProjectAttachment newProjectAttachment = new ProjectAttachment();
            newProjectAttachment.setProjectId(projectId);
            newProjectAttachment.setAttachmentName(paths[i].getFileName().toString());
            newProjectAttachment.setAttachmentPath(paths[i].toString());
            newProjectAttachment.setAttachmentSize(files[i].getSize());
            newProjectAttachment.setGmtCreate(new Date());
            newProjectAttachment.setGmtModified(new Date());

            projectAttachmentList.add(newProjectAttachment);
        }
        return projectAttachmentList;

//            if (!insertProjectAttachment(paths[i],files[i],id)) {
//                delectProjectAttachment(id);
//                return ServerResponse.Failure("error，请重新上传附件");
//            }
    }

    @Override
    public List<WorksAttachment> uploadWorksAttachment(MultipartFile[] files) {
        Long worksId = new IDUtils(2,12).nextId();
        String pathSign="works//"+worksId.toString()+"//";
        int len = files.length;
        //获取上传worksAttachment的路径
        Path [] paths= storageService.fileUpload(pathSign,files);
        String watermartPath = null;

        List<WorksAttachment> worksAttachmentList = new ArrayList<>();

        for (int i=0;i<len;i++) {

            //获取水印图片的路径
            watermartPath = storageService.makeWatermarkPath(pathSign, paths[i].getFileName().toString()).toString();
            //生成水印图片并存入
            ImageUtils.generateWatermark(paths[i].toString(), watermartPath);


            WorksAttachment worksAttachment = new WorksAttachment();
            worksAttachment.setAttachmentName(paths[i].getFileName().toString());
            worksAttachment.setAttachmentPath(paths[i].toString());
            worksAttachment.setAttachmentSize(files[i].getSize());
            worksAttachment.setId(Tools.initId());
            worksAttachment.setWorksId(worksId);
            worksAttachment.setGmtCreate(new Date());
            worksAttachment.setGmtModified(new Date());
            worksAttachment.setAttachmentWatermarkPath(watermartPath);


            worksAttachmentList.add(worksAttachment);
        }
        return worksAttachmentList;
    }

    /**
     * 加上projectId至ProjectAttachment中，然后上传至数据库中
     * @param newProjectDto
     * @return
     */
    @Override
    public List<ProjectAttachment> addProjectAttachmentToDatabase(NewProjectDto newProjectDto,Project project) {
        List<ProjectAttachment> projectAttachmentList = newProjectDto.getProjectAttachmentList();
        for (ProjectAttachment projectAttachment:projectAttachmentList){
            projectAttachment.setProjectId(project.getId());
            projectAttachmentMapper.insert(projectAttachment);
        }
        return projectAttachmentList;
    }

    @Override
    public List<WorksAttachment> addWorksAttachmentInfoToDatabase(NewWorksDto newWorksDto, Works newWorks) {
        List<WorksAttachment> worksAttachmentList = newWorksDto.getWorksAttachmentList();
        for (WorksAttachment worksAttachment:worksAttachmentList){
            if (worksAttachmentMapper.selectByPrimaryKey(worksAttachment.getId())==null){
                worksAttachment.setWorksId(newWorks.getId());
                worksAttachmentMapper.insert(worksAttachment);
            }

        }
        //添加作品的封面信息
        addWorksShowFile(worksAttachmentList.get(0),newWorks);

        return worksAttachmentList;

    }

    @Autowired
    private ServletContext servletContext;

    /**
     * 下载企业附件
     * @param businessAttachment
     */
    @Override
    public ResponseEntity<InputStreamResource> downloadBusinessAttachment(BusinessAttachment businessAttachment) throws FileNotFoundException {

       return storageService.fileDownload(businessAttachment.getAttachmentName(),businessAttachment.getAttachmentPath(),businessAttachment.getAttachmentSize());

    }

    @Override
    public void getZipFile(List<ProjectAttachment> list, HttpServletResponse response) {
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataOutputStream os = null;
        for (ProjectAttachment projectAttachment :list){
            log.info(projectAttachment.getAttachmentName());
            File file = new File(projectAttachment.getAttachmentPath());
            try {
                int num = (int) Math.random();
                zipos.putNextEntry(new ZipEntry(projectAttachment.getAttachmentName()));
                os = new DataOutputStream(zipos);
                InputStream is = new FileInputStream(file);
                byte[] b=new byte[100];
                int length = 0;
                while((length = is.read(b))!= -1){
                    os.write(b, 0, length);
                }
                is.close();
                zipos.closeEntry();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //关闭流
        try {
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    BusinessMapper businessMapper;
    @Override
    @Transactional
    public Business uploadBusinessAvatar(MultipartFile file, Business business) {
        String pathSign = "avatar//"+business.getId().toString()+"//";
        String fileName = FileUtils.creatFileName(file);
        //获取头像上传的路径
        Path path = storageService.getAvatarPath(pathSign,fileName);

        try {
                      //上传头像
            storageService.store(file,path);

            business.setGmtModified(new Date());
            business.setAvatar(path.toString());
            businessMapper.updateByPrimaryKey(business);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("头像上传失败");
        }
        return business;

    }

    @Autowired
    StudentMapper studentMapper;
    @Override
    @Transactional
    public Student uploadStudentAvatar(MultipartFile file, Student student) {
        String pathSign = "avatar//"+student.getId().toString()+"//";
        String fileName = FileUtils.creatFileName(file);
        //获取头像上传的路径
        Path path = storageService.getAvatarPath(pathSign,fileName);

        try {
            //上传头像
            storageService.store(file,path);

            student.setGmtModified(new Date());
            student.setAvatar(path.toString());
            studentMapper.updateByPrimaryKey(student);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("头像上传失败");
        }
        return student;

    }

    /**
     * 封面图选取附件的第一张图片，找到其水印图片的路径，生成一份缩略图,将缩略图的信息上传至数据库和服务器
     * @param worksAttachment
     * @param newWorks
     */
    private void addWorksShowFile(WorksAttachment worksAttachment, Works newWorks) {
        String waterPath = worksAttachment.getAttachmentWatermarkPath();
        String showName = worksAttachment.getAttachmentName();
        Path directoryPath = addWorksShowFileToServer(newWorks.getId(),worksAttachment.getAttachmentName(),waterPath);
        newWorks.setAttachmentShowName(showName);
        newWorks.setAttachmentShowPath(directoryPath.toString());
        worksMapper.updateByPrimaryKey(newWorks);

    }

    @Autowired
    WorksMapper worksMapper;
    private Path addWorksShowFileToServer(Long worksId,String fileName,String waterPath) {
        Path showfilePath = storageService.makeImageDirectoryPath(worksId.toString(),fileName);
        ImageUtils.generateDirectoryThumbnail(waterPath,showfilePath.toString());
        return showfilePath;
    }


    @Autowired
    WorksAttachmentMapper worksAttachmentMapper;
    private void delectWorksAttachment(Long worksId) {
        WorksAttachmentExample ex = new WorksAttachmentExample();
        WorksAttachmentExample.Criteria criteria = ex.createCriteria();
        criteria.andWorksIdEqualTo(worksId);
        worksAttachmentMapper.deleteByExample(ex);
    }


    private boolean insertWorksAttachment(Path path, MultipartFile file, Long worksId,String watermarkPath) {

        WorksAttachment worksAttachment = new WorksAttachment();
        worksAttachment.setAttachmentName(file.getOriginalFilename());
        worksAttachment.setAttachmentPath(path.toString());
        worksAttachment.setAttachmentSize(file.getSize());
        worksAttachment.setWorksId(worksId);
        worksAttachment.setGmtCreate(new Date());
        worksAttachment.setGmtModified(new Date());
        worksAttachment.setAttachmentWatermarkPath(watermarkPath);
        try {
            worksAttachmentMapper.insert(worksAttachment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 删除此project下的所有附件
     * @param id
     */
    private void delectProjectAttachment(Long id) {
        ProjectAttachmentExample ex = new ProjectAttachmentExample();
        ProjectAttachmentExample.Criteria criteria = ex.createCriteria();
        criteria.andProjectIdEqualTo(id);
        projectAttachmentMapper.deleteByExample(ex);
    }

    private boolean insertProjectAttachment(Path path,MultipartFile file, Long id) {
        ProjectAttachment projectAttachment = new ProjectAttachment();

        projectAttachment.setAttachmentName(file.getOriginalFilename());
        projectAttachment.setAttachmentPath(path.toString());
        projectAttachment.setAttachmentSize(file.getSize());
        projectAttachment.setProjectId(id);
        projectAttachment.setGmtModified(new Date());
        projectAttachment.setGmtCreate(new Date());
        try {
            projectAttachmentMapper.insert(projectAttachment);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
