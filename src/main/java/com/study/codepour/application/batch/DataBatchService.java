package com.study.codepour.application.batch;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.codepour.application.batch.data.DataObject;
import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;
import com.study.codepour.infrastructure.jpa.repository.SensorDataJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
@RequiredArgsConstructor
public class DataBatchService {
	
	private final SensorDataJpaRepository dataRepository;
	
//	@Scheduled(cron = "*/5 * * * * ?")
	@Scheduled(cron = "0 0 */6 * * ?")
	public void sensorDataScheduler() {
		log.info("-----Schedule Start-----");
		
		SensorDataEntity entity = new SensorDataEntity();
		String jsonString = null;
		String randomString = IntStream.range(0, 17)
                .mapToObj(i -> Character.toString((char)('A' + (int)(Math.random() * 26))))
                .collect(Collectors.joining());
		
		try {
			jsonString = new ObjectMapper().writeValueAsString(new DataObject());
		} catch (JsonProcessingException e) {
			log.error("Json 변환 실패");
			e.printStackTrace();
		}

		entity.setSensorName(Optional.ofNullable(randomString).orElse("ErrorTmpName"));
		entity.setSensorType("T");
		entity.setData(Optional.ofNullable(jsonString).orElse(null));
		entity.setCreatedAt(Timestamp.from(Instant.now()));
		entity.setUpdatedAt(Timestamp.from(Instant.now()));
		
		this.dataRepository.save(entity);
		
		log.info("-----Schedule End-----");
	}
}
