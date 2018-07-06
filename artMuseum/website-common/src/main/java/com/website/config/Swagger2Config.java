package com.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: swagger2 配置
 * @Author: hjy
 * @Date 2018/7/5 14:42
 * @Version 1.0
 **/

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestOpenApi() {

        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("open")
                .useDefaultResponseMessages(false)
                .apiInfo(openApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.website.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo openApiInfo() {
        return new ApiInfoBuilder()
                .title("小麻雀项目 APIS")
                .description("艺术博物馆[开放]api接口文档")
                .termsOfServiceUrl("https://github.com/mobileArtMuseumY")
                .version("1.0")
                .build();
    }
}
