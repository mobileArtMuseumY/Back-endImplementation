package com.artmall.email;

import com.artmall.DO.Business;
import com.artmall.DO.OrderForm;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.DO.Student;
import com.artmall.response.ServerResponse;

import java.net.MalformedURLException;

/**
 * @author
 * @create 2018-08-22 15:15
 **/

public interface EmailService {
//    public ServerResponse sendSimpleMail(String to,String subject,String content);

    ServerResponse registerEmail(NewBusinessDTO business, String token) throws MalformedURLException;
    Boolean userValidate(Long id, String token);
    ServerResponse sendResetEmail(Business user, String code);
    ServerResponse sendResetEmail(Student user, String code);
    boolean codeVerify(Business business, String code);
    boolean codeVerify(Student student, String code);

    void payFinish(OrderForm order) throws Exception;
//     ServerResponse test(Business business,String token);
}
