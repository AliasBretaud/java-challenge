package jp.co.axa.apidemo.web.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)      
	      .select()                                       
	      .apis(RequestHandlerSelectors.basePackage("jp.co.axa.apidemo.web.controllers"))
	      .paths(PathSelectors.any())
	      .build()
	      .apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "Employee API",
	            "API providing CRUD operatins on employees",
	            "v1",
	            null,
	            null,
	            null,
	            null,
	            Collections.emptyList()
	    );
	}
}
