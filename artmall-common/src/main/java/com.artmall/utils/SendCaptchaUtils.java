package com.artmall.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 腾讯云发送验证短信
 *
 * @author mllove
 * @create 2018-09-19 17:16
 **/

public class SendCaptchaUtils {


    public static SmsSingleSenderResult sendCaptcha(String tel){
        TengxunConfig tengxunConfig = new TengxunConfig();
        SmsSingleSenderResult result = null;
        try {
            ArrayList<String> params =new ArrayList<String>();
            params.add(Tools.getRandomNum()); //数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(tengxunConfig.getAppid(), tengxunConfig.getAppkey());
            result = ssender.sendWithParam("86", tel,
                    tengxunConfig.getTemplateId(), params, tengxunConfig.getSmsSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信

            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return result;
    }
}
