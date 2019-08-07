package com.weather.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.weather.model.Main;
import com.weather.model.Sys;
import com.weather.model.Weather;
import com.weather.model.WeatherClientResponse;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class WeatherClientTest {
	private static final Logger logger = LogManager.getLogger(WeatherClientTest.class); 
	 
	@InjectMocks
	private WeatherClient client;
	
	@Mock
	private RestTemplate restTemplateMock;
	
	private final static String CITY="London";
	
	@Value("${weather.url.base}")
	private String baseUrl;

	@Value("${weather.url.appId}")
	private String appId;
	
	@Before
	public void configureSystemUnderTest() {
		ReflectionTestUtils.setField(client, "baseUrl", baseUrl);
		ReflectionTestUtils.setField(client, "appId", appId);
	}
	
	@Test
	public void callExternalServiceSuccesTest() throws Exception {
			
		ResponseEntity<WeatherClientResponse> responseEntity = createResponseEntityOk();

		when(restTemplateMock.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<WeatherClientResponse>>any() 
              )
			).thenReturn(responseEntity);
		
		WeatherClientResponse response = client.callExternalWeatherService(CITY);
		logger.debug(response);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	
	@Test
	public void callExternalServiceNotFoundTest() throws Exception { 
		
		ResponseEntity<WeatherClientResponse> responseEntity = new ResponseEntity<WeatherClientResponse>(HttpStatus.NOT_FOUND);

		when(restTemplateMock.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<WeatherClientResponse>>any() 
              )
			).thenReturn(responseEntity);
		
		WeatherClientResponse response = client.callExternalWeatherService(CITY);
		logger.debug(response);
		
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void callExternalServiceBadRequestTest() throws Exception { 
		
		ResponseEntity<WeatherClientResponse> responseEntity = new ResponseEntity<WeatherClientResponse>(HttpStatus.BAD_REQUEST);

		when(restTemplateMock.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<WeatherClientResponse>>any() 
              )
			).thenReturn(responseEntity);
		
		WeatherClientResponse response = client.callExternalWeatherService(CITY);
		logger.debug(response);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	public void callExternalServiceInternalServerErrorTest() throws Exception { 
		
		ResponseEntity<WeatherClientResponse> responseEntity = new ResponseEntity<WeatherClientResponse>(HttpStatus.INTERNAL_SERVER_ERROR);

		when(restTemplateMock.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<WeatherClientResponse>>any() 
              )
			).thenReturn(responseEntity);
		
		WeatherClientResponse response = client.callExternalWeatherService(CITY);
		logger.debug(response);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
	
	 public static ResponseEntity<WeatherClientResponse> createResponseEntityOk() {
	      	        
	        WeatherClientResponse clientResponse = new WeatherClientResponse(); 
			clientResponse.setId("Id");
			Main main= new Main();
			main.setTemp(11.4);
			clientResponse.setMain(main);
			
			Sys sys= new Sys();
			sys.setSunset(12313L);
			sys.setSunrise(123221312L);
			clientResponse.setSys(sys);
			
			Weather weather= new Weather();
			weather.setDescription("scattered clouds");
			weather.setId("123");
			weather.setMain("London");

			List<Weather> list= new ArrayList<Weather>();
			list.add(weather);
			clientResponse.setWeather(list);
			
	        return new ResponseEntity<WeatherClientResponse>(clientResponse, HttpStatus.OK);
	    }

}
