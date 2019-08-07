package com.weather.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 

public class Utils {
	
	private static final int MILIS = 1000;
	private static final double TEMPERATURE = 273.15;
	private static final int CONST_9 = 9;
	private static final int CONST_5 = 5;
	private static final int CONST_32 = 32;
	private static DecimalFormat decimalFormat;

	/**
	 * Parse date to String Date representation
	 * @param time
	 * @param timePattern
	 * @return String representation for date
	 */
	public static String dateFormater(Date date, String format) { 
		DateFormat dateFormat = new SimpleDateFormat(format); 
		return dateFormat.format(date);

	}
	/**
	 * Parse time to String Date representation
	 * @param time
	 * @param timePattern
	 * @return String representation for time
	 */
	public static String parseTime(Long time, String timePattern) {	
		SimpleDateFormat dateFormat = new SimpleDateFormat(timePattern);
		Date tTime = new Date(time * MILIS); 

		return dateFormat.format(tTime);
	}

	/**
	 * Convert kelvin temperature to Cº
	 * @param tempKelvin
	 * @param format  
	 * @return Temperature in Celsius scale
	 */
	public static Double kelvinToCelsius(Double tempKelvin, String format) { 
		decimalFormat = new DecimalFormat(format);   
		Double celsius = tempKelvin - TEMPERATURE;  
		return Double.valueOf(decimalFormat.format(celsius)); 
	}
	
	/**
	 * Convert kelvin temperature to Fº
	 * @param tempKelvin
	 * @param format  
	 * @return Temperature in Fahrenhiet scale
	 */
	public static Double kelvinToFahrenheit(Double tempKelvin, String format) {
		 decimalFormat = new DecimalFormat(format);           
		 Double fahrenheit = (((tempKelvin -TEMPERATURE) * CONST_9) / CONST_5)+CONST_32; 
		return Double.valueOf(decimalFormat.format(fahrenheit)); 
        
	}
	



}
