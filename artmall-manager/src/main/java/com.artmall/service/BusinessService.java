package com.artmall.service;


import com.artmall.DO.Business;
import com.artmall.DO.BusinessAttachment;
import com.artmall.DTO.businessController.BusinessBaseDTO;
import com.artmall.DTO.businessController.BusinessShowDTO;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.response.ServerResponse;
import com.artmall.DTO.businessHome.BusinessHomeInfoDTO;

import java.util.List;

public interface BusinessService {
    Business selectBusinessByEmail(String email);

    Business selectByCode(String code);

    Business selectBusinessById(Long id);

    Business addUser(Business business) ;

    Business register(NewBusinessDTO business);


    ServerResponse resetPassword(Business business,String newPassword);

    ServerResponse emailSuccess(Business business);

    Business getBusiness();

    BusinessHomeInfoDTO showHomeInfo(Long id) throws Exception;

    void login(Business business);

    void setStatus(Business business,Byte status);

    List<BusinessShowDTO> show(int page, int rows);

    List<BusinessShowDTO> showVerify(int page, int rows);

    void delete(Business business);

    void update(BusinessBaseDTO updateDTO);

    BusinessAttachment selectBusinessAttachmentByBusinessId(Long businessId);

    void resetEmail(Business business, String email);

    void addFollowerCount(Long userId);

    void deleteFollowerCount(Long userId);

    void updateDescription(String description);

    void updatePhone(String tel);


}
