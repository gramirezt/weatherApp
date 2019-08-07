package com.weather.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.exception.WeatherServiceException;
import com.weather.model.Main;
import com.weather.model.Sys;
import com.weather.model.Weather;
import com.weather.model.WeatherAppResponse;
import com.weather.model.WeatherClientResponse;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class WeatherResponseBuilderTest { 
	 
	private final static String CITY="London";
	private final static String HOUR="10.08 PM";
	private final static String DESCRIPTION="scattered clouds";
	private final static Double TEMP_C=-261.75;
	private final static Double TEMP_F=-439.15;
	
	@Value("${weather.decimal.format}")
	private String decimalFormat;
	
	@Value("${weather.time.format}")
	private String timeFormat;
	
	@Test
	public void buildWeatherResponseValidDataTest() throws WeatherServiceException {
		WeatherAppResponse response = WeatherResponseBuilder.buildWeatherResponse(CITY, createWeatherClientResponse(),timeFormat,decimalFormat);		 
		
		assertEquals(CITY, response.getCityName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(HOUR, response.getSunrise());
		assertEquals(HOUR, response.getSunrise());
		assertEquals(TEMP_C, response.getTempC());
		assertEquals(TEMP_F, response.getTempF());
	}
	
	@Test
	public void buildWeatherResponseValidNullTest() throws WeatherServiceException {
		WeatherAppResponse response = WeatherResponseBuilder.buildWeatherResponse(CITY, createWeatherClientResponse(),timeFormat,decimalFormat);		 
		
		assertNotNull( response.getCityName());
		assertNotNull( response.getDescription());
		assertNotNull( response.getDescription());
		assertNotNull( response.getSunrise());
		assertNotNull( response.getSunrise());
		assertNotNull( response.getTempC());
		assertNotNull( response.getTempF());
	}
	
	@Test(expected=WeatherServiceException.class)
	public void buildWeatherResponseExceptionTest() throws WeatherServiceException {
		WeatherAppResponse response = WeatherResponseBuilder.buildWeatherResponse(CITY, createWeatherClientResponse(),timeFormat,"2%3.sd$w23");		 
		
		assertNotNull( response.getCityName());
	}

	 public static WeatherClientResponse createWeatherClientResponse() {
	      	        
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
			weather.setDescription(DESCRIPTION);
			weather.setId("123");
			weather.setMain(CITY);
			

			List<Weather> list= new ArrayList<Weather>();
			list.add(weather);
			clientResponse.setWeather(list);
			
	        return clientResponse;
	    }

}
