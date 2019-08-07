/**
 * 
 */
package com.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.weather.client.WeatherClient;
import com.weather.exception.WeatherServiceException;
import com.weather.model.Main;
import com.weather.model.Sys;
import com.weather.model.Weather;
import com.weather.model.WeatherAppResponse;
import com.weather.model.WeatherClientResponse;
import com.weather.service.impl.WeatherServiceImpl;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class WeatherServiceTest {
	
	private static final Logger logger = LogManager.getLogger(WeatherServiceTest.class);

	@InjectMocks
	private WeatherServiceImpl weatherService;

	@Mock
	private WeatherClient weatherClient;

	private final static String CITY = "London";

	@Value("${weather.time.format}")
	private String timeFormat;

	@Value("${weather.decimal.format}")
	private String decimalFormat;
	
	@Value("${weather.date.format}")
	private String deteFormat;

	@Before
	public void beforeTest() {

		ReflectionTestUtils.setField(weatherService, "timeFormat", timeFormat);
		ReflectionTestUtils.setField(weatherService, "decimalFormat", decimalFormat);
		ReflectionTestUtils.setField(weatherService, "dateFormat", deteFormat);
	}

	@Test
	public void getWeatherByCity200ValidResponseTest() throws WeatherServiceException{

		Mockito.when(weatherClient.callExternalWeatherService(anyString())).thenReturn(createResponseEntityOk());

		ResponseEntity<WeatherAppResponse> response = this.weatherService.getWeatherByCity(CITY);
		logger.debug(response.toString());

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(CITY, response.getBody().getCityName());

	}
	
	@Test
	public void getWeatherByCity400BadRequestTest() throws WeatherServiceException{

		Mockito.when(weatherClient.callExternalWeatherService(anyString())).thenReturn(null);

		ResponseEntity<WeatherAppResponse> response = this.weatherService.getWeatherByCity(CITY);
		logger.debug(response.toString());

		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test(expected=WeatherServiceException.class)
	public void getWeatherByCity500InternalServerErrorTest() throws WeatherServiceException{

		Mockito.when(weatherClient.callExternalWeatherService(anyString())).thenThrow(HttpClientErrorException.class);

		ResponseEntity<WeatherAppResponse> response = this.weatherService.getWeatherByCity(CITY);
		logger.debug(response.toString());

		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}



	public static WeatherClientResponse createResponseEntityOk() {

		WeatherClientResponse clientResponse = new WeatherClientResponse();
		clientResponse.setId("Id");
		Main main = new Main();
		main.setTemp(11.4);
		clientResponse.setMain(main);

		Sys sys = new Sys();
		sys.setSunset(12313L);
		sys.setSunrise(123221312L);
		clientResponse.setSys(sys);

		Weather weather = new Weather();
		weather.setDescription("scattered clouds");
		weather.setId("123");
		weather.setMain("London");

		List<Weather> list = new ArrayList<Weather>();
		list.add(weather);
		clientResponse.setWeather(list);

		return clientResponse;
	}
}
