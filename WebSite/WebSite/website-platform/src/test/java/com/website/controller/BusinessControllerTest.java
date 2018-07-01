package com.website.controller;

import com.website.controller.utils.MocUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;

/**
 * @program: WebSite
 * @description: BusinessController测试类
 * @author: smallsoup
 * @create: 2018-06-30 20:36
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/applicationContext.xml",
        "classpath:config/spring-mvc.xml",
        "classpath:config/spring-mail.xml"})
@WebAppConfiguration
public class BusinessControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() throws FileNotFoundException {
        Log4jConfigurer.initLogging("classpath:config/log4j.properties");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @After
    public void after() {
    }

    /**
     * Method: testAddProject(@RequestBody ProjectVo project)
     */
    @Test
    public void testRegist() {

        MockHttpServletRequestBuilder request = MocUtils.createRequest("/business/regist", "POST");

//        request.param("account", "ll83744892@126.com");
        request.param("account", "15222161830");

        MocUtils.handleTest(request, mockMvc);

    }

}
