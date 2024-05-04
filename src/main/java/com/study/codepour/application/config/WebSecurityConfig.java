package com.study.codepour.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration  
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	


//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("*")
//                .allowedHeaders("*");
//    }
//

	
    @Bean  
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  
    	
//    	http.headers(headers -> 
//    			headers.frameOptions(option -> option.disable()));
//    	
//    	http.cors(cors ->
//    			cors.disable());
    	
    	http.authorizeHttpRequests(authorizeHttpRequests -> //인가 설정
                authorizeHttpRequests
                        .requestMatchers("/**").permitAll() 
                        .anyRequest().authenticated()
    			)
	        .csrf(AbstractHttpConfigurer::disable);
        
        return http.build();  
    }  
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }
    
//	TODO : 준모 UserDetail 완료 되면 세팅 잡을 것
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean  
    public WebSecurityCustomizer webSecurityCustomizer() {  
        return (web) -> web.ignoring().requestMatchers(  
                "/swagger-ui/**",
                "/static/**"
		);  
    }


}