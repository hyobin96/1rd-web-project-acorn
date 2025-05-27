package com.ssafy.ssafit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * swagger 설정파일
 */
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("acorn")
						.description("개인 맞춤 운동 습관 형성 서비스 개발")
						.version("v1.0.0")
						);
		
	}
}
