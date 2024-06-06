package com.study.codepour.application.batch;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.codepour.application.batch.data.DataObject;
import com.study.codepour.application.exception.BatchException;
import com.study.codepour.infrastructure.jpa.entity.DataUserEntity;
import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;
import com.study.codepour.infrastructure.jpa.entity.SensorLogEntity;
import com.study.codepour.infrastructure.jpa.repository.SensorDataJpaRepository;
import com.study.codepour.infrastructure.jpa.repository.SensorLogJpaRepository;
import com.study.codepour.infrastructure.jpa.repository.UserDataJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
@RequiredArgsConstructor
public class DataBatchService {
	
	private final SensorDataJpaRepository dataRepository;
	private final UserDataJpaRepository userRepository;
	private final SensorLogJpaRepository logRepository;
	
	/**
	 * 주기적으로 로그 더미 로그 데이터 적재
	 */
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
	
	/**
	 * 로직
	 * 전체 센서 수와 전체 유저 수를 가져와 rand로 한 명을 고른 뒤,
	 * 해당 계정으로 더미 로그를 주기적으로 생성하는 로직
	 * @throws BatchException 
	 */
	public void sensorLogScheduler() throws BatchException {
		log.info("-----Schedule Start-----");
		
		SensorLogEntity entity = new SensorLogEntity();
		List<DataUserEntity> users = this.userRepository.findAll();
		List<SensorDataEntity> sensorData = this.dataRepository.findAll();
		
		Integer sensorDataCount = sensorData.size();
		Integer userCount = users.size();
		Random rand = new Random();
		UUID uuid = UUID.randomUUID();
		
		if (userCount < 1 || sensorDataCount < 1) {
			log.error("유저나 로그 데이터가 없습니다.");
			throw new BatchException("데이터가 없습니다.");
		}
		
		int randUser = rand.nextInt(userCount);
		int randData = rand.nextInt(sensorDataCount);
		
		entity.setSensorId(sensorDataCount > randData? Long.valueOf(randData) : 0);
		entity.setOwner(userCount > randUser? Long.valueOf(randData) : 0);
		entity.setHash(Optional.ofNullable(String.valueOf(uuid)).orElse("Can't Extract Hash Data"));
		
		this.logRepository.save(entity);
		
		log.info("-----Schedule End-----");
	}
}
