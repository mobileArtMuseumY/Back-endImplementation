
$('#captcha_img').click(function (){

        $(this).attr('src', "../Kaptcha?" + Math.floor(Math.random() * 100));
})



