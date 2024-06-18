package com.study.codepour.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.codepour.infrastructure.jwt.JwtTokenFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration  
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final JwtTokenFilter jwtAuthenticationFilter;
	private final UserDetailsService userDetailsService;

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
                		.requestMatchers("/swagger-ui/**", 
                						 "/v3/api-docs/**", 
                						 "/api/v1/auth/**")
                		.permitAll()
                        .anyRequest().authenticated()
    			)
    		.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		)
    		.authenticationProvider(this.authenticationProvider())
        	.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        	.exceptionHandling(exception ->  
            exception.authenticationEntryPoint(
            		(request, response, authException)
                    -> response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        authException.getLocalizedMessage()
                      )))
	      	.csrf(AbstractHttpConfigurer::disable);
        
        return http.build();  
    }  
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }
    
    // TODO : 추후 제거될 항목
    @Deprecated
    @Bean
    public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
    }    
    
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean  
    public WebSecurityCustomizer webSecurityCustomizer() {  
        return (web) -> web.ignoring().requestMatchers(  
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/static/**"
		);  
    }


}