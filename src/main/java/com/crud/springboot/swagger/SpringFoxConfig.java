package com.crud.springboot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.crud.springboot.controller"))
            .paths(PathSelectors.regex("/customer.*"))
            .build().apiInfo(apiEndPointsInfo());
    }
	
	private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("User Register REST API")
            .description("User Register REST API")
            .contact(new Contact("Lahiru Jayasundara", "www.test.net", "lnsjayasundara@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}
