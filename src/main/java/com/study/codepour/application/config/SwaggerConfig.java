package com.study.codepour.application.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Spring Security 미적용
 * 적용 시, 주석 해제
 */
@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI openAPI() {
//    	SecurityScheme securityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                .in(SecurityScheme.In.HEADER).name("Authorization");
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
    	
        return new OpenAPI()
        		.components(new Components())
//                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
//                .security(Arrays.asList(securityRequirement))
                .info(apiInfo());
    }
 
    private Info apiInfo() {
        return new Info()
                .title("Study API Document")
                .description("Study Swagger UI")
                .version("0.0.1");
    }
}
