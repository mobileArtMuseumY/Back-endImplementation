package com.website.service.impl;

import com.website.common.IDUtils;
import com.website.common.RandomCodeUtils;
import com.website.common.RegexUtils;
import com.website.common.SendMsgUtils;
import com.website.dao.BusinessAttachmentMapper;
import com.website.dao.BusinessMapper;
import com.website.dao.EmailActivationMapper;
import com.website.po.Business;
import com.website.po.BusinessAttachment;
import com.website.po.EmailActivation;
import com.website.service.EmailService;
import com.website.service.IBusinessService;
import com.website.service.dto.BusinessDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebSite
 * @description: 企业业务逻辑处理
 * @author: smallsoup
 * @create: 2018-06-30 18:55
 **/
@Service
public class BusinessImpl implements IBusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessImpl.class);

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private EmailActivationMapper emailActivationMapper;

    @Resource
    private BusinessAttachmentMapper businessAttachmentMapper;

    @Resource
    private EmailService emailService;

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int regist(BusinessDto businessDto) throws Exception {

        //首先验证邮箱是否被注册,注册返回,未注册发邮件
        Map<String, String> params = new HashMap<>(1);
        //校验account,要符合邮箱或者手机号格式
        String email = businessDto.getEmail();

        if (RegexUtils.isEmail(email)) {
            params.put("email", email);
        } else {
            throw new Exception("不合法邮箱");
        }

        int count = emailActivationMapper.selectCountByParams(params);

        if (count != 0) {
            throw new Exception("该账号已经被注册");
        }

        //没有被注册,入emailActivation库,然后发邮件

        EmailActivation emailActivation = new EmailActivation();

        long id = IDUtils.generateId();
        emailActivation.setId(id);
        emailActivation.setGmtRegistered(new Date());
        emailActivation.setState(Byte.valueOf("0"));

        //现在的时间
        Calendar now = Calendar.getInstance();

        //往后推移一分钟
        now.add(Calendar.MINUTE, 1);

        //创建激活码
        String token = DigestUtils.md5Hex(email + id + now.getTimeInMillis());

        emailActivation.setToken(token);
        emailActivation.setTokenExpriedTime(now.getTime());
        emailActivation.setEmail(email);

        int countEmail = emailActivationMapper.insert(emailActivation);

        if (countEmail != 1) {
            throw new Exception("邮箱入库失败");
        }

        //发送邮件
        ///邮件的内容
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        StringBuffer sb = new StringBuffer("<div style=\"width:660px;overflow:hidden;border-bottom:1px solid #bdbdbe;\"><div style=\"height:52px;overflow:hidden;border:1px solid #464c51;background:#353b3f url(http://www.lofter.com/rsc/img/email/hdbg.png);\"><a href=\"http://www.lofter.com?mail=qbclickbynoticemail_20120626_01\" target=\"_blank\" style=\"display:block;width:144px;height:34px;margin:10px 0 0 20px;overflow:hidden;text-indent:-2000px;background:url(http://www.lofter.com/rsc/img/email/logo.png) no-repeat;\">Art Museum</a></div>" + "<div style=\"padding:24px 20px;\">您好，" + email + "<br/><br/>Art Museum是一款\"专注兴趣、分享创作\"的平台，旨在为\"热爱记录生活、追求时尚品质、崇尚自由空间\"的你，打造一个全新而定展示平台！<br/><br/>请点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        StringBuffer sb = new StringBuffer("请点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
//        sb.append("<a href=\"http://18.219.28.143:8080/view/business/emailconfirm?op=activate&id=");
        sb.append("<a href=\"http://localhost:8080/view/business/emailconfirm?op=activate&id=");
        sb.append(id);
        sb.append("&token=");
        sb.append(token + "\">");
        sb.append("点我哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦！</a>" + "<br/>如果以上链接无法点击，请把上面网页地址复制到浏览器地址栏中打开<br/><br/><br/>Art Museum，专注兴趣，分享创作<br/>" + sdf.format(new Date()) + "</div></div>");

        //发邮件
//        emailService.sendMailHtml(email, "Art Museum注册验证", sb.toString());

        BusinessAttachment businessAttachment = convertToBusinessAttachment(businessDto.getLicenseImg());
        Business business = businessDto.convertBusiness();

        businessAttachment.setBusinessId(business.getId());

        int businessAttachmentCount = businessAttachmentMapper.insert(businessAttachment);

        if (businessAttachmentCount != 1) {
            throw new Exception("businessAttachment 入库失败");
        }

        int businessCount = businessMapper.insert(business);

        if (businessCount != 1) {
            throw new Exception("business 入库失败");
        }

        return businessCount + businessAttachmentCount;
    }

    private BusinessAttachment convertToBusinessAttachment(File licenseImg) {

        BusinessAttachment businessAttachment = new BusinessAttachment();

        businessAttachment.setId(IDUtils.generateId());

        businessAttachment.setAttachmentName(licenseImg.getName());

        businessAttachment.setAttachmentPath(licenseImg.getAbsolutePath());

        businessAttachment.setAttachmentSize(FileUtils.sizeOf(licenseImg));

        businessAttachment.setGmtCreate(new Date());
        businessAttachment.setGmtModified(new Date());

        return businessAttachment;
    }

    /**
     * 发送手机验证码
     *
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public String sendVerifyCode(String account) throws Exception {

//        ImmutableMap<String, String> params = null;
        Map<String, String> params = new HashMap<>(1);

        //校验account,要符合手机号格式
        if (RegexUtils.isMobileExact(account)) {

            params.put("tel", account);

        } else {
            throw new Exception("不合法账号");
        }

        final int count = businessMapper.selectCountByParams(params);

        if (count != 0) {
            throw new Exception("该手机号已经被注册");
        }

        final String code = RandomCodeUtils.getSixValidationCode();

        SendMsgUtils.sendMsgByTxPlatform(account, code);
        return code;
    }


    @Override
    public void emailActivate(String id, String token) throws Exception {

        EmailActivation emailActivation = emailActivationMapper.selectByPrimaryKey(Long.valueOf(id));

        if (!StringUtils.equals(token, emailActivation.getToken())) {
            throw new Exception("token非法");
        }

        if (new Date().after(emailActivation.getTokenExpriedTime())) {
            throw new Exception("token失效，请重新注册");
        }

        //验证通过,更新激活时间
        emailActivation.setState(Byte.valueOf("1"));
        emailActivation.setGmtActivated(new Date());
        emailActivationMapper.updateByPrimaryKey(emailActivation);
    }
}
