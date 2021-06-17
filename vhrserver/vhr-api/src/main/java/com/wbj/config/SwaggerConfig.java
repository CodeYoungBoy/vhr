package com.wbj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * @author 王兵杰
 * @date 2021/5/23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfo("人事管理", "layui+springboot+mybatis-plus+jwt技术实现", "v 1.0", "王",
                DEFAULT_CONTACT, "wang", "2793007194@qq.com");
        docket.apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.wbj.controller"))
        .paths(PathSelectors.any())
        .build();
        return docket;
    }
}
