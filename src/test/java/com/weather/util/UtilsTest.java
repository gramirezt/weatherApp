package com.weather.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
	
	@Value("${weather.time.format}")
	private String timeFormat;
	
	@Value("${weather.decimal.format}")
	private String decimalFormat;
	
	@Value("${weather.date.format}")
	private String dateFormat;
	
	@Test
	public void dateFormater() {
		Date date= new GregorianCalendar(2019, Calendar.MAY, 5).getTime();
		String actualDate= Utils.dateFormater(date,dateFormat );
		assertEquals("05/05/2019", actualDate); 
	}
	
	@Test
	public void parseNormalTimeTest() {	
		
		Long sunrise = 1556488306L;

		String sunriseStr = Utils.parseTime(sunrise, timeFormat);
		assertEquals("04.51 PM", sunriseStr); 
	}
	
	@Test
	public void parseMaxTimeTest() {	
		
		Long sunrise = Long.MAX_VALUE;

		String sunriseStr = Utils.parseTime(sunrise, timeFormat);
		assertEquals("05.59 PM", sunriseStr);
	}
	


	@Test
	public void kelvinToCelsiusNormalTest() { 
		Double kelvin, celsius;

		kelvin = 10d;
		celsius = Utils.kelvinToCelsius(kelvin, decimalFormat);

		assertEquals(Double.valueOf(-263.15), celsius);
	}
	
	@Test
	public void kelvinToFahrenheitNormalTest() {
		Double kelvin = 10d;
		Double fahrenheit = Utils.kelvinToCelsius(kelvin, decimalFormat);

		assertEquals(Double.valueOf(-263.15), fahrenheit);
        
	}
	
	@Test
	public void kelvinToCelsiusMaxTest() {

		Double kelvin = Double.MAX_VALUE;
		Double celsius = Utils.kelvinToCelsius(kelvin, decimalFormat);

		assertEquals(Double.valueOf(1.7976931348623157E308), celsius);
	}
	
	@Test(expected=NumberFormatException.class)
	public void kelvinToFahrenheitMaxErrorTest() { 

		Double kelvin = Double.MAX_VALUE;
		Double fahrenheit = Utils.kelvinToFahrenheit(kelvin, decimalFormat);
		assertEquals(Double.valueOf(1.7976931348623157E308), fahrenheit);
	}
	
	

}
