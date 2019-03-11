/*
package com.artmall.quartz;

import com.artmall.DO.Project;
import com.artmall.response.Const;
import com.artmall.service.ProjectService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

*/
/** 要执行的任务
 * @author mllove
 * @create 2018-11-30 15:58
 **//*

@Service
public class SampleJob implements Job {
    private final static Logger log = LoggerFactory.getLogger(SampleJob.class);


    @Autowired
    ProjectService projectService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//        Long projectId = Long.valueOf(jobDataMap.get("projectId").toString());
//        Project project = projectService.selectById(projectId);
//        projectService.setProjectStatus(project, Const.PROJECT_STATUS_OUT_OFF_DATA);
        System.out.println("hello world");
}
}
*/
