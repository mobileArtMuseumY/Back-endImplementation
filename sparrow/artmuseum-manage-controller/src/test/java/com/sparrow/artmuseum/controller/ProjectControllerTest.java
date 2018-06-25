package com.sparrow.artmuseum.controller;

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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/*.xml"})
@WebAppConfiguration
public class ProjectControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    /**
     * @Title：testLogin
     * @Description: 测试添加项目
     */
    @Test
    public void testAddProject() {

        MockHttpServletRequestBuilder request = createRequest("/project/add", "POST");

        String body = "{\"projectName\":\"88\",\"projectDescription\":\"222\",\"tenderPeriod\":\"3344\",\"budget\":\"1000\",\"expectedTime\":\"11212\",\"attachmentList\":\"[name1,name2]\",\"skillList\":\"[java,go,nodejs,vue]\"}";

        request.content(body);
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
