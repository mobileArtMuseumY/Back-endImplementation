package com.artmall.Impl;

import com.artmall.DTO.NewWorksDto;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.FileException;
import com.artmall.exception.NullException;
import com.artmall.mapper.*;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.SkillService;
import com.artmall.service.StudentService;
import com.artmall.service.WorksService;
import com.artmall.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mllove
 * @create 2018-09-12 18:00
 **/
@Service
public class WorksServiceImpl implements WorksService {

    private final static Logger log = LoggerFactory.getLogger(WorksServiceImpl.class);

    @Autowired
    StudentService studentService;
    @Autowired
    WorksMapper worksMapper;
    @Autowired
    SkillService skillService;
    @Override
    public Works addWorksInfo(NewWorksDto works) {
        Long worksId = new IDUtils(1,9).nextId();
        works.setId(worksId);
        Works newWorks = newWorksToWorks(works);

        newWorks.setGmtCreate(new Date());
        newWorks.setFollowerCount(0);
        try {
            worksMapper.insert(newWorks);
            return newWorks;
        }catch (Exception e) {
            return null;
        }
    }



    private Works newWorksToWorks(NewWorksDto works) {
        Works newWorks = new Works();
        Student student = studentService.getStudent();

        if (student==null)
            return null;

        if (works.getWorksAttachmentList().size()!=0){
            WorksAttachment worksAttachment = works.getWorksAttachmentList().get(0);
            works.setId(worksAttachment.getWorksId());
        }

        newWorks.setId(works.getId());
        newWorks.setStudentId(student.getId());
        newWorks.setWorksName(works.getWorksName());
        newWorks.setWorksDescribe(works.getWorksDescribe());
        newWorks.setWorksStatus(Const.WORKS_STATUS_SHOW);
        newWorks.setPrice(works.getPrice());
        newWorks.setGmtModified(new Date());
        return newWorks;

    }

    @Autowired
    WorksAttachmentMapper worksAttachmentMapper;
    @Override
    public List<WorksAttachment> selectAttachmentByWorks(Long worksId)  {
        WorksAttachmentExample example = new WorksAttachmentExample();
        WorksAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(worksId);
        List<WorksAttachment> list = worksAttachmentMapper.selectByExample(example);

        return  list;
    }

    @Override
    public Works selectWorksById(Long worksId) {

        Works works =  worksMapper.selectByPrimaryKey(worksId);
        if (works==null){
            throw new NullException("不存在这个works");
        }
        return works;
    }

    @Override
    public Student selectStudentByWorksId(Long worksId) {
        Works works=selectWorksById(worksId);
        Student student = studentService.selectStudentById(works.getStudentId());
        return student;
    }

    @Override
    public List<Works> selectWorksByStudentId(Long studentId) {
        WorksExample example = new WorksExample();
        WorksExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<Works> worksList=worksMapper.selectByExample(example);
        return worksList;
    }

    /**
     * 修改作品的状态
     * @param worksStatus
     */
    @Override
    public Works setWorksStatus(Works works,byte worksStatus) {
        works.setWorksStatus(worksStatus);
        works.setGmtModified(new Date());
        worksMapper.updateByPrimaryKey(works);
        return works;
    }

    /**
     * 作品删除要删除bid，works_attachment,bid,complaint_attachment,
     * @param works
     */
    @Autowired
    BidMapper bidMapper;
    @Autowired
    WorksComplaintMapper worksComplaintMapper;
    @Autowired
    WorksCommentMapper worksCommentMapper;

    @Override
    @Transactional
    public void delete(Works works) {
        try {
            worksMapper.deleteByPrimaryKey(works.getId());
            deleteBid(works.getId());
            deleteWorksAllAttachemnt(works.getId());
            deleteWorksComplaint(works.getId());
            deleteCommentMapper(works.getId());
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("works删除失败");
        }

    }

    public void deleteWorksAllAttachemnt(Long id) {
        List<WorksAttachment> list = selectAttachmentByWorks(id);
        for (WorksAttachment worksAttachment:list){
            deleteWorksAttachemnt(worksAttachment);
        }

    }

    /**
     * 删除单个worksAttachment
     * @param worksAttachment
     */
    public void deleteWorksAttachemnt(WorksAttachment worksAttachment) {
        try {
            //删除存储的文件
            Files.deleteIfExists(Paths.get(worksAttachment.getAttachmentPath()));
            //删除数据库里关于此文件的信息
            worksAttachmentMapper.deleteByPrimaryKey(worksAttachment.getId());
        } catch (IOException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new FileException("delete works attachment failed，attachment id is："+worksAttachment.getId());
        }


    }

    @Override
    public WorksAttachment selectAttachmentById(Long attachmentId) {
        WorksAttachment worksAttachment=worksAttachmentMapper.selectByPrimaryKey(attachmentId);
        if (worksAttachment==null){
            throw new NullException("没有此附件");
        }
        return worksAttachment;
    }

    @Override
    public void updateCollectCount(Works works,int i) {
        if (Const.ADD == i){
            log.info("update works follerCount + 1");
            works.setFollowerCount(works.getFollowerCount()+1);
        }else if (Const.MINUS == i){
            log.info("update works follerCount - 1");
            works.setFollowerCount(works.getFollowerCount()-1);
        }


        worksMapper.updateByPrimaryKey(works);
    }

    @Override
    public void addWorksCollectCount(Long worksId) {
        Works works = selectWorksById(worksId);
        if (works==null){
            throw new NullException("不存在此works:"+works.getId());
        }
        works.setFollowerCount(works.getFollowerCount()+1);
        works.setGmtModified(new Date());
        worksMapper.updateByPrimaryKey(works);
    }

    @Override
    public void deleteWorksCollectCount(Long worksId) {
        Works works = selectWorksById(worksId);
        if (works==null){
            throw new NullException("不存在此works:"+works.getId());
        }
        works.setFollowerCount(works.getFollowerCount()-1);
        works.setGmtModified(new Date());
        worksMapper.updateByPrimaryKey(works);
    }

    @Override
    public NewWorksDto getInfo(Long worksId) {
        Works works =selectWorksById(worksId);
        if (works==null){
            throw new NullException("没有此works");
        }
        NewWorksDto worksDto = new NewWorksDto();
        worksDto.setId(worksId);
        worksDto.setPrice(works.getPrice());
        worksDto.setWorksName(works.getWorksName());
        worksDto.setWorksDescribe(works.getWorksDescribe());
        worksDto.setWorksAttachmentList(selectAttachmentByWorks(worksId));
        return worksDto;
    }


    private void deleteCommentMapper(Long id) {
        WorksCommentExample example = new WorksCommentExample();
        WorksCommentExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(id);
        worksCommentMapper.deleteByExample(example);
    }

    private void deleteWorksComplaint(Long id) {
        WorksComplaintExample example = new WorksComplaintExample();
        WorksComplaintExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(id);
        worksComplaintMapper.deleteByExample(example);
    }



    private void deleteBid(Long id) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(id);
        bidMapper.deleteByExample(example);
    }

    @Override
    public Works update(Works works) {
        worksMapper.updateByPrimaryKey(works);
        return works;
    }

    @Override
    public List<Works> show(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Works> list = new ArrayList<>();
        WorksExample example = new WorksExample();
        example.setOrderByClause("`gmt_create` desc");
        list = worksMapper.selectByExample(example);
        return list;
    }

    @Override
    public Works updateWorksInfo(NewWorksDto newWorksDto) {
        Works works = selectWorksById(newWorksDto.getId());
        works.setWorksName(newWorksDto.getWorksName());
        works.setWorksDescribe(newWorksDto.getWorksDescribe());
        works.setPrice(newWorksDto.getPrice());
        works.setGmtModified(new Date());
        return update(works);
    }


}
