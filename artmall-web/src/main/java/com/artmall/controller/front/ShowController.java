package com.artmall.controller.front;

import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;
import com.artmall.DTO.home.BrowseProjectDTO;
import com.artmall.DTO.home.StudentsShowDTO;
import com.artmall.DTO.home.WorksInfoDTO;
import com.artmall.DTO.home.WorksDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 展示管理
 *
 * @author
 * @create 2018-09-10 10:19
 **/
@RestController
@Api(description = "页面展示")
@RequestMapping("/show")
public class ShowController {
private final static Logger log = LoggerFactory.getLogger(ShowController.class);
    @Autowired
    ContentService contentService;


//    @RequestMapping(value = "/home")
//    @ApiOperation(value = "首页精品展示")
//    public ServerResponse<List<Works>> getHome (@RequestParam("page") int page,
//                                                @RequestParam("rows") int rows){
//
//        List<Works> list = contentService.getHome(page,rows);
//
//        return ServerResponse.Success("展示成功",list);
//    }

    @RequestMapping(value = "/project" ,method = RequestMethod.GET)
    @ApiOperation("浏览项目，method为排序根据，0为按照时间排序，1为按照follower的人数来排序")
    public ServerResponse<List<BrowseProjectDTO>> showProject (@RequestParam("method") int method,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("rows") int rows) throws Exception {
        List<BrowseProjectDTO> list = new ArrayList<>();
        if (method == 0) {
            try {
                list = contentService.getProjectByTime(page,rows);
            } catch (Exception e) {
                log.error("show project faile");
                return ServerResponse.Failure("failure");
            }
        }
        else if (method ==1) {
            try {
                list =contentService.getProjectByCount(page,rows);
            } catch (Exception e) {
                log.error("show project failure");
                return ServerResponse.Failure("failure");
            }
        }
        return ServerResponse.Success("展示成功",list);
    }
    @ApiOperation("发现作品，method为排序根据，0为按照时间排序，1为按照follower的人数来排序")
    @RequestMapping(value = "/works" ,method = RequestMethod.GET)
    public ServerResponse<List<WorksDTO>> showWorks (@RequestParam("method") int method,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("rows") int rows){
        List<WorksDTO> list = new ArrayList<>();
        if (method == 0)
            list = contentService.getWorksByType(page,rows,"`gmt_create` desc");
        else if (method ==1)
            list =contentService.getWorksByType(page,rows,"`follower_count` desc");
        return ServerResponse.Success( "展示成功",list);
    }

    @Autowired
    WorksService worksService;

    @Autowired
    UserService userService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    StudentService studentService;

    @Autowired
    BusinessService businessService;


    @ApiOperation("人才排行榜")
    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public ServerResponse<List<StudentsShowDTO>> showStudents (int page, int rows){
        List<StudentsShowDTO> list = contentService.getStudentsList(page,rows);
        return ServerResponse.Success("展示成功",list);

    }

    @ApiOperation("返回技能列表")
    @RequestMapping(value = "/skillList",method = RequestMethod.GET )
    public ServerResponse<List<Skill>> skillList(){
        List list = contentService.getSkillList();
        return ServerResponse.Success("List显示成功",list);
    }

    @ApiOperation("通过技能来筛选project")
    @RequestMapping(value = "/skillSelect",method = RequestMethod.GET)
    public ServerResponse<List<BrowseProjectDTO>> skillSelect (@RequestParam("skillId") int skillId,
                                                               @RequestParam("page") int page,
                                                               @RequestParam("rows") int rows){
        List<BrowseProjectDTO> list= null;
        try {
            list = contentService.getProjectBySkill(skillId,page,rows);
        } catch (Exception e) {
            return ServerResponse.Failure("error");
        }
        return ServerResponse.Success("显示成功",list);
    }







}
