package com.study.codepour.presentation.v1.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.codepour.application.sensor.SensorDataService;
import com.study.codepour.application.sensor.SensorLogService;
import com.study.codepour.infrastructure.jpa.entity.SensorDataEntity;
import com.study.codepour.infrastructure.jpa.entity.SensorLogEntity;
import com.study.codepour.presentation.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sensor")
@RequiredArgsConstructor
public class SensorController {
	
	private final SensorDataService dataService;
	private final SensorLogService logService;
	
	@GetMapping("/get/log")
	public ApiResponse<List<SensorLogEntity>> getLogList() {
		ApiResponse<List<SensorLogEntity>> res = ApiResponse.of(new ArrayList<SensorLogEntity>());
		
		try {
			List<SensorLogEntity> data = this.logService.getAllSensorLogList();
			
			Assert.notNull(data, "데이터가 없습니다.");
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(data);
			
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			e.printStackTrace();
		}
		
		return res;
	}
	
	@GetMapping("/get/data")
	public ApiResponse<List<SensorDataEntity>> getDataList() {
		ApiResponse<List<SensorDataEntity>> res = ApiResponse.of(new ArrayList<SensorDataEntity>());
		
		try {
			List<SensorDataEntity> data = this.dataService.getAllSensorDataList();
			
			Assert.notNull(data, "데이터가 없습니다.");

			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(data);
			
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			e.printStackTrace();
		}
		
		return res;
	}
}
