package com.website.service.impl;

import com.website.dao.SkillMapper;
import com.website.service.ISkillService;
import com.website.service.dto.SkillDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: WebSite
 * @description: 能力处理业务类
 * @author: smallsoup
 * @create: 2018-06-30 16:48
 **/
@Service
public class SkillServiceImpl implements ISkillService {

    @Resource
    private SkillMapper skillMapper;

    @Override
    public void addSkill(SkillDto skillDto) throws Exception {

    }

    @Override
    public void updateSkill(SkillDto skillDto) throws Exception {

    }

    @Override
    public String querySkillNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public Long querySkillCountByIds(List<Long> ids) throws Exception {

        final Long count = skillMapper.selectCountByPrimaryKeys(ids);

        return count;
    }

    @Override
    public List<String> querySkillNameByIds(List<Long> ids) throws Exception {
        return null;
    }

    @Override
    public void deleteProject(Long id) throws Exception {

    }
}
