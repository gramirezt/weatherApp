package com.weather.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.weather.builder.WeatherResponseBuilder;
import com.weather.client.WeatherClient;
import com.weather.exception.WeatherServiceException;
import com.weather.model.WeatherAppResponse;
import com.weather.model.WeatherClientResponse;
import com.weather.service.WeatherService; 

@Service
public class WeatherServiceImpl implements WeatherService{
	private static final Logger LOGGER = LogManager.getLogger(WeatherServiceImpl.class);
	
	@Autowired
	private WeatherClient client;
	
	@Value("${weather.time.format}")
	private String timeFormat;
	
	@Value("${weather.decimal.format}")
	private String decimalFormat;
	
	@Value("${weather.date.format}")
	private String dateFormat;
	
	/**
	 * 
	 */
	public ResponseEntity<WeatherAppResponse> getWeatherByCity(String city) throws WeatherServiceException  {
		ResponseEntity<WeatherAppResponse>  response = null;
		
		WeatherClientResponse externalWeatherResponse=null;
		
		try { 
			externalWeatherResponse = client.callExternalWeatherService(city);	
			
			Optional<WeatherClientResponse> optionalExternalResponse = Optional.ofNullable(externalWeatherResponse);
			if (optionalExternalResponse.isPresent()) {
				WeatherAppResponse weatherApp = WeatherResponseBuilder.buildWeatherResponse(city, optionalExternalResponse.get(), dateFormat, decimalFormat);

				response= Optional.ofNullable(new ResponseEntity<>(weatherApp, HttpStatus.OK)).orElse(new ResponseEntity<>( HttpStatus.BAD_REQUEST) );
			}else {
				response= new ResponseEntity<>( HttpStatus.BAD_REQUEST);
			}			
		}  catch (HttpClientErrorException| WeatherServiceException e) { 
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			 LOGGER.error("[getWeatherByCity] An error has ocurred [FINEX] {}",e.getMessage());	 
			throw new WeatherServiceException(e.getMessage());
		}
		return response;
	}
}
