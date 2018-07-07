package com.sparrow.web;

import com.sparrow.web.business.BusinessController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BusinessControllerTest {

    private MockMvc mvc;

    @Autowired
    private BusinessController businessController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(businessController).build();
    }

    @Test
    public void registerTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/business/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("businessName","阿里")
                .param("representationName","马云")
                .param("representationIdcard","342222199209052814")
                .param("email","ali@aliyun.com")
                .param("password","123456")
                .param("tel"," 010-87655210 "))
                .andExpect(status().isOk());
    }


}
