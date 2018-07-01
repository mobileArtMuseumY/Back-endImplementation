package com.website.controller.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;
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

public final class MocUtils {

    public static void handleTest(MockHttpServletRequestBuilder request, MockMvc mockMvc){
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

    public static MockHttpServletRequestBuilder createRequest(String uri, String method) {
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
