package com.study.codepour.application.sensor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.codepour.application.sensor.SensorDataService;
import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;
import com.study.codepour.infrastructure.jpa.repository.SensorDataJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneralSensorDataService implements SensorDataService {

	private final SensorDataJpaRepository repository;
	
	@Override
	public List<SensorDataEntity> getAllSensorDataList() {
		return Optional.ofNullable(this.repository.findAll()).orElse(new ArrayList<SensorDataEntity>());
	}

}
