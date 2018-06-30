package com.website.controller;


import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileNotFoundException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @program: artMesuem
 * @description: ProjectController Tester.
 * @author: smallsoup
 * @create: 2018/6/24
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/applicationContext.xml",
        "classpath:config/springmvc.xml"})
@WebAppConfiguration
public class ProjectControllerTest {
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

        handleTest(request);

    }

    /**
     * Method: testGetProject
     */
    @Test
    public void testGetProject() {
        MockHttpServletRequestBuilder request = createRequest("/project/getById", "GET");

        request.param("projectId", "153034483869711");

        handleTest(request);

    }

    private void handleTest(MockHttpServletRequestBuilder request){
        request.accept(MediaType.ALL);
        request.contentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            String responseString = mockMvc.perform(request).
                    andDo(print()).
                    andExpect(status().isOk()).
                    andReturn().
                    getResponse().
                    getContentAsString();
            System.out.println("-----返回的json = " + responseString);

            JSONObject jsonObject = JSONObject.parseObject(responseString);

            Assert.assertEquals(jsonObject.get("status"), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MockHttpServletRequestBuilder createRequest(String uri, String method) {
        MockHttpServletRequestBuilder builder = null;

        if ("GET".equalsIgnoreCase(method))
            builder = MockMvcRequestBuilders.get(uri);
        else if ("POST".equalsIgnoreCase(method))
            builder = MockMvcRequestBuilders.post(uri);
        else
            Assert.fail("Unsupported method!");

        return builder;

    }
}


