package com.zensar;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StockDemoAppApplication {

    public static void main(String[] args) {
	SpringApplication.run(StockDemoAppApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
	return new ModelMapper();
    }

    @Bean
    public Docket getCuttomizedDocket() {
	return new Docket(DocumentationType.SWAGGER_2).select()

		.paths(PathSelectors.any()).build().apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
// TODO Auto-generated method stub
	ApiInfo apiInfo = new ApiInfo("Swagger Restful Api Documentation",
		"This page given REST API Documentation for Stock", "2.5", "My Teams of Service",
		new Contact("Raja", "http://raja.com", "raja@gmail.com"), "GPL", "http://gpl.org",
		new ArrayList<VendorExtension>());
	return apiInfo;
    }
}