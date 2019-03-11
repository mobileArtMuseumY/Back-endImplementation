package com.artmall.controller.back;

import com.artmall.DO.Student;
import com.artmall.DTO.studentController.ShowDTO;
import com.artmall.DTO.studentController.UpStuDTO;
import com.artmall.response.ServerResponse;
import com.artmall.service.StorageService;
import com.artmall.service.StudentService;
import com.artmall.DTO.studentController.NewStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 学生管理
 *
 * @author
 * @create 2018-09-07 13:47
 **/
@RequestMapping("/studentController")
@RestController
public class MemberStudentController {

    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse addStudent (@RequestBody NewStudentDTO newStudent) throws Exception {
        Student student = studentService.addUser(newStudent);
        return ServerResponse.Success("学生初始化成功");
    }

    /**
     * 通过excel批量导入
     * @return
     */
    @Autowired
    StorageService storageService;
    @RequestMapping(value = "/add/excel",method = RequestMethod.POST)
    public ServerResponse addByExcel(@RequestParam("file")MultipartFile multipartFile) throws Exception {
        storageService.uploadExcel(multipartFile);
        return ServerResponse.Success("导入成功");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ServerResponse delete (@RequestBody Map<String,String> map){
        Long studentId = Long.valueOf(map.get("studentId"));
        Student student = studentService.selectStudentById(studentId);
        studentService.delete(student);
        return ServerResponse.Success("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ServerResponse update (@RequestBody UpStuDTO upStuDTO){
        studentService.update(upStuDTO);
        return ServerResponse.Success("更新成功");
    }

    /**
     * 分页展示，并排序
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET )
    public ServerResponse show (@RequestParam("page") int page,
                                @RequestParam("rows") int rows){
        List<ShowDTO> student = studentService.show(page,rows);
        return ServerResponse.Success("展示成功",student);
    }

    /**
     *学生密码初始化
     */
    @RequestMapping(value = "/init/pwd",method = RequestMethod.POST )
    public ServerResponse initPwd (@RequestBody Map<String,String> map){
        Long studentId = Long.valueOf(map.get("studentId"));
        Student student = studentService.selectStudentById(studentId);
        studentService.initPwd(student);
        return ServerResponse.Success("密码初始化成功");
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.POST )
    public ServerResponse deleteAll (@RequestBody Map<String,String> map){
        String data = map.get("id");
        String params[] = data.split(",");
        for (int i=0;i<params.length;i++){
            if (params[i]==null){
                i++;
            }
            Student student = studentService.selectStudentById(Long.valueOf(params[i]));
            studentService.delete(student);
        }
        return ServerResponse.Success("删除成功");
    }


}
