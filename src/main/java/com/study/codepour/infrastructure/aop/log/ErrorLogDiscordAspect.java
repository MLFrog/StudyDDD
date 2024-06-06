package com.study.codepour.infrastructure.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

/**
 * Filter에는 AOP를 적용할 수 없음
 * 이에 AOP를 적용할 패키지를 일일히 지정할 것
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorLogDiscordAspect {
	
	@Value("${infra.discord}")
	private String webHookUrl;
	
	@Pointcut("execution(* com.study.codepour.application.batch..*.*(..))"
			+ " || execution(* com.study.codepour.application.security..*.*(..))"
			+ " || execution(* com.study.codepour.application.log..*.*(..))"
			+ " || execution(* com.study.codepour.presentation..*.*(..))"
			+ " || execution(* com.study.codepour.domain..*.*(..))")
	public void packagePointcut() {
		
	}

    @AfterThrowing(pointcut = "packagePointcut()", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
    	AopMessage message = new AopMessage(String.format("[ETL Server Error] Exception in %s.%s() with cause = %s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage() != null ? ex.getMessage() : "NULL"));
    	
        try {
			this.sendDiscordAopMessage(message);
		} catch (Exception e) {
			log.error("메시지 발송 도중 에러 발생 :: " + e);
		}
    }
    
    // AOP 진입점 테스트용
//    @Before("execution(* com.study.codepour.application.test..*.*(..))" )
//    public void test(JoinPoint joinPoint) {
//    	AopMessage message = new AopMessage("[Service] AOP 작동 : " + joinPoint.getSignature().getName());
//    	
//    	try {
//            this.sendDiscordAopMessage(message);
//        } catch (Exception e) {
//        	log.error("메시지 발송 도중 에러 발생 :: " + e);
//        }
//    }
    
    /**
     * Discord 연동 Method
     * @param message
     */
    private void sendDiscordAopMessage(AopMessage message) {
    	HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; utf-8");
        HttpEntity<AopMessage> messageEntity = new HttpEntity<>(message, httpHeaders);

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
    }
    
    /**
	 * 데이터 클래스
	 */
	public static record AopMessage(String content) {
		
	}
    
}
