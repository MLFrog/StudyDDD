package com.study.codepour.application.sensor;

import java.util.List;

import com.study.codepour.infrastructure.jpa.entity.SensorLogEntity;

public interface SensorLogService {
	List<SensorLogEntity> getAllSensorLogList(); 
}
