package com.study.codepour.application.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
public class DataBatchService {
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void testScheduler() {
		log.info("스케쥴이 잘 돌고 있나요?");
	}
}
