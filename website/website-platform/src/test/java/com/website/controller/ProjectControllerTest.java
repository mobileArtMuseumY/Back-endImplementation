package com.website.controller;


import com.website.controller.utils.BaseControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;


/**
 * @program: artMesuem
 * @description: ProjectController Tester.
 * @author: smallsoup
 * @create: 2018/6/24
 **/
@WebAppConfiguration
public class ProjectControllerTest extends BaseControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() throws FileNotFoundException {
//        Log4jConfigurer.initLogging("classpath:config/log4j.properties");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @After
    public void after() {
    }

    /**
     * Method: testAddProject(@RequestBody ProjectVo project)
     */
    @Test
    public void testAddProject() {
        MockHttpServletRequestBuilder request = createRequest("/project/add", "POST");

        String body = "{\"projectName\":\"设计logo\"," +
                "\"projectDescription\":\"lalalalla\"," +
                "\"tenderPeriod\":\"15\",\"budget\":\"1000\"," +
                "\"expectedTime\":\"11212\",\"attachmentList\":" +
                "\"[name1,name2]\",\"skillList\":\"[1,2,3]\"}";

        request.content(body);
        request.accept(MediaType.ALL);
        request.contentType(MediaType.APPLICATION_JSON_VALUE);

        handleTest(request, mockMvc);

    }

    /**
     * Method: testGetProject
     */
    @Test
    public void testGetProject() {
        MockHttpServletRequestBuilder request = createRequest("/project/getById", "GET");

        request.param("projectId", "153035104572630");

        handleTest(request, mockMvc);

    }


}


