package com.website.service;

import com.website.service.dto.SkillDto;

import java.util.List;

/**
 * @program: WebSite
 * @description: 能力Service
 * @author: smallsoup
 * @create: 2018-06-30 16:32
 **/

public interface ISkillService {

    public void addSkill(SkillDto skillDto) throws Exception;
    public void updateSkill(SkillDto skillDto) throws Exception;
    public String querySkillNameById(Long id) throws Exception;

    /**
     * 根据skill的id查询符合条件的count,count和list大小不同
     * 表示ids在skill表中不存在,即没有该能力
     * @param ids
     * @return
     * @throws Exception
     */
    public Long querySkillCountByIds(List<Long> ids) throws Exception;


    public List<String> querySkillNameByIds(List<Long> ids) throws Exception;



    public void deleteProject(Long id) throws Exception;
}
