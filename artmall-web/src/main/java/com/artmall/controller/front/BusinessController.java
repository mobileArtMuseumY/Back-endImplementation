package com.artmall.controller.front;


import com.artmall.DO.*;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.captcha.CaptchaService;
import com.artmall.config.RedisTokenManager;
import com.artmall.email.EmailService;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.CheckException;
import com.artmall.exception.NullException;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;

import com.artmall.utils.CheckUtils;
import com.artmall.utils.IDUtils;
import com.artmall.utils.JWTUtil;
import com.artmall.DTO.order.OrderDTO;
import com.artmall.DTO.businessHome.BusinessHomeInfoDTO;
import com.artmall.DTO.projectShow.ProjectInfoToBusinessDTO;
import com.artmall.utils.TengxunConfig;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mllove
 * @create 2018-08-20 10:08
 **/
@RestController
@RequestMapping("/business")
public class BusinessController {
    private final static Logger log = LoggerFactory.getLogger(BusinessController.class);


    @Autowired
    BusinessService businessService;
    @Autowired
    StorageService storageService;
    @Autowired
    private RedisTokenManager redisTokenManager;
    @Autowired
    EmailService emailService;
    @RequestMapping(value = "/register/form",method = RequestMethod.POST)
    @ApiOperation("注册")
    public ServerResponse resiger(@RequestBody NewBusinessDTO business) throws MalformedURLException {

        //格式验证
        if (!CheckUtils.isEmail(business.getEmail())){
            throw new CheckException(business.getEmail()+"格式有误");
        }
        if (!CheckUtils.isIDCard(business.getRepresentationIdcard())){
            throw new CheckException(business.getRepresentationIdcard()+"ID card格式有误");
        }

        String email = business.getEmail();
        if (businessService.selectBusinessByEmail(email)!=null){
            return ServerResponse.Failure("用户已注册");
        }else if(businessService.selectByCode(business.getCode())!=null){
            return ServerResponse.Failure("此信用代码重复");
        }else {
            business.setId(new IDUtils(3, 4).nextId());
            sendValidateEmail(business);
            return ServerResponse.Success("发送成功",business);
        }
    }

    @Autowired
    CaptchaService captchaService;
    /**
     * 发送验证短信
     * @param
     * @return
     */
    @ApiOperation("发送验证码")
    @RequestMapping(value = "/register/captcha",method = RequestMethod.POST)
    public SmsSingleSenderResult sendCaptcha  (@RequestBody Map<String,String> map){

        String tel = map.get("tel");
        if (!CheckUtils.isMobile(tel)){
            throw new CheckException(tel+"格式有误");
        }
        return captchaService.sendCaptcha(tel);


//        try {
//            String[] params = {"5678"};
//            TengxunConfig config = new TengxunConfig();
//
//            SmsSingleSender ssender = new SmsSingleSender(config.getAppid(), config.getAppkey());
//            SmsSingleSenderResult result = ssender.sendWithParam("86",config.getPhoneNumbers(),
//                    config.getTemplateId(), params, config.getSmsSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//            System.out.println(result);
//            return result;
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//return null;

    }

    @ApiOperation("验证手机和验证码是否匹配")
    @RequestMapping(value = "/register/captcha/varify",method = RequestMethod.POST)
    public ServerResponse varifyCaptcha  (@RequestBody Map<String,String> map){
        String tel = map.get("tel");
        String code = map.get("code");
        if (captchaService.captchaValidate(tel,code))
            return ServerResponse.Success("验证成功");
        else
            return ServerResponse.Failure("验证失败");
    }





    /**
     * 发送验证邮件
     * @param business
     */

        private ServerResponse sendValidateEmail(NewBusinessDTO business) throws MalformedURLException {
            //写入缓存
            String token =redisTokenManager.getTokenOfSignUp(business);
            return emailService.registerEmail(business,token);
        }
    /**
     * 核对用户点击的链接是否有效
     * @param token
     * @param id
     * @return
     */
    @Autowired
    UserService userService;
    @ApiOperation("验证链接")
    @RequestMapping(value = "/register/verify",method = RequestMethod.GET)
    public void verifyEmail (@RequestParam("token") String token,
                                  @RequestParam(value = "id") Long id,HttpServletResponse response) throws IOException {
        //如果验证成功要从redis中获取数据存入数据库
        if (emailService.userValidate(id,token)) {
            //读取缓存
            NewBusinessDTO newBusinessDTO =redisTokenManager.getBusiness(token);
            Business business = businessService.register(newBusinessDTO);
//            try {
//                BusinessAttachment businessAttachment = uploadService.addBusinessAttachmentInfoToDatabase(newBusinessDTO);
//            } catch (Exception e) {
//                return Const.IP_ADDRESS+"/#/signup/response";
//            }
                businessService.addUser(business);
            response.sendRedirect("http://localhost:8081/#/home");
        }
        else
            response.sendRedirect("http://localhost:8081/#/signup/response");

    }


    /**
     * 注册时上传工商管理证明
     * 删除
     * @param file
     * @param
     * @return
     */
    @Autowired
    UploadService uploadService;
    @ApiOperation("上传凭证")
    @RequestMapping(value = "/register/upload",method = RequestMethod.POST)
    public ServerResponse upload (@RequestParam("file")MultipartFile file){

        BusinessAttachment businessAttachment =uploadService.uploadBusinessAttachment(file);
//        redisTokenManager.setBusinessAttachmentRedis(businessAttachment);
        return ServerResponse.Success("上传成功",businessAttachment);
    }


    /**
     * 企业登录
     * @param
     * @param
     * @return
     */
    @ApiOperation("企业登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login (@RequestBody Map<String,String> map){
            String email = map.get("email");
            String password = map.get("password");

        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
        Business business = businessService.selectBusinessByEmail(email);
        if (business==null){
            throw new ArtmallException("没有此用户");
        }
        if (business.getIsVerified()!=Const.BUSINESS_PASS)
            return ServerResponse.Failure("审核还未通过");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            businessService.login(business);
        }catch (UnknownAccountException uae){
            return ServerResponse.Failure("用户不存在");
        }catch (IncorrectCredentialsException ice) {
            return ServerResponse.Failure("信息不匹配");
        }catch (AuthenticationException ae){
            return ServerResponse.Failure("密码有误");
        }

        return ServerResponse.Success(JWTUtil.sign(business.getId(),Const.LoginType.BUSINESS),business.getId());
    }
    /**
     * 忘记密码
     * @param
     * @return
     */
    @ApiOperation("忘记密码")
    @RequestMapping(value = "/password",method = RequestMethod.POST )
    public ServerResponse forget (@RequestBody Map<String,String> map){
        String email = map.get("email");
        Business business = businessService.selectBusinessByEmail(email);

        if(business!=null){
            return sendResetPasswordEmail(business);
        }else {
            return ServerResponse.Failure("此email没有注册");
        }

    }

    /**
     * 发送重置密码验证码的邮件
     * @param business
     * @return
     */
    private ServerResponse sendResetPasswordEmail(Business business){
        String code =redisTokenManager.getCodeOfResetPassword(business);
        return emailService.sendResetEmail(business,code);
    }


    /**
     * 重置密码
     * @return
     */
    @ApiOperation("重置密码")
    @RequestMapping(value = "/password/reset",method = RequestMethod.POST )
    public ServerResponse resetPassword (@RequestBody Map<String,String> map){
        String email = map.get("email");
        String code = map.get("code");
        String newPassword = map.get("newPassword");

        Business business = businessService.selectBusinessByEmail(email);
        if (emailService.codeVerify(business,code)){
            return businessService.resetPassword(business,newPassword);
        }else{
            return ServerResponse.Failure("验证码错误");
        }
    }

    @Autowired
    FollowerSerivce followerSerivce;

    @RequestMapping(value = "/home/info",method = RequestMethod.GET)
    public ServerResponse  showHomeInfo(@RequestParam("id") Long id) throws Exception {
        User user = userService.getUser();
        Byte isFollowed = followerSerivce.isFollowed(user.getUserId(),id);
        BusinessHomeInfoDTO businessHomeInfo = businessService.showHomeInfo(id);
        businessHomeInfo.setIsFollowed(isFollowed);
        return ServerResponse.Success("homeInfo展示成功",businessHomeInfo);
}





    @Autowired
    ProjectService projectService;
    @RequestMapping(value = "/home/projectShow",method = RequestMethod.GET)
    public ServerResponse  ProjectShow(@RequestParam("status")byte status,
                                       @RequestParam("id") Long id) throws Exception {
        Business business = businessService.selectBusinessById(id);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = projectService.selectProjectStatus(business.getId(),status);
        return ServerResponse.Success("项目展示成功", projectInfoToBusinessDTOS);
    }

    /**
     * 正在进行
     * @param id
     * @return
     */
    @RequestMapping(value = "/home/projectShow/bidding",method = RequestMethod.GET )
    public ServerResponse BiddingShow(@RequestParam("id") Long id){
        Business business = businessService.selectBusinessById(id);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = projectService.selectProjectBidding(business.getId());
        return ServerResponse.Success("正在进行的作品",projectInfoToBusinessDTOS);
    }
    @RequestMapping(value = "/home/projectShow/failed",method = RequestMethod.GET)
    public ServerResponse FailedShow(@RequestParam("id") Long id){
        Business business = businessService.selectBusinessById(id);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = projectService.selectProjectFailed(business.getId());
        return ServerResponse.Success("失败的项目",projectInfoToBusinessDTOS);

    }

    @Autowired
    OrderService orderService;
    @RequestMapping(value = "/home/orderShow",method = RequestMethod.GET )
    public ServerResponse  OrderShow(@RequestParam("id") Long id) throws Exception {
        Business business = businessService.selectBusinessById(id);
        List<OrderDTO> orderForms= orderService.selectOrderByBusinessId(id);
        return ServerResponse.Success("展示成功",orderForms);
    }


    @RequestMapping(value = "/update/email",method = RequestMethod.POST )
    public ServerResponse updateEmail (@RequestBody Map<String,String> map){
        String email = map.get("email");
        Business business= businessService.getBusiness();
        if (businessService.selectBusinessByEmail(email)!=null){
            throw new ArtmallException("此邮箱已经被注册了");
        }
        business.setEmail(email);
        return sendResetPasswordEmail(business);
    }

    @RequestMapping(value = "/email/verified",method = RequestMethod.POST )
    public ServerResponse emailVerified (@RequestBody Map<String,String> map){
        String email = map.get("email");
        String code = map.get("code");

        Business business = businessService.getBusiness();
        if (emailService.codeVerify(business,code)){
            businessService.resetEmail(business,email);
            return ServerResponse.Success("邮箱重置成功");
        }else{
            return ServerResponse.Failure("验证码错误");
        }
    }

    @RequestMapping(value = "/update/introduction",method = RequestMethod.POST )
    public ServerResponse updateDes (@RequestBody Map<String,String> map){
        String description = map.get("introduction");
        businessService.updateDescription(description);
        return ServerResponse.Success("描述更改成功");

    }

    @RequestMapping(value = "/update/tel",method = RequestMethod.POST )
    public ServerResponse updateTel (@RequestBody Map<String,String> map){
            String tel = map.get("tel");
            String code = map.get("code");
            if (captchaService.captchaValidate(tel,code)) {
                businessService.updatePhone(tel);
                return ServerResponse.Success("更改成功");
            }
            else
                return ServerResponse.Failure("更改失败");
        }
}

