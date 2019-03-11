package com.artmall.captcha;

import com.github.qcloudsms.SmsSingleSenderResult;

public interface CaptchaService {

    SmsSingleSenderResult sendCaptcha(String tel);

    Boolean captchaValidate(String tel,String code);
}
