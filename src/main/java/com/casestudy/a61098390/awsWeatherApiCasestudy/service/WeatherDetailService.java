package com.casestudy.a61098390.awsWeatherApiCasestudy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.InputCityDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.ResponseDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.WeatherDetailDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.dto.WeatherObjDto;
import com.casestudy.a61098390.awsWeatherApiCasestudy.entity.WeatherDetailEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherDetailService {

	Logger logger = LoggerFactory.getLogger(WeatherDetailService.class);
	
	@Autowired
	private DynamoDBMapper mapper;

	@Value("${open.weather.api}")
	private String weatherApi;

	public List<WeatherDetailDto> getAllDetails() {
		logger.info("WeatherDetailService:: getAllDetails");
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		List<WeatherDetailEntity> results = mapper.scan(WeatherDetailEntity.class, scan);
		return results.stream().map(this::toWeatherDto).collect(Collectors.toList());
	}

	public ResponseDto getWeatherByCityId(int id) {
		logger.info("WeatherDetailService:: getWeatherByCityId for City Id:: " + id);
		WeatherDetailEntity item = mapper.load(WeatherDetailEntity.class, id);
		if (item != null) {
			logger.info("WeatherDetailService:: getWeatherByCityId:: Weather details found for city id: " + id);
			return toResponseDto(item);
		}
		logger.info("WeatherDetailService:: getWeatherByCityId:: Weather details found for city id: " + id);
		return null;
	}

	public ResponseDto addWeatherDetails(String city) {
		logger.info("WeatherDetailService:: Add:: Fetching weather details from Open Weather Map API...");
		ObjectMapper objMapper = new ObjectMapper();
		RestTemplate template = new RestTemplate();
		
		ResponseDto response = new ResponseDto();

		try {
			logger.info("WeatherDetailService:: Inside Try block for addWeatherDetails method...");
			Object temp = template.getForObject(weatherApi + city, Object.class);
			WeatherObjDto weatherObj = objMapper.convertValue(temp, WeatherObjDto.class);


			if (weatherObj.getCod() == 200) {
				logger.info("WeatherDetailService:: Saving weather details for entered City:: " + city);
				response.setCreatedTime(System.currentTimeMillis());
				response.setCityName(weatherObj.getName());
				response.setWeatherDesc(weatherObj.getWeather().get(0).getDescription());
				response.setLon(weatherObj.getCoord().getLon());
				response.setLat(weatherObj.getCoord().getLat());
				response.setMinTemp(weatherObj.getMain().getTemp_min());
				response.setMaxTemp(weatherObj.getMain().getTemp_max());
				response.setTemp(weatherObj.getMain().getTemp());
				response.setHumidity(weatherObj.getMain().getHumidity());
				response.setResponseCode(weatherObj.getCod());

				mapper.save(toEntity(response));
			}
		} catch(RestClientException | IllegalArgumentException e) {
			e.printStackTrace();
			response.setResponseMessage("Weather details for city Id: " + city + " not found!!");
			response.setResponseCode(404);
			logger.error("WeatherDetailService:: Error occured while fetching and saving weather details for city:: " + city + "\n Detailed Error:: \n" + e);
		}
		return response;
	}

	public boolean deleteWeatherDetails(int id) {
		logger.info("WeatherDetailService:: deleteWeatherDetails method...");
		WeatherDetailEntity item = mapper.load(WeatherDetailEntity.class, id);

		if (item != null) {
			logger.info("WeatherDetailService:: delete:: Weather details found for city id: " + id);
			mapper.delete(item);
			return true;
		}
		logger.info("WeatherDetailService:: delete:: Weather details found for city id: " + id);
		return false;
	}

	private ResponseDto toResponseDto(WeatherDetailEntity entity) {
		ResponseDto dto = new ResponseDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private WeatherDetailDto toWeatherDto(WeatherDetailEntity entity) {
		WeatherDetailDto dto = new WeatherDetailDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private WeatherDetailEntity toEntity(WeatherDetailDto dto) {
		WeatherDetailEntity entity = new WeatherDetailEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
}
