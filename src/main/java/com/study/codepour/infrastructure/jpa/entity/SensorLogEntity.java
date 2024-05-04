package com.study.codepour.infrastructure.jpa.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sensor_log")
public class SensorLogEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private Long logId;
	
	@Column(name = "sensor_id")
    private Long sensorId;
	
	@Column(name = "owner")
	private Long owner;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "createdat")
	private Timestamp createdAt;
	
	@Column(name = "updatedat")
	private Timestamp updatedAt;
}
