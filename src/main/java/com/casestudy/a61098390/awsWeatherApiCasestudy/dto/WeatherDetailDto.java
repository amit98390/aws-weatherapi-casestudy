package com.casestudy.a61098390.awsWeatherApiCasestudy.dto;

public class WeatherDetailDto {

	public WeatherDetailDto() {
		super();
	}

	public WeatherDetailDto(long createdTime, String cityName, String weatherDesc, double minTemp, double maxTemp,
			double temp, int humidity, double lon, double lat) {
		super();
		this.createdTime = createdTime;
		this.cityName = cityName;
		this.weatherDesc = weatherDesc;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.temp = temp;
		this.humidity = humidity;
		this.lon = lon;
		this.lat = lat;
	}

	private long createdTime;

	private String cityName;

	private String weatherDesc;

	private double minTemp;

	private double maxTemp;

	private double temp;

	private int humidity;

	private double lon;

	private double lat;

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
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

	@Override
	public String toString() {
		return "WeatherDetailDto [createdTime=" + createdTime + ", cityName=" + cityName + ", weatherDesc=" + weatherDesc
				+ ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", temp=" + temp + ", humidity=" + humidity
				+ ", lon=" + lon + ", lat=" + lat + "]";
	}
}
