package com.study.codepour.application.sensor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.codepour.application.sensor.SensorLogService;
import com.study.codepour.infrastructure.jpa.entity.SensorLogEntity;
import com.study.codepour.infrastructure.jpa.repository.SensorLogJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneralSensorLogService implements SensorLogService {
	
	private final SensorLogJpaRepository repository;

	@Override
	public List<SensorLogEntity> getAllSensorLogList() {
		return Optional.ofNullable(this.repository.findAll()).orElse(new ArrayList<SensorLogEntity>());
	}

}
