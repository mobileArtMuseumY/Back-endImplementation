package com.website.controller;

import com.website.controller.utils.BaseControllerTest;
import com.website.service.impl.EmailServiceImpl;
import mockit.Mock;
import mockit.MockUp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    }

    @After
    public void after() {
    }

    /**
     * Method: testAddProject(@RequestBody ProjectVo project)
     */
    @Test
    public void testValidate() {

        MockHttpServletRequestBuilder request = createRequest("/business/validate", "POST");

        request.param("account", "837448792@126.com");

        try {
            handleTest(request, mockMvc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
