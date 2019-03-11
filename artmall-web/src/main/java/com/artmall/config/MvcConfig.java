package com.artmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author mllove
 * @create 2018-10-23 20:20
 **/

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/image/**")
                .addResourceLocations("file:/root/artmall/image/");
// /root/artmall/image/*
    }

}
