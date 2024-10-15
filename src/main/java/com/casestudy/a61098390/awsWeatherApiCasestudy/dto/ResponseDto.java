package com.casestudy.a61098390.awsWeatherApiCasestudy.dto;

public class ResponseDto extends WeatherDetailDto {
	
	public ResponseDto() {
		super();
	}
	
	public ResponseDto(int responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	private int responseCode;
	private String responseMessage;
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
