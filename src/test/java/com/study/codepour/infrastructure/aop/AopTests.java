package com.study.codepour.infrastructure.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.codepour.application.test.AopTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class AopTests {
	
	@Autowired
	private AopTest tester;
	
	@Test
	public void 테스트() {
		try {
			this.tester.test();
		} catch (Exception e) {
			log.info("예외 발생! 과연 AOP작동할까요?");
		}
	
	}
}
