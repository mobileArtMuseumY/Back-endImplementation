package com.website.common;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebSite
 * @description: 发送短信工具类
 * @author: smallsoup
 * @create: 2018-07-01 12:10
 **/

public class SendMsgUtils {

    /**
     * 中国网建提供的SMS短信通，免费5条短信，3条彩信
     *
     * @param phones  手机号,多个用,分割
     * @param content 短信内容
     * @return String
     * @Discription:扩展说明
     */
    public static String sendMsgBySmsPlatform(String phones, String content) throws IOException {

        //用户名
        String Uid = "smallsoup";

        //接口安全秘钥
        String Key = "d41d8cd98f00b204e980";

        //手机号码，多个号码如13800000000,13800000001,13800000002
//        String smsMob = "15229061830";

        //短信接口URL提交地址
        String url = "http://utf8.sms.webchinese.cn";

        Map<String, String> params = new HashMap<>(4);

        params.put("Uid", Uid);
        params.put("Key", Key);
        params.put("smsMob", phones);
        params.put("smsText", content);

        return HttpRequestUtils.postRequest(url, params);
    }

    /**
     * 腾讯云短信,100条一个月
     * 方法说明
     *
     * @param phone
     * @return void
     * @Discription:扩展说明
     * @throws HTTPException  http status exception
     * @throws IOException    network problem
     */
    public static void sendMsgByTxPlatform(String phone, String code) throws Exception {

        // 短信应用SDK AppID
        // 1400开头
        int appId = 1400548;

        // 短信应用SDK AppKey
        String appKey = "b67d0bf7876c1d4qeqq59ca561953532";

        // 需要发送短信的手机号码
//        String[] phoneNumbers = {"15201011830"};

        // 短信模板ID，需要在短信应用中申请
        //NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        int templateId = 148464;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "我的小碗汤";

        SmsSingleSender sSender = new SmsSingleSender(appId, appKey);
        //第一个参数0表示普通短信,1表示营销短信
        SmsSingleSenderResult result = sSender.send(0, "86",
                phone,
                code + "为您的登录验证码，请于" + 10 + "分钟内填写。如非本人操作，请忽略本短信。", "", "");

        if (result.result != 0) {
            throw new Exception("send phone validateCode is error" + result.errMsg);
        }
    }

    /**
     * 中国网建接口错误码
     *
     * @Title: getErrorMsg
     * @Description: TODO(返回异常原因)
     * @param: @param errorCode
     */
    public static String getErrorMsgBySmsPlatform(int errorCode) {
        if (errorCode == -1) {
            return "没有该用户账户";
        } else if (errorCode == -2) {
            return "接口密钥不正确";
        } else if (errorCode == -3) {
            return "短信数量不足";
        } else if (errorCode == -4) {
            return "手机号格式不正确";
        } else if (errorCode == -21) {
            return "MD5接口密钥加密不正确";
        } else if (errorCode == -11) {
            return "该用户被禁用";
        } else if (errorCode == -14) {
            return "短信内容出现非法字符";
        } else if (errorCode == -41) {
            return "手机号码为空";
        } else if (errorCode == -42) {
            return "短信内容为空";
        } else if (errorCode == -51) {
            return "短信签名格式不正确";
        } else if (errorCode == -6) {
            return "IP限制";
        } else {
            return "未知错误码:" + errorCode;
        }
    }

}
