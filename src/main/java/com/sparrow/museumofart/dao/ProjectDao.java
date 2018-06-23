package com.sparrow.museumofart.dao;

import java.util.List;

import com.sparrow.museumofart.dao.entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao {
	
	public void addProject(Project project);
	
	public List<Project> batchAddProject(List<Project> projects);
	
	public Project updateProject(Project project);
	
	public Project queryProjectById(long id);
	
	public List<Project> queryProjectByIds(List<Long> ids);
	
	public long deleteProjectById(long id);
	
	public long deleteProjectByIds(List<Long> ids);
}
