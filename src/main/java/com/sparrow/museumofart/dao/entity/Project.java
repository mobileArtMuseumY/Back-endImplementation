package com.sparrow.museumofart.dao.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Project {
	
	/**
	 * id
	 */
	private long id;
	
	/**
	 * ��ҵid
	 */
	private long business_id;
	
	/**
	 * ��ҵ����
	 */
	private String project_name;
	
	/**
	 *��Ŀ����
	 */
	private String project_description;
	
	/**
	 * ��Ŀ״̬
	 */
	private int project_status;
	
	/**
	 * Ԥ��
	 */
	private BigDecimal budget;
	
	/**
	 * Ͷ��ʱ��
	 */
	private Timestamp tender_period;
	
	/**
	 * Ԥ�����ʱ��
	 */
	private Timestamp expected_time;
	
	/**
	 * ʵ�����ʱ��
	 */
	private Timestamp finish_time;
	
	/**
	 * ����ʱ��
	 */
	private Timestamp gmt_create;
	
	/**
	 * �޸�ʱ��
	 */
	private Timestamp gmt_modified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public int getProject_status() {
		return project_status;
	}

	public void setProject_status(int project_status) {
		this.project_status = project_status;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Timestamp getTender_period() {
		return tender_period;
	}

	public void setTender_period(Timestamp tender_period) {
		this.tender_period = tender_period;
	}

	public Timestamp getExpected_time() {
		return expected_time;
	}

	public void setExpected_time(Timestamp expected_time) {
		this.expected_time = expected_time;
	}

	public Timestamp getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}

	public Timestamp getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Timestamp gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Timestamp getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Timestamp gmt_modified) {
		this.gmt_modified = gmt_modified;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [id=");
		builder.append(id);
		builder.append(", business_id=");
		builder.append(business_id);
		builder.append(", project_name=");
		builder.append(project_name);
		builder.append(", project_description=");
		builder.append(project_description);
		builder.append(", project_status=");
		builder.append(project_status);
		builder.append(", budget=");
		builder.append(budget);
		builder.append(", tender_period=");
		builder.append(tender_period);
		builder.append(", expected_time=");
		builder.append(expected_time);
		builder.append(", finish_time=");
		builder.append(finish_time);
		builder.append(", gmt_create=");
		builder.append(gmt_create);
		builder.append(", gmt_modified=");
		builder.append(gmt_modified);
		builder.append("]");
		return builder.toString();
	}
	
}
