package com.artmall.utils;

/**
 * @author mllove
 * @create 2018-09-19 17:24
 **/

public class TengxunConfig {
    // 短信应用SDK AppID
    int appid = 1400108353; // 1400开头

    // 短信应用SDK AppKey
    String appkey = "10be6e86762e0c53c00c2e0412d79b59";

    // 需要发送短信的手机号码
    String phoneNumbers ="15679141028";

    // 短信模板ID，需要在短信应用中申请
    int templateId = 0; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
//templateId7839对应的内容是"您的验证码是: {1}"

    String smsSign =null;

    public String getSmsSign() {
        return smsSign;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
}
