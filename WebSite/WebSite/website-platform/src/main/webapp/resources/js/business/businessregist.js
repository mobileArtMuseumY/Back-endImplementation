/*
*
*
*
* */

$(function(){

    $('#get_mobile_code').click(function() {

        var businessRegistUrl = "/business/validate";

        $.ajax({
            url: businessRegistUrl + "?account=" + $('#mobile').val(),
            type: "post",
            dataType: "JSON",
            contentType: "application/json",
            // data:JSON.stringify(param),
            headers: {},
            success: function(data) {
                var result = JSON.parse(data);

                if(result.status == 0) {
                    alert(result.data.code);
                }
                else {
                    alert("失败了" + result.status);
                }
            },
            error: function(retData) {

            }
        });

    })

    $('#business_reg_submit').click(function () {

        var businessRegistUrl = "/business/regist";

        // business_name = document.getElementById("business_name").value;
        business_name = $('#business_name').val();

        representation_name = document.getElementById("representation_name").value;
        id_card = document.getElementById("id_card").value;
        email = document.getElementById("email").value;
        mobile = document.getElementById("mobile").value;
        mobile_code = document.getElementById("mobile_code").value;
        password = document.getElementById("password").value;
        business_introduce = document.getElementById("business_introduce").value;

        param={
            "businessName" : business_name,
            "representationName" : representation_name,
            "representationIdcard" : id_card,
            "email" : email,
            "tel" : mobile,
            "mobileCode" : mobile_code,
            "hashedPwd" : password,
            "introduction" : business_introduce
        };

        $.ajax({
            url: businessRegistUrl,
            type: "post",
            dataType: "JSON",
            contentType: "application/json",
            data:JSON.stringify(param),
            headers: {},
            success: function(data) {
                var result = JSON.parse(data);

                if(result.status == 0) {
                    alert(result.data.code);
                }
                else {
                    alert("失败了" + result.status);
                }
            },
            error: function(retData) {

            }
        });

    })
})
