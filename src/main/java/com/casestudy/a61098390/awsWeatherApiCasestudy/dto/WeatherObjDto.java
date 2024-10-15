package com.casestudy.a61098390.awsWeatherApiCasestudy.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherObjDto {
	
	public WeatherObjDto() {
		super();
	}
	private CoordDto coord;
    private ArrayList<WeatherDto> weatherDto;
    private String base;
    private MainDto main;
    private int visibility;
    private int dt;
    private int timezone;
    private int id;
    private String name;
    private String message;
    private String cod;
	public CoordDto getCoord() {
		return coord;
	}
	public void setCoord(CoordDto coord) {
		this.coord = coord;
	}
	public ArrayList<WeatherDto> getWeather() {
		return weatherDto;
	}
	public void setWeather(ArrayList<WeatherDto> weatherDto) {
		this.weatherDto = weatherDto;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public MainDto getMain() {
		return main;
	}
	public void setMain(MainDto main) {
		this.main = main;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public int getTimezone() {
		return timezone;
	}
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
