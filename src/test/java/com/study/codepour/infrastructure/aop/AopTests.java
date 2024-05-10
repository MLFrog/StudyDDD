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
	private AopTest test;
	
	@Test
	public void 테스트() {
		this.aopTest();
		
		this.test.test();
	}
	
	private void aopTest() {
		log.info("함수가 불렸습니다.");
	}
}
