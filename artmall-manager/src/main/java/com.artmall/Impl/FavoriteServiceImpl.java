package com.artmall.Impl;

import com.artmall.exception.NullException;
import com.artmall.mapper.FavoriteProjectMapper;
import com.artmall.mapper.FavoriteWorksMapper;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.FavoriteService;
import com.artmall.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author mllove
 * @create 2018-10-22 22:16
 **/
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteWorksMapper favoriteWorksMapper;
    @Override
    public Long getWorksCount(Long id) {
        FavoriteWorksExample favoriteWorksExample = new FavoriteWorksExample();
        FavoriteWorksExample.Criteria criteria = favoriteWorksExample.createCriteria();
        criteria.andWorksIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Const.IS_COLLECT);
        Long count =  favoriteWorksMapper.countByExample(favoriteWorksExample);
        return count;
    }

    @Override
    public Long getProjectCount(Long id) {
        FavoriteProjectExample example = new FavoriteProjectExample();
        FavoriteProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        criteria.andIsDeletedEqualTo(Const.DELETE_COLLECT);
        Long count = favoriteProjectMapper.countByExample(example);
        return count;
    }

    /**
     * 用户收藏作品,1为收藏，0为取消收藏
     * @param user
     * @param worksId
     */
    @Autowired
    WorksService worksService;
    @Override
    public FavoriteWorks addWorksCollect(User user, Long worksId) throws Exception {
        FavoriteWorks favoriteWorks = getFavoriteWorks(user.getUserId(),worksId);
        Works works = worksService.selectWorksById(worksId);
        if (works==null){
            throw new NullException("没有此作品"+works.getId());
        }
        //喜欢此作品的人数加一,1表示加1，0表示减1
        worksService.updateCollectCount(works,1);
        //如果本来没有此收藏作品的记录，则插入数据库
        if(favoriteWorks==null){
            FavoriteWorks newFavoriteWorks = new FavoriteWorks();
            newFavoriteWorks.setWorksId(worksId);
            newFavoriteWorks.setUserId(user.getUserId());
            newFavoriteWorks.setIsDeleted(Const.IS_COLLECT);
            newFavoriteWorks.setGmtCreate(new Date());
            newFavoriteWorks.setGmtModified(new Date());
            favoriteWorksMapper.insert(newFavoriteWorks);
            return newFavoriteWorks;
        }else {
            //若曾经收藏过（即数据库里有此信息），就修改状态
            favoriteWorks = updateWorksStatus(favoriteWorks, Const.IS_COLLECT);
            return favoriteWorks;
        }
    }

    @Override
    public FavoriteWorks deleteWorksCollect(User user, Long worksId) throws Exception {
        FavoriteWorks favoriteWorks = getFavoriteWorks(user.getUserId(),worksId);
        favoriteWorks = updateWorksStatus(favoriteWorks, Const.DELETE_COLLECT);
        return favoriteWorks;
    }

    /**
     * 如果数据库中有此收藏信息，就更新状态
     * @param favoriteWorks
     * @return
     * @throws Exception
     */
    private FavoriteWorks updateWorksStatus(FavoriteWorks favoriteWorks, byte status) throws Exception {
            favoriteWorks.setIsDeleted(status);
            favoriteWorks.setGmtModified(new Date());
            favoriteWorksMapper.updateByPrimaryKey(favoriteWorks);
            return favoriteWorks;
    }

    private FavoriteWorks getFavoriteWorks(Long userId, Long worksId) {
        FavoriteWorksExample example = new FavoriteWorksExample();
        FavoriteWorksExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andWorksIdEqualTo(worksId);
        List<FavoriteWorks> favoriteWorksList = favoriteWorksMapper.selectByExample(example);
        if (favoriteWorksList.size() == 0){
            return null;
        }
        return favoriteWorksList.get(0);
    }

    /**
     * 收藏项目
     * @param user
     * @param projectId
     * @return
     */
    @Autowired
    FavoriteProjectMapper favoriteProjectMapper;
    @Override
    public FavoriteProject addProjectCollect(User user, Long projectId) throws Exception {
        FavoriteProject favoriteProject = getFavoriteProject(user.getUserId(),projectId);

        favoriteProject = updateProjectStatus(favoriteProject,(byte)1);

        if (favoriteProject==null) {
            FavoriteProject newFavoriteProject = new FavoriteProject();
            newFavoriteProject.setProjectId(projectId);
            newFavoriteProject.setUserId(user.getUserId());
            newFavoriteProject.setIsDeleted((byte) 1);
            newFavoriteProject.setGmtCreate(new Date());
            newFavoriteProject.setGmtModified(new Date());
            try {
                favoriteProjectMapper.insert(newFavoriteProject);
            } catch (Exception e) {
                throw new Exception("收藏项目插入数据库失败");
            }
            return newFavoriteProject;
        }
        return favoriteProject;
    }

    @Override
    public FavoriteProject deleteProjectCollect(User user, Long projectId){
        FavoriteProject favoriteProject = getFavoriteProject(user.getUserId(),projectId);
        if (favoriteProject == null){
            throw new NullException("没有此项目");
        }
        favoriteProject = updateProjectStatus(favoriteProject, Const.DELETE_COLLECT);
        return favoriteProject;
    }

    @Override
    public List<FavoriteProject> selectProjectByUserId(Long userId) {
        FavoriteProjectExample example = new FavoriteProjectExample();
        FavoriteProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsDeletedEqualTo(Const.IS_COLLECT);
        List<FavoriteProject> list = favoriteProjectMapper.selectByExample(example);
        return list;
    }

    @Override
    public Byte isCollectWorks(Long userId, Long worksId) {
        FavoriteWorksExample example = new FavoriteWorksExample();
        FavoriteWorksExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(worksId);
        criteria.andUserIdEqualTo(userId);
        List<FavoriteWorks> favoriteWorksList = favoriteWorksMapper.selectByExample(example);
        if (favoriteWorksList.size()!=0){
            if (favoriteWorksList.get(0).getIsDeleted().equals(Const.IS_COLLECT)){
                return Const.IS_COLLECT;
            }
        }
        return Const.DELETE_COLLECT;
    }

    @Override
    public Byte isCollectProject(Long userId, Long projectId) {
        FavoriteProjectExample example = new FavoriteProjectExample();
        FavoriteProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andProjectIdEqualTo(projectId);
        List<FavoriteProject> favoriteProjectList = favoriteProjectMapper.selectByExample(example);
        if (favoriteProjectList.size()!=0){
            if (favoriteProjectList.get(0).getIsDeleted().equals(Const.IS_COLLECT)){
                return Const.IS_COLLECT;
            }
        }
        return Const.DELETE_COLLECT;

    }

    @Override
    public List<FavoriteWorks> selectWorksByUserId(Long userId) {
         FavoriteWorksExample example = new FavoriteWorksExample();
         FavoriteWorksExample.Criteria criteria = example.createCriteria();
         criteria.andUserIdEqualTo(userId);
         criteria.andIsDeletedEqualTo(Const.IS_COLLECT);
         List<FavoriteWorks> list = favoriteWorksMapper.selectByExample(example);
         return list;
    }

    private FavoriteProject updateProjectStatus(FavoriteProject favoriteProject, byte isDelete) {

        if (favoriteProject!=null){
            favoriteProject.setIsDeleted(isDelete);
            favoriteProject.setGmtModified(new Date());
            favoriteProjectMapper.updateByPrimaryKey(favoriteProject);
            return favoriteProject;
        }
        return null;

    }

    private FavoriteProject getFavoriteProject(Long userId, Long projectId) {

        FavoriteProjectExample example = new FavoriteProjectExample();
        FavoriteProjectExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andUserIdEqualTo(userId);
        List<FavoriteProject> favoriteProjectList = favoriteProjectMapper.selectByExample(example);
        if (favoriteProjectList.size()==0){
            return null;
        }
        return favoriteProjectList.get(0);
    }




}
