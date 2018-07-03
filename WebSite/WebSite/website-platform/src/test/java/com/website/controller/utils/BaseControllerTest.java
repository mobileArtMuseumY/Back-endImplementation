package com.website.controller.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: WebSite
 * @description: 测试类公用方法类
 * @author: smallsoup
 * @create: 2018-06-30 21:09
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring-*.xml"})
public class BaseControllerTest {

    /**
     * 加此测试方法防止报错
     * java.lang.Exception: No runnable methods
     *
     * Your unit test code is running JUnit4. At least one test method must have
     * the @test annotation. Otherwise this error occurs.
     */
    @Test
    public void testBaseBlank(){}

    protected void handleTest(MockHttpServletRequestBuilder request, MockMvc mockMvc){
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

    protected MockHttpServletRequestBuilder createRequest(String uri, String method) {
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
