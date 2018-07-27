package com.sparrow.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    @Bean
    public Docket createRestOpenApi() {

        Predicate<RequestHandler> predicate = input -> {
            //只有添加了ApiOperation注解的method才在API中显示
            if (input.isAnnotatedWith(ApiOperation.class)) {
                return true;
            }
            return false;
        };
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.useDefaultResponseMessages(false)
                .apiInfo(openApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sparrow.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo openApiInfo() {
        return new ApiInfoBuilder()
                .title("艺术博物馆 RESTful APIs")
                .description("艺术博物馆api接口文档")
                .version("1.0")
                .build();
    }
     
}
