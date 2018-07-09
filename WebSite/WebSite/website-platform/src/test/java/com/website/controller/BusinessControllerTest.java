package com.website.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.website.common.SendMsgUtils;
import com.website.controller.utils.BaseControllerTest;
import com.website.controller.vo.BusinessVo;
import com.website.service.impl.EmailServiceImpl;
import mockit.Mock;
import mockit.MockUp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: WebSite
 * @description: BusinessController测试类
 * @author: smallsoup
 * @create: 2018-06-30 20:36
 **/
@WebAppConfiguration
public class BusinessControllerTest extends BaseControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
//        Log4jConfigurer.initLogging("classpath:config/log4j.properties");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象

        //1. mock对象
        MockUp<EmailServiceImpl> mockUp = new MockUp<EmailServiceImpl>() {

            @Mock
            public void sendMailSimple(String to, String subject, String content) {
                //blank exec
            }
        };

//        SendMsgUtils.sendMsgByTxPlatform(account, code);

        MockUp<SendMsgUtils> mockUp1 = new MockUp<SendMsgUtils>() {

            @Mock
            public void sendMsgByTxPlatform(String phone, String code) throws Exception {
                //mock sendMsgByTxPlatform什么都不干
            }
        };

    }

    @After
    public void after() {
    }

    /**
     * Method: testAddProject(@RequestBody ProjectVo project)
     */
    @Test
    public void testValidate() {

        MockHttpServletRequestBuilder request = createRequest("/business/sendverifycode", "GET");

        request.param("telephone", "15229061830");

        try {
            handleTest(request, mockMvc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRegist() {

        BusinessVo businessVo = new BusinessVo();

        businessVo.setBusinessName("121");
        businessVo.setRepresentationIdcard("612722190911131363");
        businessVo.setEmail("83744243@qq.com");
        businessVo.setTel("15236913136");

        businessVo.setIntroduction("21212121");

        businessVo.setRepresentationName("hello ");

        String businessStr = JSON.toJSONString(businessVo);

    /*    business.businessName = business_name;
        business.representationName = representation_name;
        business.representationIdcard = id_card;
        business.email = email;
        business.tel = mobile;
        business.mobileCode = mobile_code;
        business.hashedPwd = password;
        business.introduction = business_introduce;

        var business_license = $("#business_license")[0].files[0];
        var formData = new FormData();
        formData.append('businessLicense', business_license);
        formData.append('businessStr', JSON.stringify(business));*/

        MockHttpServletRequestBuilder request = createRequest("/business/regist", "POST");

        request.param("telephone", "15229061830");

        request.sessionAttr(Constants.KAPTCHA_SESSION_KEY, "1234");

        request.param("verifyCodeActual", "1234");

        request.param("businessStr", businessStr);
//        request.param("'businessLicense'", new File("D:\\tmp\\waterprint.jpg"));

//        businessStr


        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("businessLicense", "FileUploadTest.txt", "text/plain", "This is a Test".getBytes());


        try {

            MockMultipartHttpServletRequestBuilder merge = (MockMultipartHttpServletRequestBuilder)
                    MockMvcRequestBuilders.fileUpload("/business/regist").file(mockMultipartFile).merge(request);

            String responseString = mockMvc.perform(merge).
                    andDo(print()).
                    andExpect(status().isOk()).
                    andReturn().
                    getResponse().
                    getContentAsString();

            System.out.println("-----返回的json = " + responseString);

            JSONObject jsonObject = JSONObject.parseObject(responseString);

            Assert.assertEquals(jsonObject.get("status"), 0);

//            handleTest(request, mockMvc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
