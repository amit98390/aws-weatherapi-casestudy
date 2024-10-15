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
		logger.info("WeatherDetailService:: Getting weather details...");
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		List<WeatherDetailEntity> results = mapper.scan(WeatherDetailEntity.class, scan);
		return results.stream().map(this::toWeatherDto).collect(Collectors.toList());
	}

	public ResponseDto getWeatherByCityId(int id) {
		logger.info("WeatherDetailService:: Getting weather details...");
		WeatherDetailEntity item = mapper.load(WeatherDetailEntity.class, id);
		if (item != null) {
			logger.info("WeatherDetailService:: Getting weather details...");
			return toResponseDto(item);
		}
		logger.info("WeatherDetailService:: Getting weather details...");
		return null;
	}

	public ResponseDto addWeatherDetails(InputCityDto dto) {
		logger.info("WeatherDetailService:: Getting weather details...");
		ObjectMapper objMapper = new ObjectMapper();
		RestTemplate template = new RestTemplate();
		
		ResponseDto response = new ResponseDto();

		try {
			Object temp = template.getForObject(weatherApi + dto.getCity(), Object.class);
			WeatherObjDto weatherObj = objMapper.convertValue(temp, WeatherObjDto.class);


			if (weatherObj.getCod() == 200) {
				logger.info("WeatherDetailService:: Getting weather details...");
				response.setCityId(weatherObj.getId());
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
			} else {
				logger.info("WeatherDetailService:: Getting weather details...");
				response.setResponseMessage(weatherObj.getMessage());
				response.setResponseCode(weatherObj.getCod());
			}
		} catch(RestClientException | IllegalArgumentException e) {
			logger.error("WeatherDetailService:: Getting weather details..." + e);
		}
		return response;
	}

	public boolean deleteWeatherDetails(int id) {
		logger.info("WeatherDetailService:: Getting weather details...");
		WeatherDetailEntity item = mapper.load(WeatherDetailEntity.class, id);

		if (item != null) {
			logger.info("WeatherDetailService:: Weather deetails found for city id: " + id);
			mapper.delete(item);
			return true;
		}
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
