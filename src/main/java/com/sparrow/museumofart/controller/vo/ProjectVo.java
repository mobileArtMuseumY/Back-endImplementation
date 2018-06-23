package com.sparrow.museumofart.controller.vo;

public class ProjectVo {

	private String project_name;
	
	private String project_description;
	
	private String tender_period;
	
	private String budget;
	
	private String expected_time;
	
	private String attachment_list;
	
	private String skill_list;

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

	public String getTender_period() {
		return tender_period;
	}

	public void setTender_period(String tender_period) {
		this.tender_period = tender_period;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getExpected_time() {
		return expected_time;
	}

	public void setExpected_time(String expected_time) {
		this.expected_time = expected_time;
	}

	public String getAttachment_list() {
		return attachment_list;
	}

	public void setAttachment_list(String attachment_list) {
		this.attachment_list = attachment_list;
	}

	public String getSkill_list() {
		return skill_list;
	}

	public void setSkill_list(String skill_list) {
		this.skill_list = skill_list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectVo [project_name=");
		builder.append(project_name);
		builder.append(", project_description=");
		builder.append(project_description);
		builder.append(", tender_period=");
		builder.append(tender_period);
		builder.append(", budget=");
		builder.append(budget);
		builder.append(", expected_time=");
		builder.append(expected_time);
		builder.append(", attachment_list=");
		builder.append(attachment_list);
		builder.append(", skill_list=");
		builder.append(skill_list);
		builder.append("]");
		return builder.toString();
	}
	
}
