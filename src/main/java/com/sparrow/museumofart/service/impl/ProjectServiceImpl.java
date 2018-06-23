package com.sparrow.museumofart.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import com.sparrow.museumofart.controller.vo.ProjectVo;
import com.sparrow.museumofart.dao.ProjectDao;
import com.sparrow.museumofart.dao.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectDao projectDao;
	
	@Override
	public void addProject(ProjectVo projectVo) {
		
		Project project = new Project();
		
		project.setBudget(new BigDecimal(1000));
		project.setBusiness_id(1000L);
		project.setExpected_time(new Timestamp(Long.valueOf(projectVo.getExpected_time())));
		project.setFinish_time(new Timestamp(9000));
		project.setGmt_create(new Timestamp(8000));
		project.setGmt_modified(new Timestamp(7000));
		
//		projectVo.getAttachment_list();
//		projectVo.getSkill_list();
		
		project.setBudget(new BigDecimal(projectVo.getBudget()));
		project.setProject_description(projectVo.getProject_description());
		project.setProject_name(projectVo.getProject_name());
		project.setProject_status(2);
		project.setTender_period(new Timestamp(Long.valueOf(projectVo.getTender_period())));
		
		projectDao.addProject(project);
	}

	@Override
	public ProjectVo updateProject(ProjectVo project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectVo queryProject(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectVo deleteProject(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
