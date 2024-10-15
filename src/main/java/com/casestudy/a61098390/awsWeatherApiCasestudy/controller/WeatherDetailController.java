package com.casestudy.a61098390.awsWeatherApiCasestudy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.InputCityDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.ResponseDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.WeatherDetailDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.service.WeatherDetailService;

import jakarta.validation.Valid;

@RestController
public class WeatherDetailController {

	Logger logger = LoggerFactory.getLogger(WeatherDetailController.class);

	@Autowired
	private WeatherDetailService service;
	
	@GetMapping("/allWeather")
	private List<WeatherDetailDto> getWeatherDetail() {
		logger.info("WeatherDetailController:: Getting weather details...");
		return service.getAllDetails();
	}
	
	@GetMapping("/weather/{id}")
	private ResponseDto getWeatherDetail(@PathVariable int id) {
		logger.info("WeatherDetailController:: Getting weather detail for given city id:: " + id);
		ResponseDto dto = service.getWeatherByCityId(id);
		if (dto != null) {
			logger.info("Weather details for city " + dto.getCityName() + " fetched successfully!!");
			dto.setResponseCode("200");
			dto.setResponseMessage("Weather details for city " + dto.getCityName() + " fetched successfully!!");
			return dto;
		} else {
			logger.info("Weather details for city Id: " + dto.getCityId() + " not found!!");
			dto = new ResponseDto();
			dto.setResponseCode("404");
			dto.setResponseMessage("Weather details for city Id: " + dto.getCityId() + " not found!!");
			return dto;
		}
	}
	
	@PostMapping("/add")
	private ResponseDto addWeatherDetail(@RequestBody @Valid InputCityDto dto) {
		logger.info("WeatherDetailController:: Adding weather details for city:: " + dto.getCity());
		ResponseDto res = service.addWeatherDetails(dto);
		if (res.getResponseMessage() == null) {
			res.setResponseMessage("Weather details fetched successfully.");
		}
		return res;
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseDto deleteWatherDetails(@PathVariable int id) {
		logger.info("WeatherDetailController:: Delete weather details for city:: " + id);
		ResponseDto dto = new ResponseDto();
		boolean res = service.deleteWeatherDetails(0);
		if (res) {
			logger.info("WeatherDetailController:: Weather details for city id: " + id + " deleted successfully!!");
			dto.setResponseCode("200");
			dto.setResponseMessage("Weather details for city id: " + id + " deleted successfully!!");
			return dto;
		} else {
			logger.info("WeatherDetailController:: Weather details for city id: " + id + " not found!!");
			dto.setResponseCode("404");
			dto.setResponseMessage("Weather details for city id: " + id + " not found!!");
			return dto;
		}
	}
}
