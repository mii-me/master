package com.art;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class Application {
	
	@Bean // 파일업로드를 위한 객체 생성
	public CommonsMultipartResolver multipartResolver() { 
		return new CommonsMultipartResolver();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}