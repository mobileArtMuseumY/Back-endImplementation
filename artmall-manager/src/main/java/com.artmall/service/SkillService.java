package com.artmall.service;

import com.artmall.DO.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> addProjectSkillList(List<Skill> skillList,Long projectId);

    List<Skill> getSkillListByProjectId(Long projectId);

    List<Skill> getSkillListByStudentId(Long studentId);
}
