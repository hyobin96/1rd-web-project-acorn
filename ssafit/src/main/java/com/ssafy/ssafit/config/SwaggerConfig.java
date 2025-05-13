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
				.info(new Info().title("SSAFIT")
						.description("자신만의 커스텀 운동 영상")
						.version("v0.0.1")
						);
		
	}
}
