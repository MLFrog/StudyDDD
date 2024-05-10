package com.study.codepour.application.test;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AopTest {
	public void test() {
		log.info("[Service] Aop?");
	}
}
