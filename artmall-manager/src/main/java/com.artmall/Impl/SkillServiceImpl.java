package com.artmall.Impl;

import com.artmall.mapper.ProjectSkillMapper;
import com.artmall.mapper.SkillMapper;
import com.artmall.DO.ProjectSkill;
import com.artmall.DO.ProjectSkillExample;
import com.artmall.DO.Skill;
import com.artmall.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mllove
 * @create 2018-09-24 11:58
 **/
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    ProjectSkillMapper projectSkillMapper;
    @Override
    public List<Skill> addProjectSkillList(List<Skill> skillList,Long projectId) {
        ProjectSkill projectSkill = new ProjectSkill();
        for (Skill skill:skillList){
            projectSkill.setSkillId(skill.getId());
            projectSkill.setProjectId(projectId);
            projectSkillMapper.insert(projectSkill);
        }
        return skillList;
    }

    @Autowired
    SkillMapper skillMapper;
    @Override
    public List<Skill> getSkillListByProjectId(Long projectId) {
        return skillMapper.getProjectSkill(projectId);
    }

    @Override
    public List<Skill> getSkillListByStudentId(Long studentId) {
        return skillMapper.getStudentSkill(studentId);
    }

    private List<ProjectSkill> selectProjectSkillByProjectId(Long projectId) {
        ProjectSkillExample example = new ProjectSkillExample();
        ProjectSkillExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        List<ProjectSkill> projectSkillList = projectSkillMapper.selectByExample(example);
        return projectSkillList;
    }


}
