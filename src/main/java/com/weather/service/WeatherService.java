package com.weather.service;

import org.springframework.http.ResponseEntity;

import com.weather.exception.WeatherServiceException;
import com.weather.model.WeatherAppResponse; 


public interface WeatherService {

	
	public ResponseEntity<WeatherAppResponse> getWeatherByCity(String city)  throws WeatherServiceException;
	


}
