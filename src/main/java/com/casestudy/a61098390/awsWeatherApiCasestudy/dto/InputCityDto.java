package com.casestudy.a61098390.awsWeatherApiCasestudy.dto;

import jakarta.validation.constraints.NotNull;

public class InputCityDto {

	public InputCityDto() {
		super();
	}

	public InputCityDto(@NotNull String city) {
		super();
		this.city = city;
	}

	@NotNull
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
