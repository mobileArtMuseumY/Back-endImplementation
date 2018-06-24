package com.sparrow.museumofart.dao.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @program: artMesuem
 *
 * @description: 数据库对象
 *
 * @author: smallsoup
 *
 * @create: 2018/6/24
 **/
public class Project {
	
	/**
	 * id
	 */
	private long id;
	
	/**
	 * 企业id
	 */
	private long businessId;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 *项目描述
	 */
	private String projectDescription;
	
	/**
	 * 项目状态,0 1
	 */
	private int isVarified;
	
	/**
	 *预算
	 */
	private BigDecimal budget;
	
	/**
	 * 投标时间
	 */
	private int tenderPeriod;
	
	/**
	 * 预计完成时间
	 */
	private int expectedTime;
	
	/**
	 * 实际完成时间
	 */
	private Timestamp finishTime;
	
	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;
	
	/**
	 * 修改时间
	 */
	private Timestamp gmtModified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public int getIsVarified() {
		return isVarified;
	}

	public void setIsVarified(int isVarified) {
		this.isVarified = isVarified;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}


	public Timestamp getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public int getTenderPeriod() {
		return tenderPeriod;
	}

	public void setTenderPeriod(int tenderPeriod) {
		this.tenderPeriod = tenderPeriod;
	}

	public int getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(int expectedTime) {
		this.expectedTime = expectedTime;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Project{");
		sb.append("id=").append(id);
		sb.append(", businessId=").append(businessId);
		sb.append(", projectName='").append(projectName).append('\'');
		sb.append(", projectDescription='").append(projectDescription).append('\'');
		sb.append(", isVarified=").append(isVarified);
		sb.append(", budget=").append(budget);
		sb.append(", tenderPeriod=").append(tenderPeriod);
		sb.append(", expectedTime=").append(expectedTime);
		sb.append(", finishTime=").append(finishTime);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append('}');
		return sb.toString();
	}
}
