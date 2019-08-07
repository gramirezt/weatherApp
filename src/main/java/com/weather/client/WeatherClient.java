package com.weather.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.model.WeatherClientResponse;

@Component
public class WeatherClient {
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${weather.url.base}")
	private String baseUrl;
	
	@Value("${weather.url.appId}")
	private String appId;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	/**
	 * @param City
	 * @return WeatherClientResponse response from callExternalWeatherService
	 */
	public WeatherClientResponse callExternalWeatherService(String city) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<WeatherClientResponse> entity = new HttpEntity<>(headers);

		StringBuilder endPoint = new StringBuilder(baseUrl).append(city).append(appId);

		ResponseEntity<WeatherClientResponse> result = restTemplate.exchange(endPoint.toString(), HttpMethod.GET,
				entity, WeatherClientResponse.class); 

		return Optional.ofNullable(result.getBody()).orElse(null);

	}
}
