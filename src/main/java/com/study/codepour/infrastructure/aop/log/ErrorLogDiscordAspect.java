package com.study.codepour.infrastructure.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.study.codepour.infrastructure.watchtower.DiscordMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorLogDiscordAspect {
	
	private final DiscordMessageService discordService;

    @AfterThrowing(pointcut = "within(com.study.codepour.*)", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        this.discordService.sendMsg(String.format("[ETL Server Error] Exception in %s.%s() with cause = %s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getCause() != null ? ex.getCause() : "NULL"));
    }
    
    @After("within(com.study.codepour.*)")
    public void test(JoinPoint joinPoint) {
    	log.info("AOP 작동?");
    }
}
