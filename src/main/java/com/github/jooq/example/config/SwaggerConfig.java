package com.github.jooq.example.config;

import static com.google.common.collect.Lists.newArrayList;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Docket newsApi() {
        Predicate<RequestHandler> swaggerSelector = RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class);
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(this.apiKey())
                //选择需要输出文档的接口
                .select().apis(RequestHandlerSelectors.basePackage("com.github.jooq.example.controller")).build()
                .apiInfo(apiInfo())
                .select()
                .apis(swaggerSelector)
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Api接口")
                .version("2.0.0")
                .build();
    }

    List apiKey() {
        return newArrayList(
            new ApiKey("Authorization-swagger", "Authorization", "header"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html","/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/","classpath:/META-INF/resources/webjars/*");
    }
}
