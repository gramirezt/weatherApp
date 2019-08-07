/**
 * 
 */
package com.weather.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.weather.exception.WeatherServiceException;
import com.weather.model.WeatherAppResponse;
import com.weather.service.WeatherService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WeatherControllerTest { 
	
	@InjectMocks
	WeatherController weatherController;

	@Mock
	private WeatherService weatherService;

	private final static String CITY = "London";
	private final static String DESCRIPTION = "scattered clouds";

	@Test
	public void homePageTest() throws Exception {

		ModelAndView modelAndView = weatherController.home();

		assertNotNull(modelAndView);
		assertEquals("main", modelAndView.getViewName());

	}
	@SuppressWarnings("unchecked")
	@Test
	public void successPageTest() throws Exception {

		ResponseEntity<WeatherAppResponse> response = new ResponseEntity<>(this.buildValidWeatherAppResponse(),
				HttpStatus.OK);
		Mockito.when(weatherService.getWeatherByCity(anyString())).thenReturn(response);

		ModelAndView modelAndView = weatherController.getWeatherByCity(CITY);
		
		assertNotNull(modelAndView);
		assertEquals("details", modelAndView.getViewName());
		Map<String, Object> map = modelAndView.getModel(); 
		assertNotNull(map.get("response"));
		ResponseEntity<WeatherAppResponse> result= (ResponseEntity<WeatherAppResponse>) map.get("response");
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(CITY, result.getBody().getCityName());
		assertEquals(DESCRIPTION, result.getBody().getDescription());	 
		
	}

	@Test
	public void requestToErrorPageTest() throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		Mockito.when(weatherService.getWeatherByCity(anyString())).thenThrow(WeatherServiceException.class);

		modelAndView = weatherController.getWeatherByCity(CITY);
		assertNotNull(modelAndView);
		assertEquals("error", modelAndView.getViewName());

	}

	private WeatherAppResponse buildValidWeatherAppResponse() {
		WeatherAppResponse response = new WeatherAppResponse();
		response.setCityName(CITY);
		response.setCurrentDate(new Date());
		response.setDescription(DESCRIPTION);
		response.setSunrise("4:36 AM");
		response.setSunset("7:18 PM");
		response.setTempC(57.94);
		response.setTempF(14.41);

		return response;
	}

}
