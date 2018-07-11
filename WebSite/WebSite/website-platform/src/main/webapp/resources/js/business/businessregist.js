/*
*
*
*
* */

$(function () {

    $('#get_mobile_code').click(function () {

        var mobileAndEmailValidateUrl = "/business/sendverifycode";

        $.ajax({
            url: mobileAndEmailValidateUrl + "?telephone=" + $('#mobile').val(),
            type: "get",
            dataType: "JSON",
            // contentType: "application/json",
            // data:JSON.stringify(param),
            headers: {},
            success: function (data) {
                var result = JSON.parse(data);

                if (result.status == 0) {
                    $.toast("发送成功，请查收！");
                    $('#get_mobile_code').css('opacity', '0.2');
                }
                else {
                    $.toast("发送短信失败了：" + result.msg);
                }
            },
            error: function (retData) {
                $.toast("发送短信失败了：" + result.msg);
            }
        });

    })

    $('#mobile_code').blur(function () {

        var code = $('#mobile_code').val();

        if (!code) {
            $.toast("请输入手机验证码");
            return
        }

        var mobileAndEmailValidateUrl = "/business/verifycode";

        $.ajax({
            url: mobileAndEmailValidateUrl + "?mobileVerifyCodeActual=" + code,
            type: "post",
            dataType: "JSON",
            // contentType: "application/json",
            // data:JSON.stringify(param),
            headers: {},
            success: function (data) {
                var result = JSON.parse(data);

                if (result.status == 0) {
                    $.toast("短信验证成功！");
                }
                else {
                    $.toast("验证失败了：" + result.msg);
                }
            },
            error: function (retData) {
                $.toast("验证失败了：" + result.msg);
            }
        });

    })


    $('#business_reg_submit').click(function () {

        var businessRegistUrl = "/business/regist";

        var verifyCode = $('#j_captcha').val();

        if (!verifyCode) {
            $.toast("请输入验证码");
            return
        }

        // business_name = document.getElementById("business_name").value;
        var business_name = $('#business_name').val();

        var representation_name = document.getElementById("representation_name").value;
        var id_card = document.getElementById("id_card").value;
        var email = document.getElementById("email").value;
        var mobile = document.getElementById("mobile").value;
        var mobile_code = document.getElementById("mobile_code").value;
        var password = document.getElementById("password").value;
        var business_introduce = document.getElementById("business_introduce").value;


        var business = {};

        business.businessName = business_name;
        business.representationName = representation_name;
        business.representationIdcard = id_card;
        business.email = email;
        business.tel = mobile;
        business.mobileCode = mobile_code;
        business.hashedPwd = md5(password);
        business.introduction = business_introduce;

        var business_license = $("#business_license")[0].files[0];
        var formData = new FormData();
        formData.append('businessLicense', business_license);
        formData.append('businessStr', JSON.stringify(business));

        formData.append("verifyCodeActual", verifyCode);
        $.ajax({
            url: businessRegistUrl,
            type: 'POST',
            // contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                var result;
                if (typeof data === 'string') {
                    result = JSON.parse(data);
                    if (result.status == 0) {
                        $.toast('提交成功！');
                        window.location.href="../view/notifyactivate";
                    }
                    else {
                        $.toast("注册失败了：" + result.msg);
                    }
                } else {
                    if (data.status == 0) {
                        $.toast('提交成功！');
                        window.location.href="../view/notifyactivate";
                    }
                    else {
                        $.toast("注册失败了：" + data.msg);
                    }
                }

                $('#captcha_img').click();
            },
            error: function (retData) {
                $('#captcha_img').click();
                $.toast("注册失败了：" + result.msg);
            }
        });

    })
})
