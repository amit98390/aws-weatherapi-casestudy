package com.casestudy.a61098390.awsWeatherApiCasestudy.dto;

public class ResponseDto extends WeatherDetailDto {
	
	public ResponseDto() {
		super();
	}
	
	public ResponseDto(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	private String responseCode;
	private String responseMessage;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
