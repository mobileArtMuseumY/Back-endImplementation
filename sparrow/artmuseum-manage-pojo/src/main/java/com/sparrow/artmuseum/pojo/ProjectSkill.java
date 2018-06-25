package com.sparrow.artmuseum.pojo;

public class ProjectSkill {
    private Long projectId;

    private Long skillId;

    public ProjectSkill(Long projectId, Long skillId) {
        this.projectId = projectId;
        this.skillId = skillId;
    }

    public ProjectSkill() {
        super();
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}