package com.artmall.Impl;

import com.artmall.DO.*;
import com.artmall.DTO.businessController.BusinessBaseDTO;
import com.artmall.DTO.businessController.BusinessShowDTO;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.NullException;
import com.artmall.exception.FileException;
import com.artmall.mapper.BusinessAttachmentMapper;

import com.artmall.mapper.BusinessMapper;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import com.artmall.service.FollowerSerivce;
import com.artmall.service.ProjectService;
import com.artmall.service.UserService;
import com.artmall.utils.JWTUtil;
import com.artmall.utils.SaltUtil;
import com.artmall.DTO.businessHome.BusinessHomeInfoDTO;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
 * @create 2018-08-08 14:10
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessMapper businessMapper;

    @Override
    public Business selectBusinessByEmail(String email) {
        BusinessExample example = new BusinessExample();
        BusinessExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<Business> list = businessMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        else
            return null;


    }

    @Override
    public Business selectByCode(String code) {
        BusinessExample example = new BusinessExample();
        BusinessExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(code);
        List<Business> list = businessMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        else
            return null;

    }

    @Override
    public Business selectBusinessById(Long id) {
        Business business = businessMapper.selectByPrimaryKey(id);
        if (business==null){
            throw new NullException("此id没有对应的business");
        }
        return business;

    }


    public Business register(NewBusinessDTO business){
        Business newBusiness =  setInfoToBusiness(business);
        return newBusiness;
    }

    public Business setInfoToBusiness(NewBusinessDTO business) {
        Business business1 = new Business();
        Business newBusiness = setBaseInfoBusiness(business1,business);
        newBusiness.setSalt(SaltUtil.InitSalt());
        newBusiness.setHashedPwd(new SimpleHash("MD5", business.getPassword(), ByteSource.Util.bytes(newBusiness.getSalt()), 1024).toString());
        newBusiness.setGmtCreate(new Date());
        newBusiness.setIsVerified(Const.BUSINESS_EMAIL);
        newBusiness.setGmtModified(new Date());
        newBusiness.setFollowerCount(0);
        newBusiness.setCode(business.getCode());

        return newBusiness;
    }

    private Business setBaseInfoBusiness(Business newBusiness,BusinessBaseDTO business) {
        newBusiness.setId(business.getId());
        newBusiness.setBusinessName(business.getBusinessName());
        newBusiness.setRepresentationName(business.getRepresentationName());
        newBusiness.setRepresentationIdcard(business.getRepresentationIdcard());
        //要验证
        newBusiness.setEmail(business.getEmail());
        newBusiness.setTel(business.getTel());
        return newBusiness;
    }

    @Override
    @Transactional
    public Business addUser(Business business) {
        try {
            businessMapper.insert(business);
            //将用户角色写入数据库
            userService.addUserRole(business.getId(),Const.ROLE_BUSINESS);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("数据插入失败");
        }
        return business;
    }



    @Override
    public ServerResponse resetPassword(Business business,String newPassword) {
        business.setHashedPwd(new SimpleHash("MD5",newPassword,ByteSource.Util.bytes(business.getSalt()),1024).toString());
        try {
            businessMapper.updateByPrimaryKey(business);
        }catch (Exception e){
            return ServerResponse.Failure("修改密码");
        }

        return ServerResponse.Success("修改密码成功",business);
    }

    @Override
    public ServerResponse emailSuccess(Business business) {
        business.setIsVerified(Byte.valueOf("1"));
        try {
            businessMapper.updateByPrimaryKey(business);
        }catch (Exception e){
            return ServerResponse.Failure("邮箱验证成功");
        }

        return ServerResponse.Success("邮箱验证失败");
    }

    @Override
    public Business getBusiness() {
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        Long id = JWTUtil.getUserNo(token);
        if(id==null){
            throw new ArtmallException("此用户未登入，或者token无效");
        }
        Business business = businessMapper.selectByPrimaryKey(id);
        return business;
    }

    @Autowired
    ProjectService projectService;
    @Autowired
    FollowerSerivce followerSerivce;
    @Override
    public BusinessHomeInfoDTO showHomeInfo(Long id) throws Exception {
        Business business = selectBusinessById(id);
        BusinessHomeInfoDTO businessHomeInfoDTO = new BusinessHomeInfoDTO();
        businessHomeInfoDTO.setId(business.getId());
        businessHomeInfoDTO.setAvatar(business.getAvatar());
        businessHomeInfoDTO.setProjectCount(projectService.selectProjectByBusinessId(business.getId()).size());
        businessHomeInfoDTO.setFollowerCount(Math.toIntExact(followerSerivce.getFollowedCount(id)));
        //还未实现
        businessHomeInfoDTO.setFollowingCount(Math.toIntExact(followerSerivce.getFollowingCount(id)));
        businessHomeInfoDTO.setIntroduction(business.getIntroduction());
        businessHomeInfoDTO.setBusinessName(business.getBusinessName());
        businessHomeInfoDTO.setCode(business.getCode());
        return businessHomeInfoDTO;
    }

    /**
     * 登入时更改登入时间
     * @param business
     */
    @Override
    public void login(Business business) {
        business.setLoginTime(new Date());
        businessMapper.updateByPrimaryKey(business);

    }

    /**
     * 审核通过
     * @param business
     */
    @Override
    public void setStatus(Business business,Byte status) {
        business.setIsVerified(status);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);
    }

    @Override
    public List<BusinessShowDTO> show(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Business> businessList = businessMapper.selectAll();
        List<BusinessShowDTO> showList = setShowInfo(businessList);
        return showList;
    }

    private List<BusinessShowDTO> setShowInfo(List<Business> businessList) {
        List<BusinessShowDTO> showList = new ArrayList<>();
        for (Business business:businessList){
            BusinessShowDTO show = new BusinessShowDTO();
            show.setId(business.getId());
            show.setBusinessName(business.getBusinessName());
            show.setRepresentationName(business.getRepresentationName());
            show.setRepresentationIdcard(business.getRepresentationIdcard());
            show.setEmail(business.getEmail());
            show.setTel(business.getTel());
            show.setLoginTime(business.getLoginTime());
            show.setGmtCreate(business.getGmtCreate());
            show.setGmtModified(business.getGmtModified());
            show.setFollowerCount(business.getFollowerCount());
            show.setIsVerified(business.getIsVerified());
            show.setCode(business.getCode());
            showList.add(show);
        }
        return showList;
    }


    @Override
    public List<BusinessShowDTO> showVerify(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Business> businessList=selectByStatus(Const.BUSINESS_EMAIL);
        List<BusinessShowDTO> showList = setShowInfo(businessList);
        return showList;
    }

    private List<Business> selectByStatus(byte status) {
        BusinessExample businessExample = new BusinessExample();
        BusinessExample.Criteria criteria = businessExample.createCriteria();
        criteria.andIsVerifiedEqualTo(status);
        return businessMapper.selectByExample(businessExample);

    }

    /**
     * 删除学生，学生相关的作品的一切，user_role
     * @param student
     */
    @Autowired
    UserService userService;
    @Override
    @Transactional
    public void delete(Business business) {
        try{
            businessMapper.deleteByPrimaryKey(business.getId());

            userService.deleteUserRole(business.getId());
            businessAttchmentDelete(business.getId());
            List<Project> projectList = projectService.selectProjectByBusinessId(business.getId());
            for (Project project:projectList){
                projectService.delete(project);
            }

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("businessId:"+business.getId()+"删除失败");
        }
    }

    /**
     * 删除企业附件
     * @param id
     */
    @Transactional
    public void businessAttchmentDelete(Long id) {
        BusinessAttachment businessAttachment = selectBusinessAttachmentByBusinessId(id);
        if (businessAttachment!=null){
            try {
                Files.deleteIfExists(Paths.get(businessAttachment.getAttachmentPath()));
                businessAttachmentMapper.deleteByPrimaryKey(businessAttachment.getId());
            } catch (IOException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new FileException("business attachment delete failed");
            }
        }

    }

    @Override
    public void update(BusinessBaseDTO updateDTO) {
        Business business = selectBusinessById(updateDTO.getId());

        Business newBusiness = setBaseInfoBusiness(business,updateDTO);
        businessMapper.updateByPrimaryKey(newBusiness);
    }

    @Autowired
    BusinessAttachmentMapper businessAttachmentMapper;
    @Override
    public BusinessAttachment selectBusinessAttachmentByBusinessId(Long businessId) {
        BusinessAttachmentExample example = new BusinessAttachmentExample();
        BusinessAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<BusinessAttachment> list =businessAttachmentMapper.selectByExample(example);
        if (list.size()<1){
            return null;
        }
        return list.get(0);
    }

    @Override
    public void resetEmail(Business business, String email) {
        business.setEmail(email);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);

    }

    @Override
    public void addFollowerCount(Long userId) {
        Business business = selectBusinessById(userId);
        business.setFollowerCount(business.getFollowerCount()+1);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);
    }

    @Override
    public void deleteFollowerCount(Long userId) {
        Business business = selectBusinessById(userId);
        business.setFollowerCount(business.getFollowerCount()-1);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);

    }

    @Override
    public void updateDescription(String description) {
        Business business = getBusiness();
        business.setIntroduction(description);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);
    }

    @Override
    public void updatePhone(String tel) {
        Business business = getBusiness();
        business.setTel(tel);
        business.setGmtModified(new Date());
        businessMapper.updateByPrimaryKey(business);
    }


}
