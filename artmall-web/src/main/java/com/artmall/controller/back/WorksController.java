package com.artmall.controller.back;

import com.artmall.DO.Student;
import com.artmall.DO.Works;
import com.artmall.response.ServerResponse;
import com.artmall.service.WorksService;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 作品管理
 *
 * @author mllove
 * @create 2018-11-14 11:26
 **/
@RestController
@RequestMapping("/worksController")
public class WorksController {

    @Autowired
    WorksService worksService;

    @RequestMapping(value = "/delete",method = RequestMethod.POST )
    public ServerResponse delete (@RequestBody Map<String,String> map){
        Long worksId = Long.valueOf(map.get("worksId"));
        Works works = worksService.selectWorksById(worksId);
        worksService.delete(works);
        return ServerResponse.Success("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST )
    public ServerResponse update (@RequestBody Works works){
        Works newWorks = worksService.update(works);
        return ServerResponse.Success("更改成功",newWorks);
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ServerResponse show(@RequestParam("page") int page,
                               @RequestParam("rows") int rows){
        List<Works> worksList = new ArrayList<>();
        worksList = worksService.show(page,rows);
        return ServerResponse.Success("展示成功",worksList);
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.POST )
    public ServerResponse deleteAll (@RequestBody Map<String,String> map){
        String data = map.get("id");
        String params[] = data.split(",");
        for (int i=0;i<params.length;i++){
            if (params[i]==null){
                i++;
            }
            Works works = worksService.selectWorksById(Long.valueOf(params[i]));
            worksService.delete(works);
        }
        return ServerResponse.Success("删除成功");
    }


}
