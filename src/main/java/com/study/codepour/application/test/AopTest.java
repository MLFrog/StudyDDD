package com.study.codepour.application.test;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AopTest {
	
	public void test() {
		log.info("함수 호출");
	}
}
