package com.sparrow.museumofart.dao;

import com.sparrow.museumofart.dao.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: artMesuem
 *
 * @description: 项目dao
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
@Repository
public interface ProjectDao {
	
	/** 
	* @Description:  添加项目
	* @Param:  Project 单一项目
	* @return:  void
	* @Author: smallsoup
	* @Date: 2018/6/24 
	*/ 
	void addProject(Project project);

	/**
	 * @Description: 批量添加项目
	 * @Param: projects 项目集合
	 * @return: projects
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	List<Project> batchAddProject(List<Project> projects);

	/**
	 * @Description: 更新项目信息
	 * @Param: project
	 * @return: project
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	Project updateProject(Project project);

	/**
	 * @Description: 根据id查项目
	 * @Param: id 项目id
	 * @return: project
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	Project queryProjectById(long id);

	/**
	 * @Description: 根据ids查项目集合
	 * @Param: id 项目ids
	 * @return: projects
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	List<Project> queryProjectByIds(List<Long> ids);

	/**
	 * @Description: 根据id删除项目
	 * @Param: id 项目id
	 * @return: long id
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	long deleteProjectById(long id);

	/**
	 * @Description: 根据id删除多个项目
	 * @Param: id 项目ids
	 * @return: long ids
	 * @Author: smallsoup
	 * @Date: 2018/6/24
	 */
	long deleteProjectByIds(List<Long> ids);
}
