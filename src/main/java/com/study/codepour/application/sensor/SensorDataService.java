package com.study.codepour.application.sensor;

import java.util.List;

import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;

public interface SensorDataService {
	List<SensorDataEntity> getAllSensorDataList();
}
