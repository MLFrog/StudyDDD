package com.study.codepour.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;

public interface SensorDataJpaRepository extends JpaRepository<SensorDataEntity, Long> {
	
}
