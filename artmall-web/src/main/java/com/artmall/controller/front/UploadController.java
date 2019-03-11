package com.artmall.controller.front;

import com.artmall.DTO.NewProjectDto;
import com.artmall.DTO.NewWorksDto;
import com.artmall.DO.*;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传管理
 *
 * @author mllove
 * @create 2018-09-12 15:00
 **/
@RestController
@Api(description = "上传")

public class UploadController {

    private final static Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    UploadService uploadService;
    @Autowired
    ProjectService projectService;

    @Autowired
    SkillService skillService;
    /**
     * project表单上传
     * @param newProjectDto
     * @return
     */
    @ApiOperation("发布项目表单上传")
    @RequestMapping(value = "/project/form",method = RequestMethod.POST )
    public ServerResponse newProject (@RequestBody NewProjectDto newProjectDto) throws Exception {
        
        Project project = projectService.addProjectInfo(newProjectDto);
        uploadService.addProjectAttachmentToDatabase(newProjectDto,project);
        return ServerResponse.Success(project.getId()+"\t上传成功");
    }

    /**
     * project附件上传
     * @param files
     * @return
     */
    @ApiOperation("发布项目文件上传")
    @RequestMapping(value = "/project/upload",method = RequestMethod.POST )
    public ServerResponse projectAttachmentUpload (@RequestParam("file")MultipartFile []files){
        List<ProjectAttachment> projectAttachmentList = uploadService.uploadProjectAttachment(files);
        return ServerResponse.Success("发布项目的文件上传成功",projectAttachmentList);

    }



    @Autowired
    WorksService worksService;
    @ApiOperation("学生作品上传")
    @RequestMapping(value = "/works/form",method = RequestMethod.POST)
    public ServerResponse newWorks (@RequestBody NewWorksDto newWorksDto){
        Works newWorks = worksService.addWorksInfo(newWorksDto);

        if (newWorksDto.getWorksAttachmentList().size()!=0) {
            uploadService.addWorksAttachmentInfoToDatabase(newWorksDto, newWorks);
        }
        return ServerResponse.Success("上传成功");
    }


    /**
     * works附件上传
     * 上传后自动添加水印
     * 文件改名，并另存至另一个文件夹
     * @param files
     * @return
     */

    @RequestMapping(value = "/works/upload",method = RequestMethod.POST )
    @ApiOperation("学生作品文件上传")
    public ServerResponse worksAttachmentUpload (@RequestParam("file")MultipartFile []files){
        List<WorksAttachment> worksAttachmentList = uploadService.uploadWorksAttachment(files);
        return ServerResponse.Success("上传成功",worksAttachmentList);
    }

    @RequestMapping(value = "/works/infoShow",method = RequestMethod.POST)
    public ServerResponse showWorksInfo(@RequestBody Map<String,String> map){

        Long worksId = Long.valueOf(map.get("worksId"));
        NewWorksDto works = worksService.getInfo(worksId);
        return ServerResponse.Success("works信息",works);
    }



    @RequestMapping(value = "/works/attachment/delete",method = RequestMethod.POST)
    public ServerResponse worksAttDelete (@RequestBody Map<String,String> map){
        Long attachmentId = Long.valueOf(map.get("attachmentId"));
        WorksAttachment worksAttachment= worksService.selectAttachmentById(attachmentId);
        worksService.deleteWorksAttachemnt(worksAttachment);
        return ServerResponse.Success("附件删除成功");
    }

    @RequestMapping(value = "/works/update",method = RequestMethod.POST )
    public ServerResponse worksUpdate (@RequestBody NewWorksDto newWorksDto){
        Works newWorks = worksService.updateWorksInfo(newWorksDto);
        //更新的话，要避免附件的重复上传
//        uploadService.addWorksAttachmentInfoToDatabase(newWorksDto,newWorks);
        return ServerResponse.Success("更新成功");

    }


    /**
     *
     * @param projectId
     * @return
     */
    @ApiOperation("项目附件下载")
    @RequestMapping(value = "/projectAttachment/download",method = RequestMethod.GET )
    public String projectAttachmentDownload (@RequestParam("projectId") Long projectId, HttpServletRequest request,
                                                     HttpServletResponse response){
        List<ProjectAttachment> list = projectService.selectProjectAttachmentByProjectId(projectId);

        String zipName =  projectId.toString()+".zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename="+ zipName);

        uploadService.getZipFile(list,response);

    return "redirect:list.jhtml";
}


    /**
     * 下载企业上传的附件
     * @param businessId
     * @return
     */
    @Autowired
    BusinessService businessService;
    @RequestMapping(value = "/businessAttachment/download",method = RequestMethod.GET )
    public ResponseEntity<InputStreamResource> businessAttachmentDownload (@RequestParam("businessId") Long businessId) throws FileNotFoundException {
        BusinessAttachment businessAttachment = businessService.selectBusinessAttachmentByBusinessId(businessId);
        return uploadService.downloadBusinessAttachment(businessAttachment);

    }

    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/student/avatar",method = RequestMethod.POST)
    public ServerResponse studentAvatarUpload(@RequestParam("file") MultipartFile file){
        Student student =studentService.getStudent();
        Student now=uploadService.uploadStudentAvatar(file,student);
        return ServerResponse.Success("上传成功",now.getAvatar());
   }

   @RequestMapping(value = "/business/avatar",method = RequestMethod.POST )
   public ServerResponse businessAvatarUpload (@RequestParam("file") MultipartFile file){
        Business business = businessService.getBusiness();
        Business now = uploadService.uploadBusinessAvatar(file,business);
        return ServerResponse.Success("上传成功",now.getAvatar());
   }
}
