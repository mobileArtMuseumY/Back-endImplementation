package com.website.controller;

import com.website.controller.utils.BaseControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebAppConfiguration
public class BusinessControllerTest extends BaseControllerTest {

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

        MockHttpServletRequestBuilder request = createRequest("/business/regist", "POST");

        request.param("account", "837448792@126.com");
//        request.param("account", "15222161830");

        handleTest(request, mockMvc);

    }

}
