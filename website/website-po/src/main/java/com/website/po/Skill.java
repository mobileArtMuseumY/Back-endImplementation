package com.website.po;

public class Skill {
    private Long id;

    private String skillName;

    public Skill(Long id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public Skill() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName == null ? null : skillName.trim();
    }
}