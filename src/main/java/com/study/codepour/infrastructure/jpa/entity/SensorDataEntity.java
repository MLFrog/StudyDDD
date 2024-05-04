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
@Table(name = "sensor_data")
public class SensorDataEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sensor_id")
	private Long sensorId;
	
	@Column(name = "sensor_name")
    private String sensorName;
	
	@Column(name = "sensor_type")
	private String sensorType;
	
	@Column(name = "parked_type")
	private String parkedType;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "createdat")
	private Timestamp createdAt;
	
	@Column(name = "updatedat")
	private Timestamp updatedAt;
}
