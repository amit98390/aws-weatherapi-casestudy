package com.casestudy.a61098390.awsWeatherApiCasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.InputCityDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.ResponseDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.service.WeatherDetailService;

import jakarta.validation.Valid;

@RestController
public class WeatherDetailController {

	Logger logger = LoggerFactory.getLogger(WeatherDetailController.class);

	@Autowired
	private WeatherDetailService service;
	
	@PostMapping("/add")
	private ResponseDto addWeatherDetailPost(@RequestBody @Valid  InputCityDto dto) {
		logger.info("WeatherDetailController:: Adding weather details for city:: " + dto.getCity());
		ResponseDto res = service.addWeatherDetails(dto.getCity());
		if (res.getResponseMessage() == null) {
			res.setResponseMessage("Weather details fetched successfully.");
		}
		return res;
	}

	@PostMapping("/addWeather/{city}")
	private ResponseDto addWeatherDetail(@PathVariable  String city) {
		logger.info("WeatherDetailController :: QueryParam :: Adding weather details for city:: " + city);
		ResponseDto res = service.addWeatherDetails(city);
		if (res.getResponseMessage() == null) {
			res.setResponseMessage("Weather details fetched successfully.");
		}
		return res;
	}
}
