package test.com.website.controller; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 
* ProjectController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 27, 2018</pre> 
* @version 1.0 
*/ 
public class ProjectControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;
@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
}



@After
public void after() throws Exception { 
} 

/** 
* 
* Method: appendProject(@RequestBody ProjectVo project) 
* 
*/ 
@Test
public void testAddProject() throws Exception {
    MockHttpServletRequestBuilder request = createRequest("/project/add", "POST");

    String body = "{\"projectName\":\"设计logo\",\"projectDescription\":\"lalalalla\",\"tenderPeriod\":\"15\",\"budget\":\"1000\",\"expectedTime\":\"11212\",\"attachmentList\":\"[name1,name2]\",\"skillList\":\"[java,go,nodejs,vue]\"}";

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
//TODO: Test goes here... 
} 


