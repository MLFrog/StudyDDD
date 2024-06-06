package com.study.codepour.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.codepour.infrastructure.jpa.entity.SensorLogEntity;

public interface SensorLogJpaRepository extends JpaRepository<SensorLogEntity, Long> {

}
