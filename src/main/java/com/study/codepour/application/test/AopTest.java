package com.study.codepour.application.test;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AopTest {
	
	public void test() throws Exception {
		log.info("함수 호출");
		
		throw new Exception("작동함?");
	}
}
