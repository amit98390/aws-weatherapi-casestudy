package com.casestudy.a61098390.awsWeatherApiCasestudy.controller;

import java.util.List;

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

	@Autowired
	private WeatherDetailService service;
	
	@GetMapping("/allWeather")
	private List<WeatherDetailDto> getWeatherDetail() {
		return service.getAllDetails();
	}
	
	@GetMapping("/weather/{id}")
	private ResponseDto getWeatherDetail(@PathVariable int id) {
		ResponseDto dto = service.getWeatherByCityId(id);
		if (dto != null) {
			dto.setResponseCode(200);
			dto.setResponseMessage("Weather details for city " + dto.getCityName() + " fetched successfully!!");
			return dto;
		} else {
			dto = new ResponseDto();
			dto.setResponseCode(404);
			dto.setResponseMessage("Weather details for city Id: " + dto.getCityId() + " not found!!");
			return dto;
		}
	}
	
	@PostMapping("/add")
	private ResponseDto addWeatherDetail(@RequestBody @Valid InputCityDto dto) {
		ResponseDto res = service.addWeatherDetails(dto);
		if (res.getResponseMessage() == null) {
			res.setResponseMessage("Weather details fetched successfully.");
		}
		return res;
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseDto deleteWatherDetails(@PathVariable int id) {
		ResponseDto dto = new ResponseDto();
		boolean res = service.deleteWeatherDetails(0);
		if (res) {
			dto.setResponseCode(200);
			dto.setResponseMessage("Weather details for city id: " + id + " deleted successfully!!");
			return dto;
		} else {
			dto.setResponseCode(404);
			dto.setResponseMessage("Weather details for city id: " + id + " not found!!");
			return dto;
		}
	}
}
