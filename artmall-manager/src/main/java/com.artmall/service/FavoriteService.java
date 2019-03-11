package com.artmall.service;

import com.artmall.DO.FavoriteProject;
import com.artmall.DO.FavoriteWorks;
import com.artmall.DO.User;

import java.util.List;

/**
 * 收藏管理
 *
 * @author mllove
 * @create 2018-10-22 22:15
 **/

public interface FavoriteService {

    /**
     * 获取收藏此作品的总人数
     * @param id
     * @return
     */
    Long getWorksCount(Long id);


    /**
     * 获取收藏此项目的总人数
     * @param id
     * @return
     */
    Long getProjectCount(Long id);

    /**
     * 用户收藏某作品
     * @param user
     * @param worksId
     * @return
     * @throws Exception
     */
    FavoriteWorks addWorksCollect(User user, Long worksId) throws Exception;

    /**
     * 用户收藏某项目
     * @param user
     * @param projectId
     * @return
     * @throws Exception
     */
    FavoriteProject addProjectCollect(User user, Long projectId) throws Exception;

    /**
     * 用户取消某作品的收藏
     * @param user
     * @param worksId
     */
    FavoriteWorks deleteWorksCollect(User user, Long worksId) throws Exception;

    /**
     * 用户取消某作品的收藏
     * @param user
     * @param projectId
     */
    FavoriteProject deleteProjectCollect(User user, Long projectId) throws Exception;

    List<FavoriteWorks> selectWorksByUserId(Long userId);

    List<FavoriteProject> selectProjectByUserId(Long userId);

    Byte isCollectWorks(Long id, Long worksId);

    Byte isCollectProject(Long userId, Long id);
}
