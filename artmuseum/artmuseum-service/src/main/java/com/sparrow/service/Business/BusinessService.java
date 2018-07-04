package com.sparrow.service.Business;

import com.sparrow.common.ServerResponse;
import com.sparrow.common.exception.RequestParameterException;
import com.sparrow.dao.business.BusinessMapper;
import com.sparrow.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sparrow.common.RegexUtils.*;

@Service
public class BusinessService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BusinessMapper businessMapper ;

    public ServerResponse save(Business business){
        if (!isIDCard(business.getRepresentationIdcard())){
            throw new RequestParameterException("非法身份证号: "+business.getRepresentationIdcard());
        }
        if (!isEmail(business.getEmail())){
            throw new RequestParameterException("非法邮箱: "+business.getEmail());
        }
        if (!isTel(business.getTel())){
            throw new RequestParameterException("非法电话号码: "+business.getTel());
        }
        business.setGmtCreate(new Date());
        business.setIsVerified((byte) 0);
        String password = business.getPassword();
        business.setPassword(passwordEncoder.encode(password));
        businessMapper.insert(business);
        return new ServerResponse("200","ok",null);
    }



}
