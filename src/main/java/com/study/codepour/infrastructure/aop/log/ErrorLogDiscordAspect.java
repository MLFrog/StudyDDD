package com.study.codepour.infrastructure.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorLogDiscordAspect {
	
	@Value("${infra.discord}")
	private String webHookUrl;

    @AfterThrowing(pointcut = "execution(* com.study.codepour.application.test..*.*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        log.info(String.format("[ETL Server Error] Exception in %s.%s() with cause = %s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getCause() != null ? ex.getCause() : "NULL"));
    }
    
    @Before("execution(* com.study.codepour.application.test..*.*(..))" )
    public void test(JoinPoint joinPoint) {
    	log.info("???? 작동좀");
    	
    	try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json; utf-8");
            HttpEntity<AopMessage> messageEntity = new HttpEntity<>(new AopMessage("[Service] AOP 작동 : " + joinPoint.getSignature().getName()), httpHeaders);

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> response = template.exchange(
            		webHookUrl,
                    HttpMethod.POST,
                    messageEntity,
                    String.class
            );

            if(response.getStatusCode().value() != HttpStatus.NO_CONTENT.value()){
                log.error("메시지 전송 이후 에러 발생");
            }

        } catch (Exception e) {
            log.error("에러 발생 :: " + e);
        }
    }
    
    /**
	 * 데이터 클래스
	 */
	public static record AopMessage(String content) {
		
	}
    
}
