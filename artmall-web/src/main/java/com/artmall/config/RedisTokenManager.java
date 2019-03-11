
package com.artmall.config;

import com.artmall.DO.Business;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.DO.BusinessAttachment;
import com.artmall.DO.Student;
import com.artmall.utils.Tools;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;




/**生成token（发送邮件标识用户身份的token）
 * @author
 * @create 2018-08-24 10:43
 **/

@Component
public class RedisTokenManager {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private String signUpPrefix="http://localhost:8080/";

    public String getTokenOfSignUp(NewBusinessDTO business){
        String token = UUID.randomUUID().toString();
        //将token和business以键值对的形式存放至redis
        redisTemplate.opsForValue().set(token,business);
        //设置在redis里面的过期时间
        redisTemplate.expire(token,30,TimeUnit.MINUTES);

        return token;
    }

    public String getCodeOfResetPassword(Business business){
        String code = Tools.getRandomNum();
        redisTemplate.opsForValue().set(code,business);
        redisTemplate.expire(code,30,TimeUnit.HOURS);
        return code;
    }

    public String getCodeOfResetPassword(Student student){
        String code = Tools.getRandomNum();
        redisTemplate.opsForValue().set(code,student);
        redisTemplate.expire(code,30,TimeUnit.HOURS);
        return code;
    }




    public NewBusinessDTO getBusiness(String token) {
        NewBusinessDTO business=(NewBusinessDTO) redisTemplate.opsForValue().get(token);
        return business;
    }

    public void setBusinessAttachmentRedis(BusinessAttachment businessAttachment) {
        redisTemplate.opsForValue().set(String.valueOf(businessAttachment.getBusinessId()),businessAttachment);
    }

    public BusinessAttachment getBusinessAttachment(Long id) {
        BusinessAttachment businessAttachment = (BusinessAttachment)redisTemplate.opsForValue().get(id.toString());
        return businessAttachment;
    }
}

