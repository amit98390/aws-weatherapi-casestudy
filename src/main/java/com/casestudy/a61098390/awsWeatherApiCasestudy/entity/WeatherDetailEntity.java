package com.casestudy.a61098390.awsWeatherApiCasestudy.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "WeatherDetail")
public class WeatherDetailEntity {

	public WeatherDetailEntity() {
		super();
	}

	public WeatherDetailEntity(Integer cityId, String cityName, String weatherDesc, double minTemp, double maxTemp,
			double temp, int humidity, double lon, double lat) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.weatherDesc = weatherDesc;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.temp = temp;
		this.humidity = humidity;
		this.lon = lon;
		this.lat = lat;
	}


	@DynamoDBHashKey(attributeName = "cityId")
	private Integer cityId;
	
	@DynamoDBAttribute
	private String cityName;

	@DynamoDBAttribute
	private String weatherDesc;
	
	@DynamoDBAttribute
	private double minTemp;
	
	@DynamoDBAttribute
	private double maxTemp;

	@DynamoDBAttribute
	private double temp;

	@DynamoDBAttribute
	private int humidity;
	
	@DynamoDBAttribute
	private double lon;
	
	@DynamoDBAttribute
	private double lat;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getWeatherDesc() {
		return weatherDesc;
	}

	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}

	public double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
}
